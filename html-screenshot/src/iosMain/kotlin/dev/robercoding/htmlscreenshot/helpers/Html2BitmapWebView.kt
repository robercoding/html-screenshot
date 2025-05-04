package dev.robercoding.htmlscreenshot.helpers

import kotlin.math.abs
import platform.WebKit.*
import kotlinx.cinterop.*
import platform.CoreGraphics.CGRectMake
import platform.darwin.NSObject
// import platform.Foundation.NSOperationQueueMainQueue
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration

@OptIn(ExperimentalForeignApi::class)
class Html2BitmapWebView(
    private val content: HtmlStringContent,
    private val bitmapWidth: Double?,
    private val configurator: ((WKWebViewConfiguration) -> Unit)?
) {

    private var callback: ImageUiCallback? = null
    private val width = bitmapWidth ?: 480.0
    private val webView: WKWebView by lazy {
        val configuration = WKWebViewConfiguration()
        configurator?.invoke(configuration)

        val height = 1.0
        val webView = WKWebView(CGRectMake(0.0, 0.0, width, height), configuration)

        webView.navigationDelegate = object : NSObject(), WKNavigationDelegateProtocol {
            override fun webView(webView: WKWebView, didFinishNavigation: WKNavigation?) {
                captureContent()
            }
        }
        webView
    }

    fun load(callback: ImageUiCallback? = null) {
        this.callback = callback
        dispatch_async(dispatch_get_main_queue()) {
            kotlin.runCatching {
                content.loadContent(webView)
            }.onFailure {
                // println("Error loading content: ${it.message}")
                callback?.error(it)
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun captureContent() {
        webView.evaluateJavaScript(
            "document.body.scrollHeight"
        ) { bodyHeight, error ->
            if (error != null) {
                callback?.error(IllegalStateException("Error measuring content height: $error"))
                return@evaluateJavaScript
            }

            val height = ((bodyHeight as? Double) ?: 1.0)
            val frame = CGRectMake(0.0, 0.0, width, height)

            webView.setFrame(frame)

            webView.evaluateJavaScript("requestAnimationFrame(() => true)") { _, jsError ->
                if (jsError != null) {
                    // println("Warning: Minor error during requestAnimationFrame: ${jsError.localizedDescription}")
                }

                // println("requestAnimationFrame callback fired. Taking snapshot...")
                val config = WKSnapshotConfiguration().apply {
                    rect = CGRectMake(0.0, 0.0, width, height)
                }

                webView.takeSnapshotWithConfiguration(config) { image, snapshotError ->
                    if (snapshotError != null) {
                        callback?.error(IllegalStateException("Snapshot error: $snapshotError"))
                    }
                    else if (image == null) {
                        callback?.error(IllegalStateException("Snapshot returned null image."))
                    } else {
                        val imageWidth = image.size.useContents { width }
                        val imageHeight = image.size.useContents { height }

                        // Optional check for size mismatch
                        if (abs(imageWidth - width) > 1 || abs(imageHeight - height) > 1) {
                            // println("Warning: Captured image size (w=$imageWidth, h=$imageHeight) differs significantly from requested frame size (w=$width, h=$height)!")
                        }
                        callback?.finished(image)
                    }
                }
            }
        }
    }
}