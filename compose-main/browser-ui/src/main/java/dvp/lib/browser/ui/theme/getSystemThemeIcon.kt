package dvp.lib.corebrowser.composer.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
@DrawableRes
internal fun getSystemThemeIcon(@DrawableRes lightIcon: Int, @DrawableRes darkIcon: Int): Int {
  return if (isSystemInDarkTheme()) {
    darkIcon
  } else {
    lightIcon
  }
}
