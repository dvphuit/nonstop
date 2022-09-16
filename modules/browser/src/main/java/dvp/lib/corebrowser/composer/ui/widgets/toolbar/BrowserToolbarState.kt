package dvp.lib.corebrowser.composer.ui.widgets.toolbar

data class BrowserToolbarState(
  val isEditMode: Boolean = false,
  val query: String = "",
  val displayUrl: DisplayUrl = DisplayUrl("", true),
  val showHint: Boolean = true,
  val showClearButton: Boolean = false,
  val currentPageUrl: String = "",
  val showPrivacyIcon: Boolean = false,
)

data class DisplayUrl(
  val text: String,
  val isSafe: Boolean,
)
