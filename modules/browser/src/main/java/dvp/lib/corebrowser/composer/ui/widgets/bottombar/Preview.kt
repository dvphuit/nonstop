package dvp.lib.corebrowser.composer.ui.widgets.bottombar

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dvp.lib.corebrowser.composer.ui.theme.BrowserTheme

@Preview(
  name = "LIGHT - FORWARD ENABLED",
)
@Composable
fun BrowserBottomBarPreview() = BrowserTheme {
  BrowserBottomBar(
    BrowserBottomBarState(
      canGoForward = true,
    )
  )
}

@Preview(
  name = "DARK - FORWARD ENABLED",
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun BrowserBottomBarPreview2() = BrowserTheme {
  BrowserBottomBar(
    BrowserBottomBarState(canGoForward = true)
  )
}

@Preview(
  name = "LIGHT - BACK ENABLED",
)
@Composable
fun BrowserBottomBarPreview3() = BrowserTheme {
  BrowserBottomBar(
    BrowserBottomBarState(canGoBack = true)
  )
}

@Preview(
  name = "DARK - BACK ENABLED",
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun BrowserBottomBarPreview4() = BrowserTheme {
  BrowserBottomBar(
    BrowserBottomBarState(canGoBack = true)
  )
}
