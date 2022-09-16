package dvp.lib.corebrowser.features

import dvp.lib.corebrowser.features.navigation.BrowserNavigationListener


interface BrowserDelegate {
  fun delegateReload()
  fun delegateStop()
  fun delegateForward()
  fun delegateBack()
  fun delegateLoadUrl(url: String)
  fun setNavigationListener(navigationListener: BrowserNavigationListener)
}
