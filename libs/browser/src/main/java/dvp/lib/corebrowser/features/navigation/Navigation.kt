package dvp.lib.corebrowser.features.navigation


import android.util.Log
import dvp.lib.corebrowser.features.BrowserDelegate
import dvp.lib.corebrowser.features.utils.UrlUtils

class Navigation : IAction, INavigationListener {

    private var browserDelegate: BrowserDelegate? = null
    private var listeners = emptyList<INavigationListener>()

    override fun setBrowserDelegate(browserDelegate: BrowserDelegate) {
        this.browserDelegate = browserDelegate
        this.browserDelegate?.setNavigationListener(this)
    }

    override fun loadUrl(url: String) {
        val validUrl = UrlUtils.normalizeUrl(url)
        browserDelegate?.delegateLoadUrl(validUrl)
    }

    override fun reload() {
        browserDelegate?.delegateReload()
    }

    override fun stop() {
        browserDelegate?.delegateStop()
    }

    override fun goForward() {
        browserDelegate?.delegateForward()
    }

    override fun goBack() {
        browserDelegate?.delegateBack()
    }

    override fun close() {
        Log.d("TEST", "close")
    }

    override fun share() {
        Log.d("TEST", "share")
    }

    override fun addNavigationListener(browserNavigationListener: INavigationListener) {
        listeners = listeners + browserNavigationListener
    }

    override fun onPageProgressChanged(newProgress: Int) {
        listeners.forEach { it.onPageProgressChanged(newProgress) }
    }

    override fun onPageFinished(url: String) {
        listeners.forEach { it.onPageFinished(url) }
    }

    override fun onNavigationStateUpdated(canGoBack: Boolean, canGoForward: Boolean) {
        listeners.forEach { it.onNavigationStateUpdated(canGoBack, canGoForward) }
    }

    override fun onPageStarted(url: String) {
        listeners.forEach { it.onPageStarted(url) }
    }
}
