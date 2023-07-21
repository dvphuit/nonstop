package dvp.lib.core.viewmodel

import dvp.lib.common.extension.cast

sealed interface BaseViewState<T> {
    fun getData(): T {
        return this.cast<Ready<T>>().value
    }

    object Loading : BaseViewState<Nothing>
    object Empty : BaseViewState<Nothing>
    data class Ready<T>(val value: T) : BaseViewState<T>
    data class Error(val throwable: Throwable) : BaseViewState<Nothing>
}
