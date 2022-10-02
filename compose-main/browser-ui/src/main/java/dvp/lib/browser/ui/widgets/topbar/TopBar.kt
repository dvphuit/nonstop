package dvp.lib.browser.ui.widgets.topbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dvp.lib.corebrowser.R
import dvp.lib.corebrowser.composer.ui.theme.getSystemThemeIcon

@Composable
internal fun TopBar(
  state: State,
  onCloseClicked: () -> Unit = {},
  onSharedClicked: () -> Unit = {},
) {
  val modifier = Modifier
  TopAppBar(
    modifier = modifier.height(30.dp),
    contentColor = MaterialTheme.colors.primaryVariant,
    backgroundColor = MaterialTheme.colors.background
  ) {
    BottomBarButton(
      id = getSystemThemeIcon(
        lightIcon = R.drawable.light_back,
        darkIcon = R.drawable.dark_back
      ),
      enabled = state.canGoBack,
      onButtonClicked = onSharedClicked,
    )
    Row(
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier
        .weight(0.8f)
        .align(Alignment.CenterVertically)
    ) {
      if (state.displayUrl.isSafe) {
        PrivacyIcon(state.displayUrl.isSafe, modifier.align(Alignment.CenterVertically))
      }
      Text(
        text = state.displayUrl.text,
        maxLines = 1,
        color = Color.Black,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = modifier
          .align(Alignment.CenterVertically)
          .wrapContentWidth()
      )
    }
    BottomBarButton(
      id = getSystemThemeIcon(
        lightIcon = R.drawable.light_forward,
        darkIcon = R.drawable.dark_forward,
      ),
      enabled = state.canGoForward,
      onButtonClicked = onCloseClicked,
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

@Composable
private fun PrivacyIcon(isSafeUrl: Boolean, modifier: Modifier) {
  val description = if (isSafeUrl) "https" else "http"
  Spacer(modifier = modifier.padding(horizontal = 2.dp))

  Icon(
    painterResource(id = getSystemThemeIcon(R.drawable.dark_secure, R.drawable.light_secure)),
    modifier = modifier,
    contentDescription = description
  )
  Spacer(modifier = modifier.padding(horizontal = 2.dp))
}
