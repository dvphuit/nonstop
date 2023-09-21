package dvp.lib.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

//sealed class ViewState<out T> {
//    data class Data<T>(val data: T) : ViewState<T>()
//    object Empty : ViewState<Nothing>()
//    object Loading : ViewState<Nothing>()
//    data class Error(val errorMessage: String) : ViewState<Nothing>()
//}

//sealed class ViewIntent {
//    object LoadData : ViewIntent()
//}
//
//sealed class ViewAction {
//    object DataLoaded : ViewAction()
//    data class ErrorOccurred(val errorMessage: String) : ViewAction()
//}

//abstract class MviViewModel2<DATA, INTENT> : ViewModel() {
//    private val _viewState = MutableStateFlow<ViewState<DATA>>(ViewState.Empty)
//    val viewState: StateFlow<ViewState<DATA>> = _viewState
//
////    private val _viewAction = MutableSharedFlow<ViewAction>()
////    val viewAction: SharedFlow<ViewAction> = _viewAction
////
////    private val _viewIntent = MutableSharedFlow<ViewIntent>()
////    val viewIntent: SharedFlow<ViewIntent> = _viewIntent
//
//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            handleIntents()
//        }
//    }
//
//    abstract fun submit(event: INTENT)
//
//    private suspend fun handleIntents() {
//        viewIntent.collect { intent ->
//            when (intent) {
//                is ViewIntent.LoadData -> loadData()
//            }
//        }
//    }
//
//    private suspend fun loadData() {
//        _viewState.value = ViewState.Loading
//        // Simulate data loading delay
//        kotlinx.coroutines.delay(2000)
//
//        // Perform data retrieval or API call
//        val data = retrieveData()
//        if (data != null) {
//            _viewState.value = ViewState.Data(data)
//            _viewAction.emit(ViewAction.DataLoaded)
//        } else {
//            _viewState.value = ViewState.Error("Failed to load data")
//            _viewAction.emit(ViewAction.ErrorOccurred("Failed to load data"))
//        }
//    }
//
//    private fun retrieveData(): DATA? {
//        // Perform data retrieval or API call and return the result
//        return null
//    }
//
//    protected fun processIntent(intent: ViewIntent) {
//        viewModelScope.launch {
//            _viewIntent.emit(intent)
//        }
//    }
//}