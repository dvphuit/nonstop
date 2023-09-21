package dvp.ui.youtube

import android.content.Context
import androidx.compose.runtime.Composable
import dvp.ui.youtube.view.common.AppStateHandler
import dvp.ui.youtube.viewmodel.MediaViewModel
import dvp.ui.youtube.viewmodel.models.PlayerEvent
import dvp.ui.youtube.view.MainPage
import org.koin.androidx.compose.koinViewModel

@androidx.media3.common.util.UnstableApi
@Composable
fun YoutubeRouter(context: Context) {
    val mediaViewModel: MediaViewModel = koinViewModel()
    AppStateHandler(
        onBackground = {
            mediaViewModel.submit(PlayerEvent.Foreground)
        },
        onForeground = {
            mediaViewModel.submit(PlayerEvent.Background)
        })

    MainPage()

//    TestVideoPlayer()
}