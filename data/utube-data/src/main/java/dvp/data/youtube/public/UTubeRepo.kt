package dvp.data.youtube.public

import dvp.data.youtube.models.Rating
import dvp.data.youtube.models.VideoEntity
import dvp.lib.ytube.dto.YoutubeVideoResponse
import kotlinx.coroutines.flow.Flow

interface UTubeRepo {

    fun setCookie(cookie: String)

    suspend fun getVideos(): List<VideoEntity>

    suspend fun getRelatedVideos(video: VideoEntity): VideoEntity
    suspend fun getRelative(videoId: String): List<VideoEntity>

    suspend fun getVideoDetails(video: VideoEntity): VideoEntity

    suspend fun getVideoDetails(videoId: String): VideoEntity

    suspend fun rating(id: String, rating: Rating): Boolean

    suspend fun subscribe(channelId: String): Boolean

    fun onRelatedVideosChanged(callback: ((VideoEntity) -> Unit)? = null)
}