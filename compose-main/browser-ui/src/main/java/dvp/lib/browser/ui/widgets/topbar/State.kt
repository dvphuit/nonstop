package dvp.lib.browser.ui.widgets.topbar


internal data class State(
    val canGoBack: Boolean = false,
    val canGoForward: Boolean = false,
    val displayUrl: DisplayUrl = DisplayUrl("", true)
)

internal data class DisplayUrl(
    val text: String,
    val isSafe: Boolean,
)
