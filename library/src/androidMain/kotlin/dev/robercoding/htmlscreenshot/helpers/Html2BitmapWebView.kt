package dev.robercoding.htmlscreenshot.helpers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.Canvas
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.webkit.WebViewCompat
import androidx.webkit.WebViewFeature
import dev.robercoding.htmlscreenshot.helper.ScreenshotThatHtmlConfiguration
import dev.robercoding.htmlscreenshot.utils.getDeviceWidth
import dev.robercoding.htmlscreenshot.webview.content.WebViewContent
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class Html2BitmapWebView(
    private val context: Context,
    private val content: WebViewContent,
    private val screenshotThatHtmlConfiguration: ScreenshotThatHtmlConfiguration,
) : ProgressChangedListener {

    companion object {
        private const val TAG = "Html2Bitmap"
    }

    private lateinit var callback: BitmapCallback
    private lateinit var webView: WebView
    private var internalProgress = 0

    private val deviceWidth by lazy {
        getDeviceWidth(context)
    }

    private val width by lazy {
        screenshotThatHtmlConfiguration.imageWidth ?: deviceWidth
    }

    /**
     * Load the content into the WebView and set up the WebViewClient to intercept requests.
     * This method is called when the Html2Bitmap instance is created.
     */
    fun load(callback: BitmapCallback) {
        CoroutineScope(Dispatchers.Main).launch {
            this@Html2BitmapWebView.callback = callback

            webView = WebView(context).apply {
                // Set the layout parameters to match the width of the bitmap
                setInitialScale(100)
                // Disable the scrollbars
                isVerticalScrollBarEnabled = false

                // Disable the built-in zoom controls
                settings.apply {
                    builtInZoomControls = false
                    setSupportZoom(false)
                    textZoom.let { textZoom = it }
                }

                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)
                        internalProgress = newProgress
                        progressChanged()
                    }
                }
            }

            content.setDoneListener(this@Html2BitmapWebView)

            webView.measure(
                View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(10, View.MeasureSpec.EXACTLY),
            )
            webView.layout(0, 0, webView.measuredWidth, webView.measuredHeight)
            content.loadContent(webView) // loads content and we listen for progress
        }
    }

    fun cleanup() {
        webView.stopLoading()
    }

    private val measurementMutex = Mutex()
    private var measuring = false
    private fun executeMeasurementAfterLoading() {
        Log.d(TAG, "Execute measurement after loading")
        CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG, "Is done? ${content.isDone()}, isMeasurementlocked? ${measurementMutex.isLocked}")
            measurementMutex.withLock {
                if(measuring){
                    return@launch
                }
                if (!content.isDone()) return@launch

                if (webView.contentHeight == 0) {
                    executeMeasurementAfterLoading()
                    return@launch
                }

                measuring = true
            }


            webView.measure(
                View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(webView.contentHeight, View.MeasureSpec.EXACTLY)
            )
            webView.layout(0, 0, webView.measuredWidth, webView.measuredHeight)

            waitForVisualStateCallback()

            executeScreenshot()
        }
    }

    private suspend fun waitForVisualStateCallback(): Unit =
        suspendCancellableCoroutine { continuation ->
            Log.d(TAG, "Wait for visual state callback")
            if (WebViewFeature.isFeatureSupported(WebViewFeature.VISUAL_STATE_CALLBACK)) {
                val requestId = System.currentTimeMillis() // Unique ID for the callback
                WebViewCompat.postVisualStateCallback(webView, requestId) { reqId ->
                    Log.d(TAG, "got reqid: $reqId")
                    if (reqId == requestId) {
                        continuation.resume(Unit)
                    }
                }
            } else {
                continuation.resumeWithException(
                    IllegalStateException("Visual state callback not supported on this WebView version.")
                )
            }
        }

    private val screenshotMutex = Mutex()
    private var tookScreenshot = false
    private suspend fun executeScreenshot() {
        Log.d(TAG, "Execute screenshot, is done? ${content.isDone()}")
        if (!content.isDone()) return
            Log.d(TAG, "Measured Height: ${webView.measuredHeight}")
        if (webView.measuredHeight == 0) {
            return
        }

        screenshotMutex.withLock {
            if (tookScreenshot) {
                return@withLock
            }
            try {
                Log.d(TAG, "Taking screenshot")
                val screenshot = takeScreenshot(webView)
                tookScreenshot = true
                // cleanup()
                callback.finished(screenshot)
            } catch (t: Throwable) {
                callback.error(t)
            }
        }
    }

    private fun takeScreenshot(webView: WebView): Bitmap {
        return try {
            createBitmap(webView.measuredWidth, webView.measuredHeight, Bitmap.Config.RGB_565).apply {
                val canvas = Canvas(this)
                // Optional: You might want to translate the canvas if the webview is scrolled,
                // although webView.draw() usually handles drawing the full content regardless
                // of scroll position when drawing to a sufficiently large canvas.
                // canvas.translate(-webView.scrollX.toFloat(), -webView.scrollY.toFloat())
                webView.draw(canvas)
            }
        } catch (e: OutOfMemoryError) {
            Log.e("ScreenshotError", "OutOfMemoryError creating bitmap: ${e.message}")
            // Handle OOM: maybe try a smaller bitmap config, or skip screenshot
            throw e
        } catch (e: Exception) {
            Log.e("ScreenshotError", "Error taking screenshot: ${e.message}")
            throw e
        }
    }

    override fun progressChanged() {
        Log.d(
            TAG,
            "Progress changed: $internalProgress ${content.isDone()}"
        )
        if (internalProgress == 100 && content.isDone()) {
            executeMeasurementAfterLoading()
            // pageFinished()
        }
    }
}