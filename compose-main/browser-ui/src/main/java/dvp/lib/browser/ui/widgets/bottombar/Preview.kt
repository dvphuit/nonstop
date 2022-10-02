package dvp.lib.browser.ui.widgets.bottombar

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dvp.lib.corebrowser.composer.ui.theme.BrowserTheme

@Composable
@Preview(
  name = "LIGHT - EDIT MODE - NO QUERY",
)
fun BrowserToolbarEditModeNoUrl() = BrowserTheme {
  BottomBar(
    state = ToolbarState(
      isEditMode = true,
    ),
    onCancelClicked = {}
  )
}

@Composable
@Preview(
  name = "DARK - EDIT MODE - NO QUERY",
  uiMode = UI_MODE_NIGHT_YES
)
fun BrowserToolbarEditModeNoUrlDark() = BrowserTheme {
  BottomBar(
    state = ToolbarState(
      isEditMode = true,
    ),
    onCancelClicked = {}
  )
}

@Composable
@Preview(
  name = "LIGHT - EDIT MODE - WITH QUERY",
)
fun BrowserToolbarEditModeUrl() = BrowserTheme {
  BottomBar(
    state = ToolbarState(
      isEditMode = true,
      query = "https://bestbuy.com/",
      showClearButton = true,
    ),
    onCancelClicked = {}
  )
}

@Composable
@Preview(
  name = "DARK - EDIT MODE - WITH QUERY",
  uiMode = UI_MODE_NIGHT_YES
)
fun BrowserToolbarEditModeDark() = BrowserTheme {
  BottomBar(
    state = ToolbarState(
      isEditMode = true,
      query = "https://bestbuy.com/",
      showClearButton = true,
    ),
    onCancelClicked = {}
  )
}

@Composable
@Preview(
  name = "LIGHT - DISPLAY MODE - NO URL",
)
fun BrowserToolbarDisplayModeNoUrl() = BrowserTheme {
  BottomBar(
    state = ToolbarState(
      isEditMode = false,
    ),
    onCancelClicked = {}
  )
}

@Composable
@Preview(
  name = "DARK - DISPLAY MODE - NO URL",
  uiMode = UI_MODE_NIGHT_YES
)
fun BrowserToolbarDisplayModeNoUrlDark() = BrowserTheme {
  BottomBar(
    state = ToolbarState(
      isEditMode = false,
    ),
    onCancelClicked = {}
  )
}

@Composable
@Preview(
  name = "LIGHT - DISPLAY MODE - URL"
)
fun BrowserToolbarDisplayModeWithSafeUrl() = BrowserTheme {
  BottomBar(
    state = ToolbarState(
      isEditMode = false,
      showHint = false,
    ),
    onCancelClicked = {}
  )
}

@Composable
@Preview(
  name = "DARK - DISPLAY MODE - URL",
  uiMode = UI_MODE_NIGHT_YES,
)
fun BrowserToolbarDisplayModeWithSafeUrlDark() = BrowserTheme {
  BottomBar(
    state = ToolbarState(
      isEditMode = false,
      currentPageUrl = "https://bestbuy.com/",
      showHint = false,
    ),
    onCancelClicked = {}
  )
}
