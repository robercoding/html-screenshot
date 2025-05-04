package dev.robercoding.htmlscreenshot.helpers

import platform.WebKit.WKWebView

class HtmlStringContent(private val html: String) : WebViewContent {
    override fun loadContent(webView: WKWebView) {
        webView.loadHTMLString(html, baseURL = null)
    }
}