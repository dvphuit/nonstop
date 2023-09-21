package dvp.ui.youtube.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import dvp.data.youtube.models.ChannelEntity
import dvp.data.youtube.models.VideoEntity
import dvp.data.youtube.viewmodel.YoutubeState
import dvp.ui.youtube.view.common.rememberFlowWithLifecycle

@Composable
internal fun VideoListView(
    modifier: Modifier,
    state: YoutubeState,
    onVideoClicked: (VideoEntity) -> Unit,
    onChannelClicked: (ChannelEntity) -> Unit,
) {
    val data = rememberFlowWithLifecycle(state.videos).collectAsLazyPagingItems()
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
        content = {
            items(data.itemCount) { index ->
                data[index]?.let {
                    VideoCard(
                        video = it,
                        onVideoClicked = onVideoClicked::invoke,
                        onChannelClicked = onChannelClicked::invoke
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

