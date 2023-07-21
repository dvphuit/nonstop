package dvp.data.youtube.public

import dvp.data.youtube.models.Rating
import dvp.data.youtube.models.VideoEntity
import dvp.lib.ytube.dto.YoutubeVideoResponse
import kotlinx.coroutines.flow.Flow

interface YoutubeRepo {

    fun setCookie(cookie: String)

    fun getVideos(): Flow<List<VideoEntity>>

    suspend fun getVideo(id: String): YoutubeVideoResponse

    suspend fun rating(id: String, rating: Rating): Boolean

    suspend fun subscribe(channelId: String): Boolean
}