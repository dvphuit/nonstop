package dvp.ui.youtube.view.common

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun DoubleTapButton(modifier: Modifier, onInvoked: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .indication(interactionSource, rememberRipple(color = Color.Red, bounded = false))
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        onInvoked.invoke()
                        val press = PressInteraction.Press(it)
                        interactionSource.tryEmit(press)
                        interactionSource.tryEmit(PressInteraction.Release(press))
                    }
                )
            }
    )
}