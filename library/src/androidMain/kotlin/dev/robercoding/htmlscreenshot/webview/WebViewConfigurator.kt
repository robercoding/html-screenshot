package dev.robercoding.htmlscreenshot.webview

import android.webkit.WebView

/**
 * SAM interface for WebView configuration
 */
fun interface WebViewConfigurator {
    fun configure(webView: WebView)
}