package dvp.ui.youtube.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.media3.exoplayer.ExoPlayer
import dvp.data.youtube.models.AudioFormat
import dvp.data.youtube.models.StreamingData
import dvp.data.youtube.models.VideoEntity
import dvp.data.youtube.models.VideoFormat
import dvp.ui.youtube.viewmodel.MediaViewModel
import dvp.ui.youtube.view.player.VideoView
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Composable
fun TestVideoPlayer() {

    val player: ExoPlayer = get()
    val mediaViewModel: MediaViewModel = koinViewModel()

    Column(modifier = Modifier.fillMaxSize()) {
        val modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)

        Box(modifier = modifier.zIndex(1f)) {
            VideoView(modifier = modifier, video = testVideo, onMainEvent = {})
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .zIndex(0f)
                .background(Color.LightGray)
        )
    }
}


private val testVideo = VideoEntity(
    id = "1234",
    title = "Bunny",
    description = "",
    thumbnails = listOf(),
    published = "",
    duration = "",
    viewCount = "",
    ownerBadges = "",
    channel = null,
    streamingData = StreamingData(
        expireIn = 0, videoFormats = listOf(
            VideoFormat(
                itag = 0,
                url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                mimeType = "",
                bitrate = 0,
                width = 0,
                height = 0,
                qualityLabel = "480p",
                contentLength = 0,
                highReplication = false
            )
        ), audioFormats = listOf(
            AudioFormat(
                itag = 0,
                url = "",
                mimeType = "",
                bitrate = 140000,
                contentLength = 0,
                audioQuality = "",
                audioSampleRate = "",
                audioChannels = 0,
                loudnessDb = 0.0,
                highReplication = false
            )
        )
    ),
    relatedVideos = listOf()
)