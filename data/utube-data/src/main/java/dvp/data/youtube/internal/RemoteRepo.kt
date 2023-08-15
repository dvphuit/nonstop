package dvp.data.youtube.internal

import dvp.lib.ytube.UClient
import dvp.lib.ytube.dto.init.CompactVideoDto
import dvp.lib.ytube.dto.init.VideoDataDto
import dvp.lib.ytube.dto.init.VideoDto


internal class RemoteRepo {
    private val client by lazy { UClient() }

    init {
        println("Youtube: RemoteRepo init")
    }

    fun setCookie(cookie: String) {
        client.setCookie(cookie)
    }

    suspend fun fetchInitVideos(): Pair<List<VideoDto>, String?> {
        val (videos, ctoken) = client.getInitVideos()
        return videos to ctoken?.getToken()
    }

    suspend fun fetchNextVideos(token: String): Pair<List<VideoDto>, String?> {
        val (videos, ctoken) = client.getNextVideos(token)
        return videos to ctoken?.getToken()
    }

    suspend fun getVideoDetails(id: String): VideoDataDto {
        return client.getVideoDetail(videoId = id)
    }

    suspend fun fetchRelatedVideos(videoId: String): Pair<List<CompactVideoDto>, String?> {
        val (videos, ctoken) = client.getRelativeVideos(videoId)
        return videos to ctoken?.getToken()
    }

    suspend fun fetchContinueRelativeVideo(token: String): Pair<List<CompactVideoDto>, String?> {
        val (videos, ctoken) = client.getContinueRelativeVideos(token)
        return videos to ctoken?.getToken()
    }


}