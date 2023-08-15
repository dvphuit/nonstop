package dvp.lib.ytube.apis

import dvp.lib.ytube.dto.init.CompactVideoDto
import dvp.lib.ytube.dto.init.ContinuationDto
import dvp.lib.ytube.dto.init.VideoDataDto
import dvp.lib.ytube.dto.init.VideoDto

interface IUTubeApi {
    fun setCookie(cookie: String): Boolean

    suspend fun getInitVideos(): Pair<List<VideoDto>, ContinuationDto?>
    suspend fun getNextVideos(token: String): Pair<List<VideoDto>, ContinuationDto?>

    suspend fun getVideoDetail(videoId: String): VideoDataDto
    suspend fun getRelativeVideos(videoId: String): Pair<List<CompactVideoDto>, ContinuationDto?>
    suspend fun getContinueRelativeVideos(token: String): Pair<List<CompactVideoDto>, ContinuationDto?>
}