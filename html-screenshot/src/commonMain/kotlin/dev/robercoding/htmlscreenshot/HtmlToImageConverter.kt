package dev.robercoding.htmlscreenshot

import dev.robercoding.htmlscreenshot.helper.ScreenshotThatHtmlConfiguration

expect class HtmlToImageConverter(screenshotThatHtmlConfiguration: ScreenshotThatHtmlConfiguration) {
    suspend fun convert(html: String): Html2ScreenshotResult
}