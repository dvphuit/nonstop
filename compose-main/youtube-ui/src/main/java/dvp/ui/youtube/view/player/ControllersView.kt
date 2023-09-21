package dvp.ui.youtube.view.player

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dvp.ui.youtube.R
import dvp.ui.youtube.helper.alias.OnPlayerEvent
import dvp.ui.youtube.viewmodel.models.MediaState
import dvp.ui.youtube.viewmodel.models.PlayerEvent
import dvp.ui.youtube.view.player.timebar.TimeBar
import kotlinx.coroutines.delay
import kotlin.math.max


@Composable
fun AutoHideControllerView(
    state: MediaState,
    onEvent: OnPlayerEvent
) {
    var isShowing by remember { mutableStateOf(false) }
    var resetTapping by remember { mutableIntStateOf(0) }
//    println("TEST: AutoHideControllerView composing")
    Crossfade(
        modifier = Modifier
            .fillMaxSize(),
        targetState = isShowing,
        animationSpec = tween(300),
        label = "media_controller"
    ) { shown ->
        SeekerView(
            modifier = Modifier.fillMaxSize(),
            onEvent = onEvent,
            onSingleTap = {
                isShowing = !isShowing
            }
        )
        if (shown) {
            LaunchedEffect("hide$resetTapping") {
                delay(3000)
                isShowing = false
            }
            VideoControllerView(
                state = state,
                onEvent = {
                    resetTapping++
                    onEvent.invoke(it)
                })
        }
    }
}

@Composable
fun VideoControllerView(
    state: MediaState,
    onEvent: OnPlayerEvent,
    timebar: @Composable BoxScope.() -> Unit = {
        TimeBar(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            state = state,
            onEvent = onEvent
        )
    },
    seeker: @Composable BoxScope.() -> Unit = {

    },
    playbackControl: @Composable BoxScope.() -> Unit = {
        PlaybackControlButtons(
            modifier = Modifier.align(Alignment.Center),
            state = state,
            onEvent = onEvent
        )
    },
    setting: @Composable BoxScope.() -> Unit = {},
) {
//    println("TEST: VideoControllerView composing")
    Box(modifier = Modifier.fillMaxSize()) {
        setting(this)
        seeker(this)
        timebar(this)
        playbackControl(this)
    }
}

@Composable
fun PlaybackControlButtons(
    modifier: Modifier,
    state: MediaState,
    onEvent: OnPlayerEvent
) {
    Row(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
//            .background(Color.Yellow)
        ,
        horizontalArrangement = Arrangement.spacedBy(32.dp, alignment = Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SimpleIconButton(
            resId = R.drawable.ic_previous_track,
            sizeDp = 30,
            enabled = state.hasSeekToPrev(),
            onClick = {
                onEvent.invoke(PlayerEvent.PreVideo)
            }
        )

        SimpleIconButton(
            resId = if (state.isPlaying) R.drawable.ic_pause else R.drawable.ic_play,
            sizeDp = 60,
            onClick = {
                onEvent.invoke(PlayerEvent.PlayPause)
            })

        SimpleIconButton(
            resId = R.drawable.ic_next_track,
            sizeDp = 30,
            enabled = state.hasSeekToNext(),
            onClick = {
                onEvent.invoke(PlayerEvent.NextVideo)
            })
    }
}

@Composable
private fun SimpleIconButton(
    @DrawableRes resId: Int,
    sizeDp: Int = 50,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    require(sizeDp >= 30)

    IconButton(
        modifier = Modifier
            .size(sizeDp.dp)
            .clip(CircleShape)
            .background(color = Color.Black.copy(alpha = 0.2f)),
        enabled = enabled,
        onClick = {
            onClick?.invoke()
        }
    ) {
        Icon(
            modifier = Modifier.size(max(24.0, sizeDp * 0.5).dp),
            painter = painterResource(id = resId),
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary,
        )
    }
}

@Composable
fun SeekerView(
    modifier: Modifier = Modifier,
    onEvent: OnPlayerEvent,
    onSingleTap: () -> Unit
) {
//    println("TEST: SeekerView composing")
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .customPointerInput(
                    onTap = onSingleTap,
                    onDoubleTap = {
                        onEvent.invoke(PlayerEvent.NextVideo)
                    })
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .customPointerInput(
                    onTap = onSingleTap,
                    onDoubleTap = {
                        onEvent.invoke(PlayerEvent.PreVideo)
                    })
        )
    }
}


private fun Modifier.customPointerInput(
    onTap: (() -> Unit)? = null,
    onDoubleTap: (() -> Unit)? = null
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    indication(
        interactionSource = interactionSource,
        indication = rememberRipple(bounded = false)
    ).pointerInput(Unit) {
        detectTapGestures(
            onTap = onTap?.let { c -> { c() } },
            onDoubleTap = {
                onDoubleTap?.invoke()
                val press = PressInteraction.Press(it)
                interactionSource.tryEmit(press)
                interactionSource.tryEmit(PressInteraction.Release(press))
            }
        )
    }
}