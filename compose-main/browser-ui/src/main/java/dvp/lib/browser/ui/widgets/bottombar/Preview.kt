package dvp.lib.browser.ui.widgets.bottombar

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dvp.lib.corebrowser.composer.ui.theme.BrowserTheme

@Preview(
  name = "LIGHT - FORWARD ENABLED",
)
@Composable
fun BrowserBottomBarPreview() = BrowserTheme {
  BottomBar(
    State(
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
  BottomBar(
    State(canGoForward = true)
  )
}

@Preview(
  name = "LIGHT - BACK ENABLED",
)
@Composable
fun BrowserBottomBarPreview3() = BrowserTheme {
  BottomBar(
    State(canGoBack = true)
  )
}

@Preview(
  name = "DARK - BACK ENABLED",
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun BrowserBottomBarPreview4() = BrowserTheme {
  BottomBar(
    State(canGoBack = true)
  )
}
