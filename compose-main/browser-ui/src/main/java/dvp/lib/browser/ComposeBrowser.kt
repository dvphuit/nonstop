package dvp.lib.browser.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import dvp.lib.browser.Controller
import dvp.lib.browser.ui.widgets.ComposableWebView
import dvp.lib.browser.ui.widgets.bottombar.BottomBar
import dvp.lib.browser.ui.widgets.progress.ProgressBar
import dvp.lib.browser.ui.widgets.toolbar.Toolbar

@Composable
fun ComposeBrowser(
    onBrowserStarted: () -> Unit,
) {
    val viewModel =  MainViewModel(navigationApi = Controller.getInstance())
    BackHandler(onBack = { /* NOOP: disables closing browser with back press*/ })

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
        Column(modifier = Modifier.fillMaxSize().padding(it.calculateBottomPadding())) {
            ProgressBar(viewModel.progressViewState.collectAsState().value)
            ComposableWebView(
                onReady = onBrowserStarted,
                onBrowserDelegateCreated = viewModel::setDelegate
            )
        }
    }
}
