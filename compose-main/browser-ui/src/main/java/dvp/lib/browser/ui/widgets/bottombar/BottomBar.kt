package dvp.lib.browser.ui.widgets.bottombar

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dvp.lib.browser.ui.Border
import dvp.lib.browser.ui.border
import dvp.lib.corebrowser.R

@Composable
internal fun BottomBar(
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
  Column {
    Divider(color = Color.LightGray)
    Crossfade(
      targetState = state.isEditMode, animationSpec = tween(500)) { isEditMode ->
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
          onUrlClicked = onDisplayToolbarClick,
          onRefreshClicked = onRefreshClicked,
          onCloseButtonClicked = onCloseButtonClicked,
        )
      }
    }
  }
}
