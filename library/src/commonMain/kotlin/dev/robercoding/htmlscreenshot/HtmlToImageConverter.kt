package dev.robercoding.htmlscreenshot

expect class HtmlToImageConverter {
    suspend fun convert(html: String): Html2ScreenshotResult
}