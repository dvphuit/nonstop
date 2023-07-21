package dvp.data.youtube.viewmodel

import androidx.paging.PagingData
import dvp.data.youtube.models.VideoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class YoutubeState(
    val videos: Flow<PagingData<VideoEntity>> = emptyFlow(),
    val openingVideo: VideoEntity? = null
)


sealed class YoutubeEvent {
    object LoadVideos : YoutubeEvent()
    data class GetVideoDetail(val videoId: String) : YoutubeEvent()
    data class GetRelative(val videoId: String) : YoutubeEvent()
    data class SetVideo(val video: VideoEntity?) : YoutubeEvent()
}