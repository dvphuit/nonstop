package dvp.lib.corebrowser.webview

import android.webkit.WebChromeClient
import android.webkit.WebView
import dvp.lib.corebrowser.features.navigation.BrowserNavigationListener

internal class CoreChromeClient() : WebChromeClient() {
    private var listener: BrowserNavigationListener? = null

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        listener?.onPageProgressChanged(newProgress)
    }

    fun setBrowserNavigationListener(navigationListener: BrowserNavigationListener) {
        this.listener = navigationListener
    }
}
