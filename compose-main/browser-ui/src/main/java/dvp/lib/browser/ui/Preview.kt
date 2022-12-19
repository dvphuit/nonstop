package dvp.lib.browser.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dvp.lib.browser.BrowserUI
import dvp.lib.corebrowser.composer.ui.theme.BrowserTheme

@Composable
@Preview(
  name = "LIGHT",
)
fun Browser() {
  ThemedBrowser()
}

@Composable
@Preview(
  name = "DARK",
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
fun BrowserDark() {
  ThemedBrowser()
}

@Composable
private fun ThemedBrowser() {
  BrowserTheme {
    BrowserUI()
  }
}
