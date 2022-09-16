package dvp.lib.corebrowser.features.navigation


import dvp.lib.corebrowser.features.BrowserDelegate
import dvp.lib.corebrowser.features.utils.URLUtils
import dvp.lib.corebrowser.features.utils.URLUtilsImpl

class ComposeBrowserNavigationApi(
  private val urlUtil: URLUtils = URLUtilsImpl(),
) : BrowserNavigationApi, BrowserNavigationListener {

  private var browserDelegate: BrowserDelegate? = null
  private var listeners = emptyList<BrowserNavigationListener>()

  override fun setBrowserDelegate(browserDelegate: BrowserDelegate) {
    this.browserDelegate = browserDelegate
    this.browserDelegate?.setNavigationListener(this)
  }

  override fun loadUrl(url: String) {
    val validUrl = urlUtil.normalizeUrl(url)
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

  override fun addNavigationListener(browserNavigationListener: BrowserNavigationListener) {
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
