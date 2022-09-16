package dvp.lib.browser.ui.widgets.toolbar

internal data class ToolbarState(
  val isEditMode: Boolean = false,
  val query: String = "",
  val displayUrl: DisplayUrl = DisplayUrl("", true),
  val showHint: Boolean = true,
  val showClearButton: Boolean = false,
  val currentPageUrl: String = "",
  val showPrivacyIcon: Boolean = false,
)

internal data class DisplayUrl(
  val text: String,
  val isSafe: Boolean,
)
