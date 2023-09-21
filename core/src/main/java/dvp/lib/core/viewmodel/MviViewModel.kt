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
    private val _uiState = MutableStateFlow<ViewState<STATE>>(ViewState.Empty)
    val uiState: StateFlow<ViewState<STATE>> = _uiState

    private val _handler = CoroutineExceptionHandler { _, exception ->
        logE("MviViewModel", exception)
        onError(exception)
    }


    abstract fun submit(event: EVENT)

    open fun getData(): STATE {
        val value = _uiState.value
        if (value is ViewState.Data) {
            return value.data
        } else {
            error("state is not ready")
        }
    }

    open fun getReady(): STATE? {
        val value = _uiState.value
        return if (value is ViewState.Data) {
            value.data
        } else {
            null
        }
    }

    fun getError(): Throwable {
        return _uiState.value.cast<ViewState.Error>().throwable
    }


    protected fun setData(state: STATE?.() -> STATE?) {
        _uiState.value = when (val current = _uiState.value) {
            is ViewState.Data -> {
                ViewState.Data(state.invoke(current.data)!!)
            }

            else -> {
                ViewState.Data(state.invoke(null)!!)
            }
        }
    }

    protected fun updateData(state: STATE.() -> STATE) {
        _uiState.value = when (val current = _uiState.value) {
            is ViewState.Data -> {
                ViewState.Data(state.invoke(current.data))
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
        _uiState.value = ViewState.Loading
    }

    protected fun setEmpty() {
        _uiState.value = ViewState.Empty
    }

    open fun onError(exception: Throwable) {
        _uiState.value = ViewState.Error(exception)
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