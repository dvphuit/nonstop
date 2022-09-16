package dvp.lib.browser.ui.widgets.toolbar

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dvp.lib.corebrowser.R

@Composable
internal fun Toolbar(
  state: ToolbarState,
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
      EditToolbar(
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
      DisplayToolbar(
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
