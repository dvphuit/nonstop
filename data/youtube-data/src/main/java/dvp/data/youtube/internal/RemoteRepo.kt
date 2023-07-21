package dvp.data.youtube.internal

import dvp.data.youtube.extension.toVideo
import dvp.data.youtube.models.Rating
import dvp.data.youtube.models.VideoEntity
import dvp.lib.common.logger.logD
import dvp.lib.ytube.YtClient
import dvp.lib.ytube.dto.YoutubeVideoResponse


class RemoteRepo {
    private val client by lazy { YtClient() }
    private var nextToken: String? = null

    init {
        println("Youtube: RemoteRepo init")
    }

    fun setCookie(cookie: String) {
        client.setCookie(cookie)
    }

    suspend fun fetchVideos(): List<VideoEntity> {
        return if (nextToken == null) {
            val contents =
                client.getHomePage().contents?.twoColumnBrowseResultsRenderer?.tabs?.firstOrNull()?.tabRenderer?.content?.richGridRenderer?.contents
//            nextToken = contents?.lastOrNull()?.continuationItemRenderer?.continuationEndpoint?.continuationCommand?.token
            contents?.mapNotNull {
                it.richItemRenderer?.content?.videoRenderer?.toVideo()
            }
        } else {
            val continuation =
                client.getNext(nextToken!!).onResponseReceivedActions?.firstOrNull()?.appendContinuationItemsAction?.continuationItems
            nextToken =
                continuation?.lastOrNull()?.continuationItemRenderer?.continuationEndpoint?.continuationCommand?.token
            continuation?.mapNotNull {
                it.richItemRenderer?.content?.videoRenderer?.toVideo()
            }
        } ?: emptyList()
    }

    suspend fun getVideo(id: String): YoutubeVideoResponse {
        return client.getVideo(videoId = id)
    }

    suspend fun getRelative(id: String): YoutubeVideoResponse {
        return client.getVideo(id)
    }

    suspend fun rating(id: String, rating: Rating): Boolean {
        TODO("Not yet implemented")
    }

    suspend fun subscribe(channelId: String): Boolean {
        TODO("Not yet implemented")
    }


}