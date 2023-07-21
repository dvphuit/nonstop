package dvp.lib.browser

import dvp.lib.corebrowser.features.navigation.IAction
import dvp.lib.corebrowser.features.navigation.Navigation

class Controller internal constructor(
    private val navigationApi: IAction,
) : IAction by navigationApi {

    companion object {
        private lateinit var instance: Controller

        @Synchronized
        fun getInstance(): Controller {
            if (!this::instance.isInitialized) {
                instance = Controller(navigationApi = Navigation())
            }
            return instance
        }
    }
}

