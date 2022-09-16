package dvp.lib.browser.ui.widgets.toolbar

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dvp.lib.corebrowser.R
import dvp.lib.corebrowser.composer.ui.theme.BrowserRoundedShape
import dvp.lib.corebrowser.composer.ui.theme.Green

@Composable
internal fun EditToolbar(
  query: String,
  hint: String,
  showClearButton: Boolean,
  onTextSubmitted: (String) -> Unit = {},
  onQueryChange: (String) -> Unit = {},
  onClearButtonClicked: () -> Unit = {},
  onBackPressed: () -> Unit = {},
  onCancelClicked: () -> Unit,
) {
  val backgroundColor = MaterialTheme.colors.surface
  val foregroundColor = contentColorFor(backgroundColor)
  val focusRequester = remember { FocusRequester() }

  LaunchedEffect(Unit) { focusRequester.requestFocus() }
  BackHandler(onBack = onBackPressed)

  val textFieldValueState = remember {
    mutableStateOf(
      TextFieldValue(
        text = query,
        selection = TextRange(0, query.length)
      )
    )
  }
  val customTextSelectionColors = TextSelectionColors(
    handleColor = Green,
    backgroundColor = Green.copy(alpha = 0.4f)
  )

  val modifier = Modifier
  Row(
    modifier
      .fillMaxWidth()
      .background(MaterialTheme.colors.background)
      .padding(horizontal = 8.dp, vertical = 6.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
      TextField(
        value = textFieldValueState.value,
        onValueChange = { newTextFieldValue ->
          textFieldValueState.value = newTextFieldValue
          onQueryChange(textFieldValueState.value.text)
        },
        shape = BrowserRoundedShape,
        placeholder = { Text(hint) },
        colors = TextFieldDefaults.textFieldColors(
          textColor = foregroundColor,
          backgroundColor = MaterialTheme.colors.primaryVariant,
          unfocusedIndicatorColor = Color.Transparent,
          focusedIndicatorColor = Color.Transparent,
          cursorColor = Green,
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Uri,
          imeAction = ImeAction.Go
        ),
        keyboardActions = KeyboardActions(
          onGo = { onTextSubmitted(query) }
        ),
        modifier = modifier
          .height(50.dp)
          .focusRequester(focusRequester)
          .weight(0.8f),
        trailingIcon = {
          if (showClearButton) {
            ClearButton(onButtonClicked = {
              textFieldValueState.value = textFieldValueState.value.copy(text = "")
              onClearButtonClicked()
            })
          }
        }
      )
    }
    CancelButton(
      modifier = modifier.weight(0.2f),
      onCancelClicked = onCancelClicked
    )
  }
}

@Composable
internal fun ClearButton(onButtonClicked: () -> Unit = {}) {
  IconButton(onClick = { onButtonClicked() }) {
    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
  }
}

@Composable
internal fun CancelButton(modifier: Modifier, onCancelClicked: () -> Unit = {}) {
  TextButton(
    modifier = modifier,
    onClick = onCancelClicked
  ) {
    Text(
      text = stringResource(R.string.browser_toolbar_cancel),
      fontWeight = FontWeight.Bold,
      fontSize = 15.sp,
      color = MaterialTheme.colors.onBackground
    )
  }
}
