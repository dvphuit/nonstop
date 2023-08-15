package dvp.ui.youtube

import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import dvp.data.youtube.models.VideoEntity
import dvp.data.youtube.viewmodel.MainEvent
import dvp.data.youtube.viewmodel.YoutubeState
import dvp.data.youtube.viewmodel.MainViewModel
import dvp.lib.core.debug.text30
import dvp.lib.core.debug.text5
import dvp.ui.youtube.common.rememberFlowWithLifecycle
import dvp.ui.youtube.player.VideoView
import kotlinx.coroutines.launch
import kotlin.math.min

@androidx.media3.common.util.UnstableApi
@Composable
internal fun YoutubeMainView(main: MainViewModel) {
    val state = main.getData()
    YoutubeMotionContainer(
        state = state,
        videoListView = { onVideoClicked ->
            VideoListView(
                state = state,
                onItemClicked = { video ->
                    onVideoClicked.invoke()
                    main.submit(MainEvent.SetVideo(video))
                })
        },
        videoView = {
            VideoView(video = state.openingVideo)
        },
        titleView = {
            Box(
                modifier = this
            ) {
                Text(text = text5)
            }
        },
        videoRelativeView = {
            VideoRelatedView(video = state.openingVideo)
        },
        closeButton = { onCloseClicked ->
            Box(modifier = this
                .clickable {
                    onCloseClicked.invoke()
                    main.submit(MainEvent.SetVideo(null))
                }
                .background(Color.Black)
            )
        }
    )
}


@Composable
internal fun Modifier.VideoListView(
    state: YoutubeState,
    onItemClicked: (VideoEntity) -> Unit
) {
    val data = rememberFlowWithLifecycle(state.videos).collectAsLazyPagingItems()
//    println("TEST: videoListView ${data.itemCount}")
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = this,
        content = {
            items(data.itemCount) { index ->
                data[index]?.let {
                    VideoCard(
                        video = it,
                        onVideoClicked = { video ->
                            onItemClicked.invoke(video)
                        },
                        onChannelClicked = {

                        }
                    )
                }
            }

            // append loading
            if (data.loadState.append == LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
internal inline fun YoutubeMotionContainer(
    state: YoutubeState,
    crossinline videoListView: @Composable() (Modifier.(() -> Unit) -> Unit),
    crossinline videoView: @Composable (Modifier.() -> Unit),
    crossinline titleView: @Composable (Modifier.() -> Unit),
    crossinline videoRelativeView: @Composable (Modifier.() -> Unit),
    crossinline closeButton: @Composable (Modifier.(() -> Unit) -> Unit),
) {
    val noVideo = state.openingVideo == null

    val closeAnim by animateDpAsState(
        targetValue = if (noVideo) 80.dp else 0.dp,
        animationSpec = tween(500, easing = EaseOutExpo), label = "closeAnim",
    )
    val alphaAnim by animateFloatAsState(
        targetValue = if (noVideo) 0f else 1f,
        animationSpec = tween(500, easing = EaseOutExpo), label = "alphaAnim"
    )

    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenHeight = with(density) { configuration.screenHeightDp.dp.toPx() }
    val swipeAreaHeight = screenHeight - 250.dp.value
    val anchors = remember { mapOf(0f to 0, -swipeAreaHeight to 1) }

    val scope = rememberCoroutineScope()

    val swipeState = rememberSwipeableState(
        0,
        animationSpec = tween(400, easing = Ease)
    )
    val progress = swipeState.offset.value / -swipeAreaHeight

    MotionLayout(
        motionScene = youtubeMotionScene(),
        progress = if(noVideo) 0f else progress.coerceIn(0f, 1f),
        modifier = Modifier
    ) {

        videoListView.invoke(
            Modifier
                .layoutId("video_list")
                .fillMaxSize()) {
            scope.launch {
                if (swipeState.currentValue == 0) {
                    swipeState.animateTo(1)
                }
            }
        }

        Box(
            modifier = Modifier
                .alpha(alphaAnim)
                .offset(y = closeAnim)
                .clickable(onClick = {
                    scope.launch {
                        if (swipeState.currentValue == 0) {
                            swipeState.animateTo(1)
                        }
                    }
                })
                .layoutId("video_wrapper")
                .background(Color.White)
                .swipeable(
                    state = swipeState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0f) },
                    orientation = Orientation.Vertical,
                    enabled = true,
                    velocityThreshold = 10.dp,
                )
        ) {

            Row(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.65f)
                    .align(Alignment.CenterEnd)
                    .alpha(alphaAnim)
                    .offset(y = closeAnim)
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .background(Color.Yellow),
                verticalAlignment = Alignment.CenterVertically
            ) {
                titleView.invoke(
                    Modifier.weight(1f)
                )

                closeButton.invoke(
                    Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .offset(x = (-4).dp)
                ) {
                }
            }


            videoView.invoke(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(videoScaleX(progress, videoWidthKeyFrame))
            )
        }

        videoRelativeView.invoke(
            Modifier
                .layoutId("video_relative")
                .alpha(alphaAnim)
                .background(Color.White)
        )

    }

}

private fun youtubeMotionScene(): MotionScene {
    return MotionScene {
        val videoList = createRefFor("video_list")
        val box = createRefFor("video_wrapper")
        val videoRelative = createRefFor("video_relative")

        val start = constraintSet("start") {
            constrain(videoList) {
                this.width = Dimension.matchParent
                this.height = Dimension.matchParent
            }
            constrain(box) {
                this.width = Dimension.fillToConstraints
                this.height = Dimension.value(80.dp)
                this.bottom.linkTo(parent.bottom)
                this.start.linkTo(parent.start)
                this.end.linkTo(parent.end)
            }
            constrain(videoRelative) {
                this.width = Dimension.matchParent
                this.height = Dimension.fillToConstraints
                this.start.linkTo(parent.start)
                this.end.linkTo(parent.end)
                this.top.linkTo(box.bottom)
                this.bottom.linkTo(parent.bottom)
            }
        }

        val end = constraintSet("end") {
            constrain(videoList) {
                this.width = Dimension.matchParent
                this.height = Dimension.matchParent
            }
            constrain(box) {
                this.width = Dimension.matchParent
                this.height = Dimension.value(250.dp)
                this.top.linkTo(parent.top)
                this.start.linkTo(parent.start)
                this.end.linkTo(parent.end)
            }

            constrain(videoRelative) {
                this.width = Dimension.matchParent
                this.height = Dimension.fillToConstraints
                this.top.linkTo(box.bottom)
                this.bottom.linkTo(parent.bottom)
                this.start.linkTo(parent.start)
                this.end.linkTo(parent.end)
            }
        }


        defaultTransition(start, end) {
            keyAttributes(videoList) {
                this.frame(100) {
                    this.translationY = (-50).dp
                    this.alpha = 0f
                }
            }
        }
    }
}


private val videoWidthKeyFrame = listOf(
    0.0f to 0.4f,
    0.2f to 1.0f,
)

private fun videoScaleX(progress: Float, keyframes: List<Pair<Float, Float>>): Float {
    val value = calculateKeyFrame(progress, keyframes) { fraction ->
        fraction * fraction * (3 - 2 * fraction)
    }
    return if (value.isNaN()) 1f else value
}

private fun calculateKeyFrame(
    position: Float,
    keyframes: List<Pair<Float, Float>>,
    interpolator: (Float) -> Float
): Float {
    var i = 0
    while (i < keyframes.size - 1 && position >= keyframes[i + 1].first) {
        i++
    }
    val start = keyframes[i]
    val end = keyframes[min(i + 1, keyframes.lastIndex)]
    val fraction = (position - start.first) / (end.first - start.first)
    return interpolator(start.second + fraction * (end.second - start.second))
}