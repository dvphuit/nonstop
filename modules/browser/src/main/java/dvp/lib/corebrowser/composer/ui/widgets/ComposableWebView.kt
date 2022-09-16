package dvp.lib.corebrowser.composer.ui.widgets

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import dvp.lib.corebrowser.webview.CoreWebView
import dvp.lib.corebrowser.features.BrowserDelegate

@Composable
fun ComposableWebView(
  onReady: () -> Unit = {},
  onBrowserDelegateCreated: (BrowserDelegate) -> Unit,
) {
  AndroidView(
    factory = { context ->
      CoreWebView(context).apply {
        layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
      }
    },
    update = {
      onBrowserDelegateCreated(it)
      onReady()
    }
  )
}
