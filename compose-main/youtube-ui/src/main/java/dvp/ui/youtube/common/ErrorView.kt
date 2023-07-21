package dvp.ui.youtube.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorView(modifier: Modifier = Modifier, e: Throwable, action: () -> Unit) {
    // todo: handleThrowable- create extension method
    e.printStackTrace()
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        Icon(
            painter = rememberVectorPainter(Icons.Default.Info),
            contentDescription = null,
            tint = Red,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
        SmallSpacer()
        Text(
            text = e.localizedMessage ?: "Something error",
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
        SmallSpacer()
        Button(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            onClick = action
        ) {
            Text(text = "text_retry")
        }
    }
}
