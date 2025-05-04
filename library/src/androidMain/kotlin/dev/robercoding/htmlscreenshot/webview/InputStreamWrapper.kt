package dev.robercoding.htmlscreenshot.webview

import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream

class InputStreamWrapper(
    private val callback: Callback,
    inputStream: InputStream
) : BufferedInputStream(inputStream) {

    @Throws(IOException::class)
    override fun close() {
        super.close()
        callback.onClose()
    }

    fun interface Callback {
        fun onClose()
    }
}