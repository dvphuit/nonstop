package dvp.lib.corebrowser.composer

import dvp.lib.corebrowser.features.navigation.ComposeBrowserNavigationApi


internal object BrowserFactory {

  fun createBrowserWith(): ComposeBrowserApi {
    return ComposeBrowserApi(navigationApi = ComposeBrowserNavigationApi())
  }
}
