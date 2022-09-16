package dvp.lib.corebrowser.composer.ui

import androidx.lifecycle.ViewModel
import dvp.lib.corebrowser.composer.ui.widgets.bottombar.BrowserBottomBarState
import dvp.lib.corebrowser.composer.ui.widgets.progress.BrowserProgressState
import dvp.lib.corebrowser.composer.ui.widgets.toolbar.BrowserToolbarState
import dvp.lib.corebrowser.composer.ui.widgets.toolbar.DisplayUrl
import dvp.lib.corebrowser.features.BrowserDelegate
import dvp.lib.corebrowser.features.navigation.BrowserNavigationApi
import dvp.lib.corebrowser.features.navigation.BrowserNavigationListener
import dvp.lib.corebrowser.features.utils.URLUtils
import dvp.lib.corebrowser.features.utils.URLUtilsImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ComposeBrowserViewModel(
  private val navigationApi: BrowserNavigationApi,
  private val urlUtils: URLUtils = URLUtilsImpl(),
  initialToolbarState: BrowserToolbarState = BrowserToolbarState(),
  initialProgressState: BrowserProgressState = BrowserProgressState(),
  initialBottomBarState: BrowserBottomBarState = BrowserBottomBarState(),
) : ViewModel(), BrowserNavigationListener {

  private val _toolbarViewState = MutableStateFlow(initialToolbarState)
  val toolbarViewState: StateFlow<BrowserToolbarState> = _toolbarViewState.asStateFlow()

  private val _progressViewState = MutableStateFlow(initialProgressState)
  val progressViewState: StateFlow<BrowserProgressState> = _progressViewState.asStateFlow()

  private val _bottomBarViewState = MutableStateFlow(initialBottomBarState)
  val bottomBarViewState: StateFlow<BrowserBottomBarState> = _bottomBarViewState.asStateFlow()

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

  fun setDelegate(browserDelegate: BrowserDelegate) {
    navigationApi.setBrowserDelegate(browserDelegate)
  }

  fun onCancelClicked() {
    _toolbarViewState.update { it.copy(isEditMode = false) }
  }

  fun onBackPressed() {
    _toolbarViewState.update { it.copy(isEditMode = false) }
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
    val isValidUrl = urlUtils.isValidUrl(potentialUrl)
    val (simpleUrl, isSafeUrl) = parseUrl(potentialUrl)

    val displayText = when {
      isValidUrl -> simpleUrl
      else -> potentialUrl
    }
    _toolbarViewState.update {
      it.copy(
        showPrivacyIcon = isValidUrl,
        showHint = potentialUrl.isEmpty(),
        isEditMode = false,
        displayUrl = DisplayUrl(displayText, isSafeUrl),
        currentPageUrl = if (isValidUrl) potentialUrl else ""
      )
    }
  }

  private fun parseUrl(url: String): Pair<String, Boolean> {
    return urlUtils.getDisplayUrl(url) to url.contains("https")
  }
}
