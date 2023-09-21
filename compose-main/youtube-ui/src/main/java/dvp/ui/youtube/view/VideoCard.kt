package dvp.ui.youtube.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dvp.data.youtube.models.ChannelEntity
import dvp.data.youtube.models.VideoEntity
import dvp.ui.youtube.R


@Composable
internal fun VideoCard(
    video: VideoEntity,
    onVideoClicked: (VideoEntity) -> Unit,
    onChannelClicked: (ChannelEntity) -> Unit
) {
    val shape = RoundedCornerShape(15.dp)
    Box(
        modifier = Modifier
            .clip(shape)
            .fillMaxWidth(1f)
            .clickable {
                onVideoClicked.invoke(video)
            }
    ) {
        Column {
            Box(
                modifier = Modifier.aspectRatio(16f / 9f)
            ) {
                // video thumbnail
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(video.getThumbnailUrl())
                        .crossfade(true)
                        .build(),
                    contentDescription = "none",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape)
                )

                // duration
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .align(Alignment.BottomEnd)
                        .background(MaterialTheme.colorScheme.background.copy(alpha = .8f))

                ) {
                    Text(
                        text = video.duration,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        )
                    )
                }
            }

            // bottom
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                // channel avatar
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(video.channel?.thumbnail?.url)
                        .crossfade(false)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = "none",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .clickable { video.channel?.let { onChannelClicked.invoke(it) } }
                )

                Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                    // video title
                    Text(
                        text = video.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    // channel name
                    Text(
                        text = video.channel!!.name,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    // video stats
                    Text(
                        text = "${video.viewCount}  ·  ${video.published}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}