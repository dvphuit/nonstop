package dvp.lib.corebrowser.composer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun BrowserTheme(content: @Composable () -> Unit) {
  val colorPalette = if (isSystemInDarkTheme()) {
    darkThemeColors
  } else {
    lightThemeColors
  }

  MaterialTheme(
    colors = colorPalette,
    content = content,
  )
}
