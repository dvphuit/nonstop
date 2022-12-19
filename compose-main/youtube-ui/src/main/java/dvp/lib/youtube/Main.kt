package dvp.lib.youtube

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dvp.data.youtube.YtViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.inject

@Composable
fun YoutubeUI() {
    val yt: YtViewModel = get()
    yt.getVideoInfo()

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Youtube Page", modifier = Modifier.align(Alignment.Center))
    }
}