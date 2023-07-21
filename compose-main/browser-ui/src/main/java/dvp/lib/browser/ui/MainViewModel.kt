package dvp.lib.browser.ui

import androidx.lifecycle.ViewModel
import dvp.lib.browser.ui.widgets.topbar.DisplayUrl
import dvp.lib.browser.ui.widgets.topbar.State as BottomBarState
import dvp.lib.browser.ui.widgets.progress.State as ProgressBarState
import dvp.lib.browser.ui.widgets.bottombar.ToolbarState
import dvp.lib.corebrowser.features.BrowserDelegate
import dvp.lib.corebrowser.features.navigation.IAction
import dvp.lib.corebrowser.features.navigation.INavigationListener
import dvp.lib.corebrowser.features.utils.UrlUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class MainViewModel(
    private val navigationApi: IAction,
    initialToolbarState: ToolbarState = ToolbarState(),
    initialProgressState: ProgressBarState = ProgressBarState(),
    initialBottomBarState: BottomBarState = BottomBarState(),
) : ViewModel(), INavigationListener {

    private val _toolbarViewState = MutableStateFlow(initialToolbarState)
    val toolbarViewState: StateFlow<ToolbarState> = _toolbarViewState.asStateFlow()

    private val _progressViewState = MutableStateFlow(initialProgressState)
    val progressViewState: StateFlow<dvp.lib.browser.ui.widgets.progress.State> =
        _progressViewState.asStateFlow()

    private val _bottomBarViewState = MutableStateFlow(initialBottomBarState)
    val bottomBarViewState: StateFlow<BottomBarState> = _bottomBarViewState.asStateFlow()

    init {
        updateDisplayUrl("")
        navigationApi.addNavigationListener(this)
    }

    override fun onPageProgressChanged(newProgress: Int) {
        _progressViewState.update {
            it.copy(
                progressPercentage = newProgress.toFloat() / 100,
                showProgress = newProgress < 100
            )
        }
    }

    override fun onNavigationStateUpdated(canGoBack: Boolean, canGoForward: Boolean) {
        _bottomBarViewState.update {
            it.copy(
                canGoBack = canGoBack,
                canGoForward = canGoForward,
            )
        }
    }

    override fun onPageStarted(url: String) = updateDisplayUrl(url)

    fun onQueryChange(newQuery: String) {
        _toolbarViewState.update {
            it.copy(
                query = newQuery,
                showClearButton = newQuery.isNotEmpty()
            )
        }
    }

    fun onSubmit(url: String) {
        updateDisplayUrl(url)
        navigationApi.loadUrl(url)
    }

    fun onRefreshClicked() = navigationApi.reload()

    fun onDisplayModeClicked() {
        val currentPageUrl = toolbarViewState.value.currentPageUrl
        val newQuery = currentPageUrl.ifEmpty { "" }

        _toolbarViewState.update {
            it.copy(
                isEditMode = true,
                query = newQuery,
                showClearButton = newQuery.isNotEmpty()
            )
        }
    }

    fun onGoBackClicked() = navigationApi.goBack()
    fun onGoForwardClicked() = navigationApi.goForward()

    fun onShareClicked() = navigationApi.share()
    fun onCloseClicked() = navigationApi.close()

    fun setDelegate(browserDelegate: BrowserDelegate) {
        navigationApi.setBrowserDelegate(browserDelegate)
    }

    fun onCancelClicked() {
        _toolbarViewState.update { it.copy(isEditMode = false) }
    }

    fun onBackPressed() {
        _toolbarViewState.update { it.copy(isEditMode = false) }
    }

    fun onClosed() {

    }

    fun onClearButtonClicked() {
        _toolbarViewState.update {
            it.copy(
                query = "",
                showClearButton = false
            )
        }
    }

    private fun updateDisplayUrl(potentialUrl: String) {
        val isValidUrl = UrlUtils.isValidUrl(potentialUrl)
        val (simpleUrl, isSafeUrl) = parseUrl(potentialUrl)

        val displayText = when {
            isValidUrl -> simpleUrl
            else -> potentialUrl
        }
        _toolbarViewState.update {
            it.copy(
                showHint = potentialUrl.isEmpty(),
                isEditMode = false,
                currentPageUrl = if (isValidUrl) potentialUrl else ""
            )
        }
        _bottomBarViewState.update {
            it.copy(
                displayUrl = DisplayUrl(displayText, isSafeUrl)
            )
        }
    }

    private fun parseUrl(url: String): Pair<String, Boolean> {
        return UrlUtils.getDisplayUrl(url) to url.contains("https")
    }
}
