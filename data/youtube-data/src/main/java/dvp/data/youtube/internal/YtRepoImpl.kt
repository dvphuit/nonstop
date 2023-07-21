package dvp.data.youtube.internal

import dvp.data.youtube.models.Rating
import dvp.data.youtube.models.VideoEntity
import dvp.data.youtube.public.YoutubeRepo
import dvp.lib.ytube.dto.YoutubeVideoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class YtRepoImpl(private val local: LocalRepo, private val remote: RemoteRepo) :
    YoutubeRepo {

    init {
        local.init()
    }

    override fun setCookie(cookie: String) {
        remote.setCookie(cookie)
    }

    override fun getVideos(): Flow<List<VideoEntity>> {
        return flow {
            val data = remote.fetchVideos()
            println("TEST: data size =${data.size}")
            emit(data)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getVideo(id: String): YoutubeVideoResponse {
        return remote.getVideo(id)
    }

    override suspend fun rating(id: String, rating: Rating): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun subscribe(channelId: String): Boolean {
        TODO("Not yet implemented")
    }


}

