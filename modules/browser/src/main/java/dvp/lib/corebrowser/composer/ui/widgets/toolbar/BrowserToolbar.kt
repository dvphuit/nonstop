package dvp.lib.corebrowser.composer.ui.widgets.toolbar

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dvp.lib.corebrowser.R

/**
 * A toolbar for browsers.
 *
 * The toolbar can switch between two modes: display and edit. The display mode displays the current
 * URL and controls for navigation. In edit mode the current URL can be edited. Those two modes are
 * implemented by the [BrowserDisplayToolbar] and [BrowserEditToolbar] composables.
 *
 * @param state The state representation of the ui
 * @param onQueryChange Function to get executed when the user changes the query in "edit" mode.
 * @param onClearButtonClicked Function to get executed when the user clicks on the clear
 * button in "edit" mode.
 * @param onTextSubmitted Function to get executed when the user has finished editing the
 * URL and wants to load the entered text.
 * @param onRefreshClicked Function to get executed when the user clicks on the refresh
 * button in "display" mode.
 * @param onDisplayToolbarClick Function to get executed when the user clicks on the URL in
 * "display" mode.
 * @param onCloseButtonClicked Function to get executed when the user clicks on the close button.
 */
@Composable
@Suppress("LongParameterList")
fun BrowserToolbar(
  state: BrowserToolbarState,
  onQueryChange: (String) -> Unit = {},
  onTextSubmitted: (String) -> Unit = {},
  onDisplayToolbarClick: () -> Unit = {},
  onRefreshClicked: () -> Unit = {},
  onClearButtonClicked: () -> Unit = {},
  onCloseButtonClicked: () -> Unit = {},
  onBackPressed: () -> Unit = {},
  onCancelClicked: () -> Unit = {},
) {

  val hint = stringResource(id = R.string.browser_toolbar_hint)
  Crossfade(targetState = state.isEditMode, animationSpec = tween(500)) { isEditMode ->
    if (isEditMode) {
      BrowserEditToolbar(
        query = state.query,
        hint = hint,
        showClearButton = state.showClearButton,
        onTextSubmitted = onTextSubmitted,
        onQueryChange = onQueryChange,
        onClearButtonClicked = onClearButtonClicked,
        onBackPressed = onBackPressed,
        onCancelClicked = onCancelClicked,
      )
    } else {
      BrowserDisplayToolbar(
        hint = hint,
        showHint = state.showHint,
        displayText = state.displayUrl.text,
        isSafeUrl = state.displayUrl.isSafe,
        showPrivacyIcon = state.showPrivacyIcon,
        onUrlClicked = onDisplayToolbarClick,
        onRefreshClicked = onRefreshClicked,
        onCloseButtonClicked = onCloseButtonClicked,
      )
    }
  }
}
