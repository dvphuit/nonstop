package dvp.lib.browser

//import androidx.activity.compose.BackHandler
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import dvp.lib.browser.ui.MainViewModel
import dvp.lib.browser.ui.widgets.ComposableWebView
import dvp.lib.browser.ui.widgets.bottombar.BottomBar
import dvp.lib.browser.ui.widgets.progress.ProgressBar
import dvp.lib.browser.ui.widgets.toolbar.Toolbar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ComposeBrowser(
    onBrowserStarted: () -> Unit,
) {
    val viewModel = MainViewModel(navigationApi = Controller.getInstance())
//    BackHandler(onBack = { /* NOOP: disables closing browser with back press*/ })
    Scaffold(
        topBar = {
            Toolbar(
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
        bottomBar = {
            BottomBar(
                viewModel.bottomBarViewState.collectAsState().value,
                onGoForwardButtonClicked = viewModel::onGoForwardClicked,
                onGoBackButtonClicked = viewModel::onGoBackClicked,
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ProgressBar(viewModel.progressViewState.collectAsState().value)
            ComposableWebView(
                onReady = {
//                          viewModel.onSubmit("tinhte.vn")
                },
                onBrowserDelegateCreated = viewModel::setDelegate
            )
        }
    }
}

@Composable
fun MyWebView() {
    val viewModel = MainViewModel(navigationApi = Controller.getInstance())
    ComposableWebView(
        onReady = {
            viewModel.onSubmit("tinhte.vn")
        },
        onBrowserDelegateCreated = viewModel::setDelegate
    )
}
