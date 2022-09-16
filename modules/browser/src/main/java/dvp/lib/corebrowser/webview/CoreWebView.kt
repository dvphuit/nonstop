package dvp.lib.corebrowser.webview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebSettings
import android.webkit.WebView
import dvp.lib.corebrowser.features.BrowserDelegate
import dvp.lib.corebrowser.features.navigation.BrowserNavigationListener

@SuppressLint("SetJavaScriptEnabled")
class CoreWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : WebView(context, attrs, defStyleAttr), BrowserDelegate {

    private val browserChromeClient = CoreChromeClient()
    private val coreWebViewClient = CoreWebViewClient()

    init {
        runCatching {
            with(settings) {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = true
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                domStorageEnabled = true
                allowFileAccess = true
                allowContentAccess = true
            }
        }
        webChromeClient = browserChromeClient
        webViewClient = coreWebViewClient
    }

    override fun delegateLoadUrl(url: String) = loadUrl(url)
    override fun delegateReload() = reload()
    override fun delegateStop() = stopLoading()
    override fun delegateForward() = goForward()
    override fun delegateBack() = goBack()
    override fun setNavigationListener(navigationListener: BrowserNavigationListener) {
        coreWebViewClient.setBrowserNavigationListener(navigationListener)
        browserChromeClient.setBrowserNavigationListener(navigationListener)
    }
}
