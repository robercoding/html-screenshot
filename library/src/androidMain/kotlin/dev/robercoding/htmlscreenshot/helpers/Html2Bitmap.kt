package dev.robercoding.htmlscreenshot.helpers

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import dev.robercoding.htmlscreenshot.helper.ScreenshotThatHtmlConfiguration
import dev.robercoding.htmlscreenshot.webview.content.WebViewContent
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.suspendCancellableCoroutine

class Html2Bitmap constructor(
    private val context: Context,
    private val screenshotThatHtmlConfiguration: ScreenshotThatHtmlConfiguration,
) {

    private var content: WebViewContent? = null

    fun setContent(content: WebViewContent): Html2Bitmap {
        this.content = content
        return this
    }


    suspend fun getBitmap(): Bitmap {
        val content = content ?: throw IllegalStateException("Content not set. Please call setContent() before) getBitmap().")
        val html2BitmapWebView = Html2BitmapWebView(
            context,
            content,
            screenshotThatHtmlConfiguration,
        )
        val bitmap = awaitBitmap(html2BitmapWebView)
        return bitmap
    }

    private suspend fun awaitBitmap(html2BitmapWebView: Html2BitmapWebView): Bitmap =
        suspendCancellableCoroutine { continuation ->
            html2BitmapWebView.load(object : BitmapCallback {
                override fun finished(bitmap: Bitmap) {
                    continuation.resume(bitmap)
                }

                override fun error(throwable: Throwable) {
                    Log.d("Html2Bitmap", "Error: ${throwable.message}")
                    continuation.resumeWithException(throwable)
                }
            })
        }

}