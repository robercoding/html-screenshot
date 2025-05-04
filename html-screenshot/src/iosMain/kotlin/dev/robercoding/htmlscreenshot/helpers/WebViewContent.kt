package dev.robercoding.htmlscreenshot.helpers

import platform.WebKit.WKWebView

interface WebViewContent {
    fun loadContent(webView: WKWebView)
}