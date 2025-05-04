package dev.robercoding.htmlscreenshot

import android.graphics.Bitmap
import android.util.Log
import dev.robercoding.htmlscreenshot.di.applicationContext
import dev.robercoding.htmlscreenshot.helper.ScreenshotThatHtmlConfiguration
import dev.robercoding.htmlscreenshot.helpers.Html2Bitmap
import dev.robercoding.htmlscreenshot.webview.content.WebViewContent
import java.io.ByteArrayOutputStream

actual class HtmlToImageConverter(private val screenshotThatHtmlConfiguration: ScreenshotThatHtmlConfiguration) {

    val html2bitmap = Html2Bitmap(
        context = applicationContext,
        screenshotThatHtmlConfiguration = screenshotThatHtmlConfiguration,
    )

    actual suspend fun convert(
        html: String,
    ): Html2ScreenshotResult {
        return  try {
            html2bitmap.setContent(WebViewContent.html(html))
            val bitmap = html2bitmap.getBitmap()

            Log.d("HtmlToImageConverter", "Bitmap: $bitmap")

            val byteArray = ByteArrayOutputStream().use { stream ->
                when (screenshotThatHtmlConfiguration.imageFormat) {
                    ImageFormat.PNG -> bitmap.compress(Bitmap.CompressFormat.PNG, screenshotThatHtmlConfiguration.qualityImage, stream)
                    ImageFormat.JPEG -> bitmap.compress(Bitmap.CompressFormat.JPEG, screenshotThatHtmlConfiguration.qualityImage, stream)
                }
                stream.toByteArray()
            }
            Html2ScreenshotResult.Success(byteArray)
        } catch (e: Exception) {
            Html2ScreenshotResult.Error(IllegalStateException("Error converting HTML to image: ${e.message}", e))
        }
    }
}