package dvp.lib.corebrowser.composer.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import dvp.lib.corebrowser.composer.ComposeBrowserApi
import dvp.lib.corebrowser.composer.ui.widgets.ComposableWebView
import dvp.lib.corebrowser.composer.ui.widgets.bottombar.BrowserBottomBar
import dvp.lib.corebrowser.composer.ui.widgets.progress.BrowserProgress
import dvp.lib.corebrowser.composer.ui.widgets.toolbar.BrowserToolbar

@Composable
fun ComposeBrowser(
    browserViewModel: ComposeBrowserViewModel = ComposeBrowserViewModel(navigationApi = ComposeBrowserApi.getInstance()),
    onBrowserStarted: () -> Unit,
) {

    val activity = (LocalContext.current as? Activity)

    BackHandler(onBack = { /* NOOP: disables closing browser with back press*/ })

    Scaffold(
        topBar = {
            BrowserToolbar(
                browserViewModel.toolbarViewState.collectAsState().value,
                onQueryChange = browserViewModel::onQueryChange,
                onTextSubmitted = browserViewModel::onSubmit,
                onDisplayToolbarClick = browserViewModel::onDisplayModeClicked,
                onRefreshClicked = browserViewModel::onRefreshClicked,
                onClearButtonClicked = browserViewModel::onClearButtonClicked,
                onCancelClicked = browserViewModel::onCancelClicked,
                onBackPressed = browserViewModel::onBackPressed,
                onCloseButtonClicked = { activity?.finish() },
            )
        },
        bottomBar = {
            BrowserBottomBar(
                browserViewModel.bottomBarViewState.collectAsState().value,
                onGoForwardButtonClicked = browserViewModel::onGoForwardClicked,
                onGoBackButtonClicked = browserViewModel::onGoBackClicked,
            )
        },
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(it.calculateBottomPadding())) {
            BrowserProgress(browserViewModel.progressViewState.collectAsState().value)
            ComposableWebView(
                onReady = onBrowserStarted,
                onBrowserDelegateCreated = browserViewModel::setDelegate
            )
        }
    }
}
