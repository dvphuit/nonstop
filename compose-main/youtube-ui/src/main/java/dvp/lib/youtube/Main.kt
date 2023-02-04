package dvp.lib.youtube

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dvp.data.youtube.YtViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.inject
import kotlin.math.roundToInt
import androidx.compose.ui.unit.Dp

@Composable
fun YoutubeUI() {
    val yt: YtViewModel = get()
    yt.getVideoInfo()

    Box(modifier = Modifier.fillMaxSize()) {
//        Text(text = "Youtube Page", modifier = Modifier.align(Alignment.Center))

        MoveBoxWhereTapped()
    }
}

@Preview(device = Devices.NEXUS_5)
@Composable
fun VideoCard() {
    val shape = RoundedCornerShape(15.dp)
    Box(
        modifier = Modifier
            .clip(shape)
            .fillMaxWidth(1f)
            .background(Color.Cyan)
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(Dp(200f))
                    .background(Color.Blue)
            ) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .align(Alignment.BottomEnd)
                        .background(Color.Yellow)

                ) {
//                    Box(modifier = Modifier.fillMaxSize().background(Color.White).blur(10.dp))
                    Text("15:53", modifier = Modifier.padding(4.dp))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE5AA70))
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "Title line 1\nline 2")
                    Text(text = "Channel Name | 200K Views | 5 min ago")
                }
            }
        }
    }
}

@Composable
fun MoveBoxWhereTapped() {
    // Creates an `Animatable` to animate Offset and `remember` it.
    val animatedOffset = remember {
        Animatable(Offset(0f, 0f), Offset.VectorConverter)
    }

    Box(
        // The pointerInput modifier takes a suspend block of code
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                // Create a new CoroutineScope to be able to create new
                // coroutines inside a suspend function
                coroutineScope {
                    while (true) {
                        // Wait for the user to tap on the screen
                        val offset = awaitPointerEventScope {
                            awaitFirstDown().position
                        }
                        // Launch a new coroutine to asynchronously animate to where
                        // the user tapped on the screen
                        launch {
                            // Animate to the pressed position
                            animatedOffset.animateTo(offset)
                        }
                    }
                }
            }
    ) {
        Text("Tap anywhere", Modifier.align(Alignment.Center))
        Box(
            Modifier
                .offset {
                    // Use the animated offset as the offset of this Box
                    IntOffset(
                        animatedOffset.value.x.roundToInt(),
                        animatedOffset.value.y.roundToInt()
                    )
                }
                .size(40.dp)
                .background(Color(0xff3c1361), CircleShape)
        )
    }
}