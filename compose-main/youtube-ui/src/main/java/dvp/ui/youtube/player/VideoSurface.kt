package dvp.ui.youtube.player


import android.view.SurfaceView
import android.view.TextureView
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.Player

enum class SurfaceType {
    None, SurfaceView
}

@Composable
internal fun VideoSurface(
    player: Player,
    modifier: Modifier,
    surfaceType: SurfaceType = SurfaceType.SurfaceView,
) {
    val context = LocalContext.current
    key(surfaceType, context) {
        if (surfaceType != SurfaceType.None) {
            fun Player.clearVideoView(view: View) {
                when (surfaceType) {
                    SurfaceType.None -> throw IllegalStateException()
                    SurfaceType.SurfaceView -> clearVideoSurfaceView(view as SurfaceView)
                }
            }

            fun Player.setVideoView(view: View) {
                when (surfaceType) {
                    SurfaceType.None -> throw IllegalStateException()
                    SurfaceType.SurfaceView -> setVideoSurfaceView(view as SurfaceView)
                }
            }

            val videoView = remember {
                when (surfaceType) {
                    SurfaceType.None -> throw IllegalStateException()
                    SurfaceType.SurfaceView -> SurfaceView(context)
                }
            }
            AndroidView(
                factory = { videoView },
                modifier = modifier,
            ) {
                // update player
                val previousPlayer = it.tag as? Player
                if (previousPlayer === player) return@AndroidView

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