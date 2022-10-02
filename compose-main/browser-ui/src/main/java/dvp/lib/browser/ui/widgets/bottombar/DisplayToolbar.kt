package dvp.lib.browser.ui.widgets.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dvp.lib.corebrowser.R
import dvp.lib.corebrowser.composer.ui.theme.BrowserRoundedShape
import dvp.lib.corebrowser.composer.ui.theme.getSystemThemeIcon


@Composable
internal fun DisplayToolbar(
  onUrlClicked: () -> Unit = {},
  onRefreshClicked: () -> Unit = {},
  onCloseButtonClicked: () -> Unit = {},
) {
  val modifier = Modifier
  Row(
    modifier
      .fillMaxWidth()
      .height(50.dp)
      .background(MaterialTheme.colors.background)
      .padding(horizontal = 8.dp)
  ) {
    DisplayToolbarButton(
      modifier = modifier
        .wrapContentWidth()
        .align(Alignment.CenterVertically),
      resourceId = getSystemThemeIcon(
        R.drawable.dark_close,
        R.drawable.light_close
      ),
      onButtonClicked = onCloseButtonClicked,
    )
    Box(
      modifier = modifier
        .fillMaxSize()
        .padding(4.dp)
        .weight(1f)
        .clickable { onUrlClicked() }
        .background(
          color = Color.Gray,
          shape = BrowserRoundedShape
        )
    ) {
      Text(
        text = "Search something",
        maxLines = 1,
        color = MaterialTheme.colors.onPrimary,
        textAlign = TextAlign.Center,
        modifier = modifier
          .fillMaxWidth()
          .align(Alignment.Center)
      )
    }

    DisplayToolbarButton(
      modifier = modifier.weight(0.1f),
      resourceId = getSystemThemeIcon(
        lightIcon = R.drawable.light_refresh,
        darkIcon = R.drawable.dark_refresh
      ),
      onButtonClicked = onRefreshClicked,
    )
  }
}


@Composable
internal fun DisplayToolbarButton(
  modifier: Modifier = Modifier,
  resourceId: Int,
  description: String = "",
  onButtonClicked: () -> Unit = {},
) {
  IconButton(
    modifier = modifier,
    onClick = { onButtonClicked() }
  ) {
    Icon(
      painter = painterResource(id = resourceId),
      contentDescription = description
    )
  }
}
