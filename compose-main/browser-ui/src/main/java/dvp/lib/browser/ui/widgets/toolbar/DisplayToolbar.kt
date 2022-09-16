package dvp.lib.browser.ui.widgets.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dvp.lib.corebrowser.R
import dvp.lib.corebrowser.composer.ui.theme.BrowserRoundedShape
import dvp.lib.corebrowser.composer.ui.theme.getSystemThemeIcon


@Composable
internal fun DisplayToolbar(
  displayText: String,
  isSafeUrl: Boolean,
  showHint: Boolean,
  hint: String,
  showPrivacyIcon: Boolean,
  onUrlClicked: () -> Unit = {},
  onRefreshClicked: () -> Unit = {},
  onCloseButtonClicked: () -> Unit = {},
) {
  val modifier = Modifier
  Row(
    modifier
      .fillMaxWidth()
      .height(60.dp)
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
    Row(
      modifier = modifier
        .padding(2.dp)
        .wrapContentHeight()
        .align(Alignment.CenterVertically)
        .padding(vertical = 4.dp)
        .clickable { onUrlClicked() }
        .background(
          color = MaterialTheme.colors.primaryVariant,
          shape = BrowserRoundedShape
        )
    ) {
      Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
          .weight(0.8f)
          .align(Alignment.CenterVertically)
      ) {
        if (showPrivacyIcon) {
          PrivacyIcon(isSafeUrl, modifier.align(Alignment.CenterVertically))
        }
        Text(
          text = if (showHint) hint else displayText,
          maxLines = 1,
          color = MaterialTheme.colors.onPrimary,
          textAlign = TextAlign.Center,
          modifier = modifier
            .align(Alignment.CenterVertically)
            .wrapContentWidth()
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
}

@Composable
private fun PrivacyIcon(isSafeUrl: Boolean, modifier: Modifier) {
  val description = if (isSafeUrl) "https" else "http"
  Spacer(modifier = modifier.padding(horizontal = 2.dp))

  Icon(
    painterResource(id = getSystemThemeIcon(R.drawable.dark_secure, R.drawable.light_secure)),
    modifier = modifier,
    contentDescription = description
  )
  Spacer(modifier = modifier.padding(horizontal = 2.dp))
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
