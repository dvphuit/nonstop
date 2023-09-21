package dvp.ui.youtube.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import dvp.data.youtube.viewmodel.MainEvent
import dvp.data.youtube.viewmodel.YoutubeState
import dvp.lib.core.debug.text5
import dvp.ui.youtube.helper.alias.OnMainUiEvent
import dvp.ui.youtube.view.player.VideoView

@androidx.media3.common.util.UnstableApi
@Composable
internal fun YoutubeMainView(
    state: YoutubeState,
    onMainUiEvent: OnMainUiEvent
) {
    YoutubeMotionContainer(
        state = state,
        videoListView = { onVideoClicked ->
            VideoListView(
                modifier = this.zIndex(0f),
                state = state,
                onVideoClicked = { video ->
                    onVideoClicked.invoke()
                    onMainUiEvent.invoke(MainEvent.SetVideo(video))
                },
                onChannelClicked = {
                    // todo
                }
            )
        },
        videoView = {
            VideoView(
                modifier = this.zIndex(3f),
                video = state.openingVideo,
                onMainEvent = onMainUiEvent
            )
        },
        titleView = {
            Box(
                modifier = this
            ) {
                Text(text = text5)
            }
        },
        videoRelativeView = {
            VideoRelatedView(
                modifier = this.zIndex(2f),
                video = state.openingVideo
            )
        },
        closeButton = { onCloseClicked ->
            Box(modifier = this
                .clickable {
                    onCloseClicked.invoke()
                    onMainUiEvent.invoke(MainEvent.SetVideo(null))
                }
                .background(Color.Black)
            )
        }
    )
}