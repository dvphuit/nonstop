package dvp.lib.corebrowser.webview

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import dvp.lib.corebrowser.features.navigation.BrowserNavigationListener

internal class CoreWebViewClient() : WebViewClient() {
  private var listener: BrowserNavigationListener? = null

  fun setBrowserNavigationListener(navigationListener: BrowserNavigationListener) {
    this.listener = navigationListener
  }

  override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
    super.onPageStarted(view, url, favicon)
    listener?.onPageStarted(url)
  }

  override fun doUpdateVisitedHistory(view: WebView, url: String, isReload: Boolean) {
    super.doUpdateVisitedHistory(view, url, isReload)
    listener?.onNavigationStateUpdated(view.canGoBack(), view.canGoForward())
  }

  override fun onPageFinished(view: WebView, url: String) {
    super.onPageFinished(view, url)
    listener?.onPageFinished(url)
  }
}
