package dvp.lib.corebrowser.features.navigation

import dvp.lib.corebrowser.features.BrowserDelegate


interface IAction {
    fun setBrowserDelegate(browserDelegate: BrowserDelegate)
    fun addNavigationListener(browserNavigationListener: INavigationListener)
    fun reload()
    fun stop()
    fun goForward()
    fun goBack()
    fun close()
    fun share()
    fun loadUrl(url: String)
}

