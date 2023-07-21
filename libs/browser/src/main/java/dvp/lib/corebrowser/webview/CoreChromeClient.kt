package dvp.lib.corebrowser.webview

import android.webkit.WebChromeClient
import android.webkit.WebView
import dvp.lib.corebrowser.features.navigation.INavigationListener

internal class CoreChromeClient : WebChromeClient() {
    private var listener: INavigationListener? = null

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        listener?.onPageProgressChanged(newProgress)
    }

    fun setBrowserNavigationListener(navigationListener: INavigationListener) {
        this.listener = navigationListener
    }
}
