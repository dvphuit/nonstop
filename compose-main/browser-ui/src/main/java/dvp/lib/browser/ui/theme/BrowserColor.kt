package dvp.lib.corebrowser.composer.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

internal val Green = Color(0xFF80918F)
internal val Pink = Color(0xFFF9B2C6)
internal val Gray = Color(0xFFD7D6D2)

internal val lightThemeColors = lightColors(
  primary = Pink,
  primaryVariant = Gray,
  background = Color(0xFFE5E5E5),
  surface = Color.White,
  onPrimary = Color(0xFF17120F),
  onBackground = Color(0xFF17120F),
  onSurface = Color(0xFF17120F),
)

internal val darkThemeColors = darkColors(
  primary = Color(0xFFF9B2C6),
  primaryVariant = Color(0xFF323130),
  background = Color(0xFF111111),
  surface = Color.Black,
  onPrimary = Color(0xFFFAFAF8),
  onBackground = Color(0xFFFAFAF8),
  onSurface = Color(0xFFFAFAF8),
)
