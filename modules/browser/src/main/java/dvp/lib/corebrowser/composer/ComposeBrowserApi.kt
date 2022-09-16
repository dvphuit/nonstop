package dvp.lib.corebrowser.composer

import dvp.lib.corebrowser.features.navigation.BrowserNavigationApi

internal class ComposeBrowserApi internal constructor(
    private val navigationApi: BrowserNavigationApi,
) : BrowserNavigationApi by navigationApi {

    companion object {
        private lateinit var instance: ComposeBrowserApi

        @Synchronized
        fun getInstance(): ComposeBrowserApi {
            if (!this::instance.isInitialized) {
                instance = BrowserFactory.createBrowserWith()
            }
            return instance
        }
    }
}

