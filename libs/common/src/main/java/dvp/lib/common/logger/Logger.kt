package dvp.lib.common.logger

import timber.log.Timber

object Logger {
    fun init() {
//        Timber.plant(Timber.asTree())
    }
}

fun logD(tag: String, message: String, vararg args: Any?) {
    Timber.tag(tag).d(message, args)
}

fun logD(message: String, vararg args: Any?) {
    Timber.d(message, args)
}

fun logE(error: String, vararg args: Any?) {
    Timber.e(error, args)
}

fun logE(tag: String, exception: Throwable) {
    Timber.tag(tag).e(exception)
}