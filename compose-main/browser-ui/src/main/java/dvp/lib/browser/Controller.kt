package dvp.lib.browser

import dvp.lib.corebrowser.features.navigation.BrowserNavigationApi
import dvp.lib.corebrowser.features.navigation.ComposeBrowserNavigationApi

class Controller internal constructor(
    private val navigationApi: BrowserNavigationApi,
) : BrowserNavigationApi by navigationApi {

    companion object {
        private lateinit var instance: Controller

        @Synchronized
        fun getInstance(): Controller {
            if (!this::instance.isInitialized) {
                instance = Controller(navigationApi = ComposeBrowserNavigationApi())
            }
            return instance
        }
    }
}

