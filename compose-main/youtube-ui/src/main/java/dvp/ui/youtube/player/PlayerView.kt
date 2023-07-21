//package dvp.ui.youtube.player
//
//import android.content.res.Configuration
//import androidx.compose.animation.Crossfade
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.systemGestureExclusion
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.movableContentOf
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.media3.common.MediaItem
//import dvp.ui.youtube.media.TimeBar
//import dvp.data.youtube.viewmodel.YoutubeState
//import dvp.ui.youtube.R
//import dvp.ui.youtube.media.*
//import dvp.ui.youtube.media.SurfaceType
//import dvp.ui.youtube.service.MediaViewModel
//import kotlinx.coroutines.delay
//import org.koin.androidx.compose.koinViewModel
//
//@androidx.media3.common.util.UnstableApi
//@Composable
//internal fun Modifier.PlayerView(state: YoutubeState) {
//    var surfaceType by rememberSaveable { mutableStateOf(SurfaceType.SurfaceView) }
//    var resizeMode by rememberSaveable { mutableStateOf(ResizeMode.Fit) }
//    var keepContentOnPlayerReset by rememberSaveable { mutableStateOf(false) }
//    var useArtwork by rememberSaveable { mutableStateOf(true) }
//    var showBuffering by rememberSaveable { mutableStateOf(ShowBuffering.Always) }
//
//    var setPlayer by rememberSaveable { mutableStateOf(true) }
//    var playWhenReady by rememberSaveable { mutableStateOf(true) }
//
//    var controllerHideOnTouch by rememberSaveable { mutableStateOf(true) }
//    var controllerAutoShow by rememberSaveable { mutableStateOf(true) }
//
//    var rememberedMediaItemIdAndPosition: Pair<String, Long>? by remember { mutableStateOf(null) }
//    val vm: MediaViewModel = koinViewModel()
//    val player = vm.getPlayer()
//
//
//
//
//    DisposableEffect(player, playWhenReady) {
//        player.playWhenReady = playWhenReady
//        onDispose {}
//    }
//
//    val mediaItem = remember(url) { MediaItem.Builder().setMediaId(url).setUri(url).build() }
//    DisposableEffect(mediaItem, player) {
//        player.run {
//            setMediaItem(mediaItem)
//            rememberedMediaItemIdAndPosition?.let { (id, position) ->
//                if (id == mediaItem.mediaId) seekTo(position)
//            }?.also { rememberedMediaItemIdAndPosition = null }
//            prepare()
//        }
//        onDispose {}
//    }
//
//    val playerState = rememberMediaState(player = player.takeIf { setPlayer })
//    val content = remember {
//        movableContentOf {
//            Media(
//                playerState,
//                modifier = Modifier
//                    .aspectRatio(16f / 9f)
//                    .background(Color.Black),
//                surfaceType = surfaceType,
//                resizeMode = resizeMode,
//                keepContentOnPlayerReset = keepContentOnPlayerReset,
//                useArtwork = useArtwork,
//                showBuffering = showBuffering,
//                buffering = {
//                    Box(Modifier.fillMaxSize(), Alignment.Center) {
//                        CircularProgressIndicator()
//                    }
//                },
//                errorMessage = { error ->
//                    Box(Modifier.fillMaxSize(), Alignment.Center) {
//                        Text(
//                            error.message ?: "",
//                            modifier = Modifier
//                                .background(Color(0x80808080), RoundedCornerShape(16.dp))
//                                .padding(horizontal = 12.dp, vertical = 4.dp),
//                            color = Color.White,
//                            textAlign = TextAlign.Center
//                        )
//                    }
//                },
//                controllerHideOnTouch = controllerHideOnTouch,
//                controllerAutoShow = controllerAutoShow,
//                controller = { SimpleController(playerState, Modifier.fillMaxSize()) }
//            )
//        }
//    }
//
//
//    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
//    if (!isLandscape) Column(this) { content() } else Row(this) { content() }
//}
//
//
//@Composable
//fun SimpleController(
//    mediaState: MediaState,
//    modifier: Modifier = Modifier,
//) {
//    Crossfade(targetState = mediaState.isControllerShowing, modifier) { isShowing ->
//        if (isShowing) {
//            val controllerState = rememberControllerState(mediaState)
//            var scrubbing by remember { mutableStateOf(false) }
//            val hideWhenTimeout = !mediaState.shouldShowControllerIndefinitely && !scrubbing
//            var hideEffectReset by remember { mutableIntStateOf(0) }
//            LaunchedEffect(hideWhenTimeout, hideEffectReset) {
//                if (hideWhenTimeout) {
//                    // hide after 3s
//                    delay(3000)
//                    mediaState.isControllerShowing = false
//                }
//            }
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color(0x98000000))
//            ) {
//                Image(
//                    painter = painterResource(
//                        if (controllerState.showPause) R.drawable.ic_play
//                        else R.drawable.ic_play
//                    ),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(52.dp)
//                        .clickable(
//                            interactionSource = remember { MutableInteractionSource() },
//                            indication = null,
//                        ) {
//                            hideEffectReset++
//                            controllerState.playOrPause()
//                        }
//                        .align(Alignment.Center),
//                    colorFilter = ColorFilter.tint(Color.White)
//                )
//
//                LaunchedEffect(Unit) {
//                    while (true) {
//                        delay(200)
//                        controllerState.triggerPositionUpdate()
//                    }
//                }
//                TimeBar(
//                    controllerState.durationMs,
//                    controllerState.positionMs,
//                    controllerState.bufferedPositionMs,
//                    modifier = Modifier
//                        .systemGestureExclusion()
//                        .fillMaxWidth()
//                        .height(28.dp)
//                        .align(Alignment.BottomCenter),
//                    contentPadding = PaddingValues(12.dp),
//                    scrubberCenterAsAnchor = true,
//                    onScrubStart = { scrubbing = true },
//                    onScrubStop = { positionMs ->
//                        scrubbing = false
//                        controllerState.seekTo(positionMs)
//                    }
//                )
//            }
//        }
//    }
//}