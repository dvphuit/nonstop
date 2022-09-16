package dvp.lib.corebrowser.composer.ui.widgets.progress

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BrowserProgress(state: BrowserProgressState) {
  Crossfade(
    targetState = state.showProgress,
    animationSpec = tween(500)
  ) { showProgress ->
    if (showProgress) {
      LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth().height(1.dp),
        progress = state.progressPercentage,
        color = MaterialTheme.colors.onBackground
      )
    }
  }
}

data class BrowserProgressState(
  val showProgress: Boolean = false,
  val progressPercentage: Float = 0.0F,
)
