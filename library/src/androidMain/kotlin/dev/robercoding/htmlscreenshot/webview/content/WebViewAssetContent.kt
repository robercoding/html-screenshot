package dev.robercoding.htmlscreenshot.webview.content

import android.webkit.WebView

internal class WebViewAssetContent(private val html: String) : WebViewContent() {
    override fun loadContent(webView: WebView) {
        webView.loadData(html, "text/html", "UTF-8")
    }
}