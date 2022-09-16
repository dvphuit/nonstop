package dvp.lib.browser.ui.widgets.bottombar

import androidx.annotation.DrawableRes
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import dvp.lib.corebrowser.R
import dvp.lib.corebrowser.composer.ui.theme.getSystemThemeIcon

@Composable
internal fun BottomBar(
  state: State,
  onGoForwardButtonClicked: () -> Unit = {},
  onGoBackButtonClicked: () -> Unit = {},
) {
  BottomAppBar(
    contentColor = MaterialTheme.colors.primaryVariant,
    backgroundColor = MaterialTheme.colors.background
  ) {
    BottomBarButton(
      id = getSystemThemeIcon(
        lightIcon = R.drawable.light_back,
        darkIcon = R.drawable.dark_back
      ),
      enabled = state.canGoBack,
      onButtonClicked = onGoBackButtonClicked,
    )
    BottomBarButton(
      id = getSystemThemeIcon(
        lightIcon = R.drawable.light_forward,
        darkIcon = R.drawable.dark_forward,
      ),
      enabled = state.canGoForward,
      onButtonClicked = onGoForwardButtonClicked,
    )
  }
}

@Composable
internal fun BottomBarButton(
  modifier: Modifier = Modifier,
  @DrawableRes id: Int,
  description: String = "",
  enabled: Boolean = true,
  onButtonClicked: () -> Unit = {},
) {

  IconButton(
    modifier = modifier,
    onClick = onButtonClicked,
    enabled = enabled
  ) {
    val color = MaterialTheme.colors.onBackground
    Icon(
      painter = painterResource(id = id),
      contentDescription = description,
      tint = if (enabled) {
        color
      } else {
        color.copy(alpha = 0.6f)
      },
    )
  }
}
