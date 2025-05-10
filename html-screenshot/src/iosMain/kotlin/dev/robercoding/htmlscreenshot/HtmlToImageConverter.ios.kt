package dev.robercoding.htmlscreenshot

import dev.robercoding.htmlscreenshot.helper.ScreenshotThatHtmlConfiguration
import dev.robercoding.htmlscreenshot.helpers.ImageUiCallback
import dev.robercoding.htmlscreenshot.helpers.Html2BitmapWebView
import dev.robercoding.htmlscreenshot.helpers.HtmlStringContent
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSData
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import platform.UIKit.UIImagePNGRepresentation
import platform.posix.memcpy

actual class HtmlToImageConverter actual constructor(private val screenshotThatHtmlConfiguration: ScreenshotThatHtmlConfiguration) {
    actual suspend fun convert(
        html: String,
    ): Html2ScreenshotResult {
        return try {
            val webViewContent = HtmlStringContent(html)

            val converter = Html2BitmapWebView(
                content = webViewContent,
                bitmapWidth = screenshotThatHtmlConfiguration.imageWidth?.toDouble(),
                configurator = null
            )

            val image = awaitUiImage(html2BitmapWebView = converter)
            val byteArray = image.toByteArray(screenshotThatHtmlConfiguration.imageFormat, screenshotThatHtmlConfiguration.qualityImage) ?: throw IllegalStateException("Error converting UIImage to ByteArray")
            Html2ScreenshotResult.Success(byteArray)
        } catch (e: Exception) {
            Html2ScreenshotResult.Error(e)
        }
    }

    private suspend fun awaitUiImage(
        html2BitmapWebView: Html2BitmapWebView,
    ): UIImage = suspendCancellableCoroutine { continuation ->
        html2BitmapWebView.load(object : ImageUiCallback {
            override fun finished(image: UIImage) {
                continuation.resume(image)
            }

            override fun error(error: Throwable) {
                continuation.resumeWithException(error)
            }
        })
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun UIImage.toByteArray(format: ImageFormat, quality: Int): ByteArray? {
    return memScoped {
        val data = when (format) {
            ImageFormat.PNG -> UIImagePNGRepresentation(this@toByteArray)
            ImageFormat.JPEG -> UIImageJPEGRepresentation(this@toByteArray, quality.toDouble() / 100.0)
        }
        data?.toByteArray()
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun NSData.toByteArray(): ByteArray = ByteArray(this@toByteArray.length.toInt()).apply {
    usePinned {
        memcpy(it.addressOf(0), this@toByteArray.bytes, this@toByteArray.length)
    }
}
