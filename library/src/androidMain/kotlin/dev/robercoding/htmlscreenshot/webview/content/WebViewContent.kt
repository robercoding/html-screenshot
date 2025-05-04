package dev.robercoding.htmlscreenshot.webview.content

import android.webkit.WebView
import dev.robercoding.htmlscreenshot.helpers.ProgressChangedListener
import dev.robercoding.htmlscreenshot.webview.WebViewResource
import dev.robercoding.htmlscreenshot.webview.content.WebViewContent.Companion.html
import java.lang.ref.WeakReference
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Base class for loading content into a WebView.
 *
 * This class handles the loading of local and remote resources, and provides a mechanism to
 * notify when all resources have been loaded.
 *
 * @constructor Creates a new instance of WebViewContent.
 *
 * @property webViewResources A list of resources to be loaded in the WebView.
 * @property webViewContentLoaded A weak reference to the listener that will be notified when loading is complete.
 *
 * @see [html] for loading local HTML content.
 *
 * @see [loadContent] for loading the content into the WebView.
 *
 */
abstract class WebViewContent {

    private val webViewResources = CopyOnWriteArrayList<WebViewResource>()
    private var webViewContentLoaded: WeakReference<ProgressChangedListener>? = null

    companion object {
        private const val TAG = "WebViewContent"

        fun html(html: String): WebViewContent = WebViewAssetContent(html)
    }

    /**
     * Loads the content into the WebView.
     */
    abstract fun loadContent(webView: WebView)

    fun isDone(): Boolean = webViewResources.all { it.isLoaded }

    fun setDoneListener(listener: ProgressChangedListener) {
        webViewContentLoaded = WeakReference(listener)
    }

    private fun resourceLoaded() {
        val listener = webViewContentLoaded?.get()
        if (isDone() && listener != null) {
            listener.progressChanged()
        }
    }
}