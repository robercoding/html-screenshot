package dev.robercoding.htmlscreenshot.webview

import android.net.Uri

data class WebViewResource(
    val uri: Uri,
    var isLoaded: Boolean = false,
) {
    fun setLoaded() { isLoaded = true }
}