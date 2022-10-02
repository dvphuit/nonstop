package dvp.lib.browser.ui.widgets.bottombar

//import androidx.activity.compose.BackHandler
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
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
                text = query, selection = TextRange(0, query.length)
            )
        )
    }
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Green, backgroundColor = Green.copy(alpha = 0.4f)
    )

    val modifier = Modifier
    Column() {
        Divider(color = Color.Red)
        Row(
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                BasicTextField(
                    singleLine = true,
                    value = textFieldValueState.value,
                    onValueChange = {
                        textFieldValueState.value = it
                        onQueryChange(it.text)
                    },
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier.padding(start = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(modifier = modifier.weight(1f)) {
                                innerTextField()
                                if (textFieldValueState.value.text.isEmpty()) {
                                    Text(
                                        text = hint,
                                        color = MaterialTheme.colors.onBackground
                                    )
                                }
                            }
                            if (showClearButton) {
                                ClearButton(onButtonClicked = {
                                    textFieldValueState.value =
                                        textFieldValueState.value.copy(text = "")
                                    onClearButtonClicked()
                                })
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Uri, imeAction = ImeAction.Go
                    ),
                    keyboardActions = KeyboardActions(onGo = { onTextSubmitted(query) }),
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(40.dp)
                        .padding(horizontal = 0.dp)
                        .background(
                            color = Color.LightGray,
                            shape = BrowserRoundedShape
                        )
                        .focusRequester(focusRequester),
                )

            }
            CancelButton(
                onCancelClicked = onCancelClicked
            )
        }
    }
}

@Composable
internal fun ClearButton(onButtonClicked: () -> Unit = {}) {
    IconButton(onClick = { onButtonClicked() }) {
        Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
    }
}

@Composable
internal fun CancelButton(onCancelClicked: () -> Unit = {}) {
    TextButton(
        onClick = onCancelClicked
    ) {
        Text(
            text = stringResource(R.string.browser_toolbar_cancel),
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            color = MaterialTheme.colors.onBackground
        )
    }
}
