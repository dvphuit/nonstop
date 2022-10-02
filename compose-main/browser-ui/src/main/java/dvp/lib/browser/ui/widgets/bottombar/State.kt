package dvp.lib.browser.ui.widgets.bottombar

internal data class ToolbarState(
  val isEditMode: Boolean = false,
  val query: String = "",
  val showHint: Boolean = true,
  val showClearButton: Boolean = false,
  val currentPageUrl: String = "",
)

