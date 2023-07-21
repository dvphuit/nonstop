package dvp.ui.youtube

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import dvp.data.youtube.models.AudioFormat
import dvp.data.youtube.models.ChannelEntity
import dvp.data.youtube.models.StreamingData
import dvp.data.youtube.models.ThumbnailEntity
import dvp.data.youtube.models.VideoEntity
import dvp.data.youtube.models.VideoFormat
import dvp.data.youtube.viewmodel.YoutubeEvent
import dvp.data.youtube.viewmodel.UTubeViewModel
import dvp.lib.common.extension.cast
import dvp.lib.core.viewmodel.BaseViewState
import dvp.ui.youtube.common.OnLifecycleEvent
import dvp.ui.youtube.mediaplayer.MediaViewModel
import dvp.ui.youtube.mediaplayer.models.PlayerEvent
import dvp.ui.youtube.player.VideoView
import org.koin.androidx.compose.koinViewModel

@androidx.media3.common.util.UnstableApi
@Composable
fun YoutubeRouter(context: Context) {
    val mediaViewModel: MediaViewModel = koinViewModel()
    OnLifecycleEvent(onEvent = { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                mediaViewModel.submit(PlayerEvent.Foreground)
            }

            Lifecycle.Event.ON_STOP -> {
                mediaViewModel.submit(PlayerEvent.Background)
            }
            else -> {}
        }
    })
    MainPage()
}


@androidx.media3.common.util.UnstableApi
@Composable
fun TestVideoPlayer() {
    val uTubeViewModel: UTubeViewModel = koinViewModel()
    val mediaViewModel: MediaViewModel = koinViewModel()
    val state by mediaViewModel.uiState.collectAsState()
    val youtubeState by uTubeViewModel.uiState.collectAsState()

    if (youtubeState is BaseViewState.Ready) {
        OnLifecycleEvent(onEvent = { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    mediaViewModel.submit(PlayerEvent.Foreground)
                }

                Lifecycle.Event.ON_STOP -> {
                    mediaViewModel.submit(PlayerEvent.Background)
                }

                Lifecycle.Event.ON_DESTROY -> {
//                    mediaViewModel.submit(PlayerEvent.Stop)
                }

                else -> {}
            }
        })

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Modifier.VideoView(
                    state = youtubeState.getData().cast()
                )
            }
            when (state) {
                BaseViewState.Empty -> {
                }

                is BaseViewState.Error -> {}
                BaseViewState.Loading -> {}
                is BaseViewState.Ready -> {
                    val data = mediaViewModel.getData()
                    Row {
                        Button(onClick = {
                            mediaViewModel.submit(PlayerEvent.PlayPause)
                        }) {
                            Text(text = if (data.isPlaying) "Pause" else "Play")
                        }

                        Button(onClick = {
                            uTubeViewModel.submit(YoutubeEvent.SetVideo(null))
                        }) {
                            Text(text = "Stop")
                        }
                    }
                }
            }

        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Button(
                modifier = Modifier.align(Alignment.Center),
                onClick = {
                    uTubeViewModel.submit(YoutubeEvent.SetVideo(video))
                }) {
                Text(text = "Start")
            }
        }
    }
}

//videoUrl=, audioUrl=
private val video = VideoEntity(
    "",
    title = "Title of video",
    description = "",
    thumbnails = listOf(ThumbnailEntity("https://i.pinimg.com/736x/4b/02/1f/4b021f002b90ab163ef41aaaaa17c7a4.jpg", 100, 100)),
    published = "",
    duration = "",
    viewCount = "",
    ownerBadges = "",
    channel = ChannelEntity("test", "Author", thumbnail = ThumbnailEntity("", 0, 0)),
    streamingData = StreamingData(
        0, listOf(
            VideoFormat(
                itag = 0,
                url = "https://rr7---sn-8pxuuxa-nbozy.googlevideo.com/videoplayback?expire=1688977786&ei=Gm2rZNyZMrSO2roP3fqE8Aw&ip=171.235.35.161&id=o-AG3Px3GJtIYtTypf9Jhn9aVHiNvUS5VlcIpgmExb7T8a&itag=135&source=youtube&requiressl=yes&mh=aK&mm=31%2C29&mn=sn-8pxuuxa-nbozy%2Csn-8pxuuxa-nbole&ms=au%2Crdu&mv=m&mvi=7&pl=22&initcwndbps=1891250&siu=1&bui=AYlvQAv2oCYHVteaty0OTFTnpFsvNLHIYn-lJl7I5tAcSUg-a1tB_sL6v3bJQ5PYBElcTWaGMTUfD2aznXMqH4b0xQ&vprv=1&mime=video%2Fmp4&gir=yes&clen=3192730&dur=318.066&lmt=1577778557293266&mt=1688955916&fvip=2&keepalive=yes&fexp=24007246&beids=24350017&c=ANDROID_TESTSUITE&txp=5535432&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Csiu%2Cbui%2Cvprv%2Cmime%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRAIgGbZNepeuLcWeRtYw0oV1P82Eg3mTljis0O_tPwjh5kMCIAgY6RsWDsy7N8ELguGNafKJtRyZ7UQLZEH_1q93kf4v&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRQIhAJGjlBRlgucmLMZ_J8E4EMygoWii6PoYga7QFoZyGAeOAiBrjV6Lt3x0vBTHelPX3motoFJ7UYHWjt2rpHXz2Vvw_w%3D%3D",
                mimeType = "",
                bitrate = 0,
                width = 0,
                height = 0,
                qualityLabel = "480p",
                contentLength = 0,
                highReplication = false
            )
        ), listOf(
            AudioFormat(
                itag = 0,
                url = "https://rr7---sn-8pxuuxa-nbozy.googlevideo.com/videoplayback?expire=1688977786&ei=Gm2rZNyZMrSO2roP3fqE8Aw&ip=171.235.35.161&id=o-AG3Px3GJtIYtTypf9Jhn9aVHiNvUS5VlcIpgmExb7T8a&itag=140&source=youtube&requiressl=yes&mh=aK&mm=31%2C29&mn=sn-8pxuuxa-nbozy%2Csn-8pxuuxa-nbole&ms=au%2Crdu&mv=m&mvi=7&pl=22&initcwndbps=1891250&siu=1&bui=AYlvQAv2oCYHVteaty0OTFTnpFsvNLHIYn-lJl7I5tAcSUg-a1tB_sL6v3bJQ5PYBElcTWaGMTUfD2aznXMqH4b0xQ&vprv=1&mime=audio%2Fmp4&gir=yes&clen=5148990&dur=318.113&lmt=1577778314589332&mt=1688955916&fvip=2&keepalive=yes&fexp=24007246&beids=24350017&c=ANDROID_TESTSUITE&txp=5531432&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Csiu%2Cbui%2Cvprv%2Cmime%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRAIgQTYF1dlO9tWpxuE-oO8-NbLv6qNt2nzj0w7XOHNMj70CIHNZ_DTjf2VkHusP2TCu83fOsmsnLVr43SmBv0iJpbGi&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRQIhAJGjlBRlgucmLMZ_J8E4EMygoWii6PoYga7QFoZyGAeOAiBrjV6Lt3x0vBTHelPX3motoFJ7UYHWjt2rpHXz2Vvw_w%3D%3D",
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
)