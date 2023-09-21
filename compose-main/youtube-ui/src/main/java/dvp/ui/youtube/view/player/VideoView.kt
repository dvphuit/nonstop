package dvp.ui.youtube.view.player

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.zIndex
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.AsyncImage
import dvp.data.youtube.models.VideoEntity
import dvp.data.youtube.viewmodel.MainEvent
import dvp.ui.youtube.helper.alias.OnControllerState
import dvp.ui.youtube.helper.alias.OnMainUiEvent
import dvp.ui.youtube.helper.alias.OnPlayerEvent
import dvp.ui.youtube.view.common.UiStateWrapper
import dvp.ui.youtube.helper.ext.loadImage
import dvp.ui.youtube.viewmodel.MediaViewModel
import dvp.ui.youtube.viewmodel.models.MediaData
import dvp.ui.youtube.viewmodel.models.MediaState
import dvp.ui.youtube.viewmodel.models.PlayerEvent
import dvp.ui.youtube.service.MediaPlayerService
import org.koin.androidx.compose.koinViewModel


@androidx.media3.common.util.UnstableApi
@Composable
internal fun VideoView(
    modifier: Modifier,
    video: VideoEntity?,
    onMainEvent: OnMainUiEvent,
) {
    val context = LocalContext.current
    val mediaViewModel: MediaViewModel = koinViewModel()

    if (video == null) {
        LaunchedEffect(key1 = "stop") {
            mediaViewModel.submit(PlayerEvent.Stop)
            MediaPlayerService.stop(context)
        }
        return
    }

    video.streamingData?.let {
        LaunchedEffect(key1 = video.id) {
            val initData = MediaData.fromVideoStreaming(video)
            mediaViewModel.submit(PlayerEvent.Init(initData))

            MediaPlayerService.start(
                context = context,
                initData = initData,
                onStopByNotification = {
                    onMainEvent(MainEvent.SetVideo(null))
                })
        }
    }

    UiStateWrapper(
        uiState = mediaViewModel.uiState,
        onLoading = {
            AsyncImage(
                model = context.loadImage(video.getThumbnailUrl()),
                contentDescription = video.title,
                contentScale = ContentScale.Crop,
                modifier = modifier.zIndex(1f)
            )
        },
        onReady = { state ->
            CustomVideoView(
                modifier = modifier,
                player = mediaViewModel.player,
                state = state,
                onEvent = mediaViewModel::submit,
                controller = { mediaState, event ->
                    AutoHideControllerView(
                        state = mediaState,
                        onEvent = event,
                    )
                })
        })
}


@Composable
fun CustomVideoView(
    modifier: Modifier,
    player: ExoPlayer,
    controller: @Composable OnControllerState? = null,
    state: MediaState,
    onEvent: OnPlayerEvent
) {
    Box(
        modifier = modifier
    ) {
        VideoSurface(
            modifier = Modifier,
            player = player,
            surfaceType = SurfaceType.SurfaceView,
        )
        controller?.invoke(state, onEvent)
    }
}
