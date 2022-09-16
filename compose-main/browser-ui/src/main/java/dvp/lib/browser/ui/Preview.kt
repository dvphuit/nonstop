package dvp.lib.browser.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dvp.lib.browser.Controller
import dvp.lib.corebrowser.composer.ui.theme.BrowserTheme
import dvp.lib.corebrowser.features.navigation.ComposeBrowserNavigationApi

@Composable
@Preview(
  name = "LIGHT - Klarna Browser",
)
fun Browser() {
  ThemedBrowser()
}

@Composable
@Preview(
  name = "DARK - KlarnaBrowser",
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
fun BrowserDark() {
  ThemedBrowser()
}

@Composable
private fun ThemedBrowser() {
  BrowserTheme {
    ComposeBrowser(
      browserViewModel = MainViewModel(Controller(ComposeBrowserNavigationApi())),
      onBrowserStarted = {},
    )
  }
}
