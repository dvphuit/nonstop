package dvp.ui.youtube.player

import android.view.SurfaceView
import android.view.TextureView
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.media3.common.Player
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dvp.data.youtube.models.VideoEntity
import dvp.data.youtube.viewmodel.DetailEvent
import dvp.data.youtube.viewmodel.DetailViewModel
import dvp.data.youtube.viewmodel.MainEvent
import dvp.data.youtube.viewmodel.YoutubeState
import dvp.data.youtube.viewmodel.MainViewModel
import dvp.lib.core.viewmodel.BaseViewState
import dvp.ui.youtube.common.EmptyView
import dvp.ui.youtube.common.ErrorView
import dvp.ui.youtube.media.SurfaceType
import dvp.ui.youtube.mediaplayer.MediaViewModel
import dvp.ui.youtube.mediaplayer.models.MediaData
import dvp.ui.youtube.mediaplayer.models.PlayerEvent
import dvp.ui.youtube.mediaplayer.service.OnlinePlayerService
import org.koin.androidx.compose.koinViewModel


@androidx.media3.common.util.UnstableApi
@Composable
internal fun Modifier.VideoView(video: VideoEntity?) {
    val context = LocalContext.current
    val mediaViewModel: MediaViewModel = koinViewModel()

    if (video == null) {
        LaunchedEffect(key1 = "stop") {
            mediaViewModel.submit(PlayerEvent.Stop)
            OnlinePlayerService.stop(context)
        }
        return
    }

    val mainViewModel: MainViewModel = koinViewModel()

    video.streamingData?.let {
        LaunchedEffect(key1 = video.id) {
            val initData = MediaData.fromVideoStreaming(video)
            mediaViewModel.submit(PlayerEvent.Init(initData))

            OnlinePlayerService.start(
                context = context,
                initData = initData,
                onStopByNotification = {
                    mainViewModel.submit(MainEvent.SetVideo(null))
                })
        }
    }

    val mediaState by mediaViewModel.uiState.collectAsState()

    when (mediaState) {
        is BaseViewState.Ready -> {
            VideoSurface(
                modifier = this,
                player = mediaViewModel.player,
                surfaceType = mediaViewModel.getData().surfaceType,
            )
        }

        is BaseViewState.Loading -> AsyncImage(
            model = ImageRequest.Builder(context)
                .data(video.getThumbnailUrl())
                .crossfade(true)
                .build(),
            contentDescription = video.title,
            contentScale = ContentScale.Crop,
            modifier = this
                .zIndex(1f)
        )

        is BaseViewState.Empty -> EmptyView()
        is BaseViewState.Error -> ErrorView(
            e = mediaViewModel.getError(),
            action = {}
        )
    }
}


@Composable
private fun VideoSurface(
    player: Player,
    surfaceType: SurfaceType,
    modifier: Modifier
) {
    val context = LocalContext.current
    key(surfaceType, context) {
        if (surfaceType != SurfaceType.None) {
            fun Player.clearVideoView(view: View) {
                when (surfaceType) {
                    SurfaceType.None -> throw IllegalStateException()
                    SurfaceType.SurfaceView -> clearVideoSurfaceView(view as SurfaceView)
                    SurfaceType.TextureView -> clearVideoTextureView(view as TextureView)
                }
            }

            fun Player.setVideoView(view: View) {
                when (surfaceType) {
                    SurfaceType.None -> throw IllegalStateException()
                    SurfaceType.SurfaceView -> setVideoSurfaceView(view as SurfaceView)
                    SurfaceType.TextureView -> setVideoTextureView(view as TextureView)
                }
            }

            val videoView = remember {
                when (surfaceType) {
                    SurfaceType.None -> throw IllegalStateException()
                    SurfaceType.SurfaceView -> SurfaceView(context)
                    SurfaceType.TextureView -> TextureView(context)
                }
            }
            AndroidView(
                factory = { videoView },
                modifier = modifier,
            ) {
                // update player
                val previousPlayer = it.tag as? Player
                if (previousPlayer === player) return@AndroidView
                println("TEST: video update player")

                previousPlayer?.clearVideoView(it)

                it.tag = player.apply {
                    setVideoView(it)
                }
            }
            DisposableEffect(Unit) {
                onDispose {
                    println("TEST: video disposed")
                    (videoView.tag as? Player)?.clearVideoView(videoView)
                }
            }
        }
    }
}
