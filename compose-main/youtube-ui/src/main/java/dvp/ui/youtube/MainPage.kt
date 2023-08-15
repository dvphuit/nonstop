package dvp.ui.youtube

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dvp.data.youtube.viewmodel.MainEvent
import dvp.data.youtube.viewmodel.MainViewModel
import dvp.lib.core.viewmodel.BaseViewState
import dvp.ui.youtube.common.EmptyView
import dvp.ui.youtube.common.ErrorView
import dvp.ui.youtube.common.LoadingView
import org.koin.androidx.compose.koinViewModel

@androidx.media3.common.util.UnstableApi
@Composable
fun  MainPage(vm: MainViewModel = koinViewModel()) {
    val uiState by vm.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        content = {
            when (uiState) {
                is BaseViewState.Ready -> YoutubeMainView(main = vm)
                is BaseViewState.Empty -> EmptyView(modifier = Modifier)
                is BaseViewState.Error -> ErrorView(
                    e = vm.getError(),
                    action = {
                        vm.submit(MainEvent.LoadVideos)
                    }
                )

                is BaseViewState.Loading -> LoadingView()
            }
        }
    )
}
