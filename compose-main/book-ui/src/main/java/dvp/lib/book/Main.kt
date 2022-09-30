package dvp.lib.book

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BookUI() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Book Page", modifier = Modifier.align(Alignment.Center))
    }
}