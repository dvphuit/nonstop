package dvp.lib.browser.ui

import androidx.compose.runtime.Composable
import dvp.lib.browser.BrowserUI
import dvp.lib.corebrowser.composer.ui.theme.BrowserTheme

@Composable
private fun ThemedBrowser() {
  BrowserTheme {
    BrowserUI()
  }
}
