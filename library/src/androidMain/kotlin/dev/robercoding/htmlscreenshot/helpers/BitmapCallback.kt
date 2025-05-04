package dev.robercoding.htmlscreenshot.helpers

import android.graphics.Bitmap

interface BitmapCallback {
    fun finished(bitmap: Bitmap)
    fun error(throwable: Throwable)

    companion object {
        // Convenience method for creating callbacks with lambdas
        inline fun create(
            crossinline onFinished: (Bitmap) -> Unit,
            crossinline onError: (Throwable) -> Unit
        ): BitmapCallback = object : BitmapCallback {
            override fun finished(bitmap: Bitmap) = onFinished(bitmap)
            override fun error(throwable: Throwable) = onError(throwable)
        }
    }
}