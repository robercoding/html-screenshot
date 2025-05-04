package dev.robercoding.htmlscreenshot.helper

import dev.robercoding.htmlscreenshot.ImageFormat

class ScreenshotThatHtmlConfiguration(
    val imageWidth: Int? = null,
    val qualityImage: Int = 100,
    val imageFormat: ImageFormat = ImageFormat.PNG,
)