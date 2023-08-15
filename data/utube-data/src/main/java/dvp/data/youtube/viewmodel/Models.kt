package dvp.data.youtube.viewmodel

import androidx.paging.PagingData
import dvp.data.youtube.models.VideoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class YoutubeState(
    val videos: Flow<PagingData<VideoEntity>> = emptyFlow(),
    val openingVideo: VideoEntity? = null
)

sealed class MainEvent {
    object LoadVideos : MainEvent()

    data class SetVideo(val video: VideoEntity?) : MainEvent()
}

data class DetailState(
//    val video: VideoEntity,
    val related: Flow<PagingData<VideoEntity>>
)

sealed class DetailEvent {
    data class Init(val videoId: String) : DetailEvent()
}