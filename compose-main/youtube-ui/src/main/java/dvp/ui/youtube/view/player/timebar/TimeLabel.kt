package dvp.ui.youtube.view.player.timebar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TimeLabel(
    visible: Boolean,
    modifier: Modifier = Modifier,
    currentMs: Long,
    durationMs: Long,
    color: Color = Color(0xFFFFFFFF),
) {
    if (!visible) return

    Text("20:00")
}