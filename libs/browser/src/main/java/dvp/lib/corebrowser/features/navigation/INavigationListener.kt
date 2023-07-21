package dvp.lib.corebrowser.features.navigation

interface INavigationListener {
    fun onPageFinished(url: String) {}
    fun onPageStarted(url: String) {}
    fun onPageProgressChanged(newProgress: Int) {}
    fun onNavigationStateUpdated(canGoBack: Boolean, canGoForward: Boolean) {}
}
