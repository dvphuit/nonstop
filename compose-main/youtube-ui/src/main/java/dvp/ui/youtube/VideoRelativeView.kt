package dvp.ui.youtube

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dvp.data.youtube.models.VideoEntity
import dvp.ui.youtube.common.LoadingView


@Composable
fun VideoRelatedView(modifier: Modifier, video: VideoEntity?) {
    video ?: return

    if (video.relatedVideos == null) {
        LoadingView()
        return
    }

    if (video.relatedVideos?.isEmpty() == true) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Green)
        )
        return
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    )
}