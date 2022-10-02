package dvp.lib.browser

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import dvp.lib.browser.ui.MainViewModel
import dvp.lib.browser.ui.widgets.ComposableWebView
import dvp.lib.browser.ui.widgets.bottombar.BottomBar
import dvp.lib.browser.ui.widgets.progress.ProgressBar
import dvp.lib.browser.ui.widgets.topbar.TopBar
import dvp.lib.corebrowser.composer.ui.theme.BrowserRoundedShape
import kotlin.math.min

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BrowserUI() {
    val viewModel = MainViewModel(navigationApi = Controller.getInstance())
//    BackHandler(onBack = {
//        Log.d("BrowserUI", "on back pressed")
//    })

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            TopBar(
                viewModel.bottomBarViewState.collectAsState().value,
                onCloseClicked = viewModel::onCloseClicked,
                onSharedClicked = viewModel::onShareClicked,
            )
        },
        bottomBar = {
            BottomBar(
                viewModel.toolbarViewState.collectAsState().value,
                onQueryChange = viewModel::onQueryChange,
                onTextSubmitted = viewModel::onSubmit,
                onDisplayToolbarClick = viewModel::onDisplayModeClicked,
                onRefreshClicked = viewModel::onRefreshClicked,
                onClearButtonClicked = viewModel::onClearButtonClicked,
                onCancelClicked = viewModel::onCancelClicked,
                onBackPressed = viewModel::onBackPressed,
                onCloseButtonClicked = viewModel::onClosed,
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ProgressBar(viewModel.progressViewState.collectAsState().value)
            ComposableWebView(
                onReady = {
                    Log.d("BrowserUI", "on ready")
                },
                onBrowserDelegateCreated = viewModel::setDelegate
            )
        }
    }
}

private var showSearch: Boolean by mutableStateOf(false)

@Composable
fun BrowserMain() {

    val focusManager = LocalFocusManager.current
    val modifier = Modifier
    BackHandler(onBack = {
        println("TEST: back pressed")
        focusManager.clearFocus()
    })

    Column(
        modifier = modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(Color.Cyan)
    ) {

        Crossfade(
            modifier = modifier.weight(1f),
            targetState = showSearch,
            animationSpec = tween(durationMillis = 300)
        ) { state ->
            if (state) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .clickable {
                            focusManager.clearFocus()
                        }
                        .background(Color.Yellow),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                }
            } else {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color.Black),
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
        }
        SearchBar(focusManager)
    }
}


@OptIn(ExperimentalMotionApi::class)
@Composable
fun SearchBar(focusManager: FocusManager) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
    val focusRequester = remember { FocusRequester() }
    var textFieldValueState by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardState = remember { KeyBoardState() }
    keyboardState.listenState {
        println("TEST: state = $it")
        if (it == KeyBoardState.State.Closing) {
            focusManager.clearFocus()
        }
    }

    val modifier = Modifier
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = min(1f, (keyboardState.height.toFloat() / keyboardState.maxHeight)),
        modifier = modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .imePadding()
            .padding(vertical = 8.dp)
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
                        .fillMaxWidth()
                        .background(
                            color = Color.Magenta,
                            shape = BrowserRoundedShape
                        )
                        .padding(vertical = 12.dp, horizontal = 16.dp)
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
                .focusRequester(focusRequester)
                .background(
                    color = Color.Red,
                    shape = BrowserRoundedShape
                )
                .layoutId("center")
        )
        Box(
            modifier = modifier
                .focusRequester(focusRequester)
                .background(
                    color = Color.Blue,
                    shape = BrowserRoundedShape
                )
                .layoutId("menu")
        )
    }
}


class KeyBoardState {
    var height by mutableStateOf(0)
        private set
    var maxHeight by mutableStateOf(1000)
        private set

    private var lastState = State.Closed
    private var lastHeight = 0

    @SuppressLint("ComposableNaming")
    @JvmName("setHeight1")
    @Composable
    fun listenState(listener: (State) -> Unit) {
        this.height = WindowInsets.ime.getBottom(LocalDensity.current)
        if (height > maxHeight) {
            maxHeight = height
        }

        val mState = when {
            height == 0 -> State.Closed
            height == maxHeight -> State.Opened
            height > lastHeight -> State.Opening
            height < lastHeight -> State.Closing
            else -> State.Closed
        }
        lastHeight = height
        if (lastState != mState) {
            listener.invoke(mState)
            lastState = mState
        }

    }

    enum class State {
        Closed,
        Closing,
        Opened,
        Opening,
    }
}
