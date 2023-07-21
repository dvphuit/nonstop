package dvp.lib.browser

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import dvp.lib.browser.ui.MainViewModel
import dvp.lib.corebrowser.composer.ui.theme.BrowserRoundedShape
import kotlin.math.min

private var showSearch: Boolean by mutableStateOf(false)

@OptIn(ExperimentalMotionApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BrowserUI() {
    val viewModel = MainViewModel(navigationApi = Controller.getInstance())
    val focusManager = LocalFocusManager.current
    BackHandler(onBack = {
        println("TEST: back pressed")
        focusManager.clearFocus()
    })
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.home_scene)
            .readBytes()
            .decodeToString()
    }
    val searchBarScene = remember {
        context.resources
            .openRawResource(R.raw.search_bar_scene)
            .readBytes()
            .decodeToString()
    }

    val keyboardState = remember { KeyBoardState() }
    keyboardState.listenState {
        if (it == KeyBoardState.State.Closing) {
            focusManager.clearFocus()
        }
    }

    Scaffold {
        val progress = min(1f, (keyboardState.height.toFloat() / keyboardState.maxHeight))
        val modifier = Modifier
        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .imePadding()
        ) {
            BrowserHome()
            BrowserRecent()
            SearchBar(searchBarScene, progress)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BrowserRecent() {
    Column(
        modifier = Modifier
            .layoutId("browser_recent")
            .verticalScroll(rememberScrollState())
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        List(30) {
            ListItem {
                Text(text = "Item $it")
            }
        }
    }
}

@Composable
fun BrowserHome() {
    val modifier = Modifier
    Column(
        modifier = Modifier
            .layoutId("browser_home")
            .background(Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = modifier
                .width(200.dp)
                .height(50.dp)
                .background(Color.Red)
        )
        Box(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Blue)
        )
    }
}

@OptIn(ExperimentalMotionApi::class, ExperimentalMaterialApi::class)
@Composable
fun SearchBar(
    motionScene: String,
    progress: Float
) {
    val focusRequester = remember { FocusRequester() }
    var textFieldValueState by remember { mutableStateOf(TextFieldValue("")) }

    val modifier = Modifier
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = modifier
            .layoutId("search_bar")
//            .imePadding()
    ) {
        BasicTextField(
            singleLine = true,
            value = textFieldValueState,
            onValueChange = {
                textFieldValueState = it
            },
            decorationBox = { innerTextField ->
                Box(
                    modifier = modifier
                        .background(
                            color = Color.Gray,
                            shape = BrowserRoundedShape
                        )
                        .padding(vertical = 10.dp, horizontal = 16.dp)
                ) {
                    innerTextField()
                }
            },
            modifier = modifier
                .layoutId("text_field")
                .onFocusChanged {
                    showSearch = it.isFocused
                }
                .focusRequester(focusRequester),
        )
        Box(
            modifier = modifier
                .background(
                    color = Color.Red,
                    shape = BrowserRoundedShape
                )
                .size(40.dp)
                .layoutId("center")
        )
        Box(
            modifier = modifier
                .background(
                    color = Color.Blue,
                    shape = BrowserRoundedShape
                )
                .size(40.dp)
                .layoutId("menu")
        )
    }
}

