package dvp.ui.youtube.view.player.timebar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TimeBarScrubber(
    enabled: Boolean,
    scrubbing: Boolean,
    modifier: Modifier = Modifier,
    enabledSize: Dp = 12.dp,
    disabledSize: Dp = 0.dp,
    draggedSize: Dp = 16.dp,
    color: Color = Color(0xFF1FB344),
    shape: Shape = CircleShape
) {
    val size = when {
        !enabled -> disabledSize
        scrubbing -> draggedSize
        else -> enabledSize
    }
    Spacer(
        modifier = modifier
            .size(size)
            .background(color, shape)
    )
}