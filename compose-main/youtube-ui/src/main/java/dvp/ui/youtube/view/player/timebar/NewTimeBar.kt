package dvp.ui.youtube.view.player.timebar

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.systemGestureExclusion
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dvp.ui.youtube.helper.alias.OnPlayerEvent
import dvp.ui.youtube.viewmodel.models.MediaState
import dvp.ui.youtube.viewmodel.models.PlayerEvent
import dvp.ui.youtube.viewmodel.models.getRemainingTime
import kotlin.math.roundToInt

@Composable
fun TimeBar(
    state: MediaState,
    modifier: Modifier = Modifier,
    onEvent: OnPlayerEvent,
) {
//    println("TEST: TimeBar composing")
    BoxWithConstraints(
        modifier = modifier
            .systemGestureExclusion()
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        val width = with(LocalDensity.current) { maxWidth.toPx() }
        var scrubbing by remember { mutableStateOf(false) }
        val progress = state.currentMs.toFloat() / state.durationMs
        val bufferedPercent = state.bufferedMs.toFloat() / state.durationMs
        var offsetX by remember { mutableFloatStateOf(getX(progress, width)) }

        Column(
            modifier = modifier
                .wrapContentHeight()
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        offsetX += delta

                        offsetX = if (scrubbing) {
                            offsetX.coerceIn(0f..width)
                        } else {
                            getX(progress, width)
                        }
                    },
                    onDragStarted = {
                        scrubbing = true
                    },
                    onDragStopped = {
                        val pos = offsetX / width * state.durationMs
                        onEvent.invoke(PlayerEvent.Seek(pos.toLong()))
                        scrubbing = false
                    }
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
//                    .background(Color.Red)
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = state.getRemainingTime())
                Spacer(modifier = Modifier.weight(1f))
//                Text(text = "[ ]")
            }

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
//                    .background(Color.Red)
                ,
                contentAlignment = Alignment.BottomStart
            ) {
                TimeBarProgress(
                    played = progress,
                    buffered = bufferedPercent,
                    bufferedColor = Color.Red.copy(alpha = 0.2f),
                    playedColor = Color.Red,
                    modifier = Modifier
                        .height(4.dp)
                )

                TimeBarScrubber(
                    enabled = true,
                    scrubbing = true,
                    modifier = Modifier
                        .offset {
                            IntOffset(x = offsetX.roundToInt(), 6.dp.roundToPx())
                        }
                )

            }
        }
    }
}

private fun getX(percent: Float, maxWidth: Float): Float {
    if (percent < 0 || percent.isNaN()) return 0f
    if (percent > 1) return maxWidth
    return maxWidth * percent
}
