package dev.robercoding.htmlscreenshot.helpers

import platform.UIKit.UIImage

interface ImageUiCallback {
    fun finished(image: UIImage)
    fun error(error: Throwable)
}

