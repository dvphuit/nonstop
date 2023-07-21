package dvp.lib.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvp.lib.common.extension.cast
import dvp.lib.common.logger.logE
import dvp.lib.core.model.DataState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class MviViewModel<STATE, EVENT> : ViewModel() {

    private val _uiState = MutableStateFlow<BaseViewState<*>>(BaseViewState.Empty)
    private val _handler = CoroutineExceptionHandler { _, exception ->
        logE("MviViewModel", exception)
        onError(exception)
    }

    val uiState = _uiState.asStateFlow()


    abstract fun submit(event: EVENT)

    open fun getData(): STATE {
        if (_uiState.value is BaseViewState.Ready){
            return _uiState.value.cast<BaseViewState.Ready<STATE>>().value
        } else {
            error("state is not ready")
        }
    }

    open fun getReady(): STATE? {
        return if (_uiState.value is BaseViewState.Ready){
            _uiState.value.cast<BaseViewState.Ready<STATE>>().value
        } else {
            null
        }
    }

    fun getError(): Throwable {
        return _uiState.value.cast<BaseViewState.Error>().throwable
    }


    protected fun setData(state: STATE?.() -> STATE?) {
        _uiState.value = when (val current = _uiState.value) {
            is BaseViewState.Ready -> {
                BaseViewState.Ready(state.invoke(current.value.cast()))
            }

            else -> {
                BaseViewState.Ready(state.invoke(null))
            }
        }
    }

    protected fun updateData(state: STATE.() -> STATE) {
        _uiState.value = when (val current = _uiState.value) {
            is BaseViewState.Ready -> {
                BaseViewState.Ready(state.invoke(current.value.cast()))
            }

            else -> {
                error("State is not ready")
            }
        }
    }


    protected fun setError(ex: Exception) {
        onError(ex)
    }

    protected fun setLoading() {
        _uiState.value = BaseViewState.Loading
    }

    protected fun setEmpty() {
        _uiState.value = BaseViewState.Empty
    }

    open fun onError(exception: Throwable) {
        _uiState.value = BaseViewState.Error(exception)
    }


    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(_handler, block = block)
    }

    protected suspend fun <T> call(
        flow: Flow<T>,
        completion: (collect: T) -> Unit = {}
    ) {
        flow.catch { onError(it) }
            .collect {
                completion.invoke(it)
            }
    }

    protected suspend fun <T> execute(
        call: Flow<DataState<T>>,
        complettion: (collect: T) -> Unit = {}
    ) {
        call.onStart { setLoading() }
            .catch { onError(it) }
            .collect { state ->
                when (state) {
                    is DataState.Error -> onError(state.error)
                    is DataState.Success -> complettion.invoke(state.result)
                }
            }
    }
}