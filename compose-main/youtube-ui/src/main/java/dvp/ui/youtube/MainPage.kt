package dvp.ui.youtube

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dvp.data.youtube.viewmodel.MainEvent
import dvp.data.youtube.viewmodel.MainViewModel
import dvp.ui.youtube.common.ErrorView
import dvp.ui.youtube.common.LoadingView
import dvp.ui.youtube.common.UiStateWrapper
import org.koin.androidx.compose.koinViewModel

@androidx.media3.common.util.UnstableApi
@Composable
fun MainPage(vm: MainViewModel = koinViewModel()) {
    Box(
        modifier = Modifier.fillMaxSize(),
        content = {
            UiStateWrapper(
                uiState = vm.uiState,
                onLoading = { LoadingView() },
                onReady = {
                    YoutubeMainView(state = it, onMainUiEvent = vm::submit)
                },
                onError = {
                    ErrorView(
                        e = it,
                        action = {
                            vm.submit(MainEvent.LoadVideos)
                        }
                    )
                })
        }
    )
}
