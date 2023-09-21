package dvp.ui.youtube.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dvp.lib.core.viewmodel.ViewState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun <T> UiStateWrapper(
    uiState: StateFlow<ViewState<T>>,
    onLoading: @Composable () -> Unit,
    onReady: @Composable (T) -> Unit,
    onEmpty: @Composable () -> Unit = {
        EmptyView()
    },
    onError: @Composable (Throwable) -> Unit = {
        ErrorView(e = it, action = {})
    }
) {
    val state by uiState.collectAsStateWithLifecycle()
    when (state) {
        is ViewState.Data -> onReady.invoke((state as ViewState.Data<T>).data)
        is ViewState.Loading -> onLoading.invoke()
        is ViewState.Empty -> onEmpty.invoke()
        is ViewState.Error -> onError.invoke((state as ViewState.Error).throwable)
    }
}