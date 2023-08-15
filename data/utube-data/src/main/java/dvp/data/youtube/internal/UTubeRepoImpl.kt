package dvp.data.youtube.internal

import dvp.data.youtube.extension.toVideo
import dvp.data.youtube.models.Rating
import dvp.data.youtube.models.VideoEntity
import dvp.data.youtube.public.UTubeRepo

internal class UTubeRepoImpl(
    private val local: LocalRepo,
    private val remote: RemoteRepo
) : UTubeRepo {

    init {
        println("TEST: UTubeRepo init $this")
    }

    private var onRelatedVideoChagned: ((VideoEntity) -> Unit)? = null

    override fun setCookie(cookie: String) {
        println("TEST: UTubeRepo setCookie")
        remote.setCookie(cookie)
    }

    override suspend fun getVideos(): List<VideoEntity> {
        val res = local.getBrowseToken()?.run {
            remote.fetchNextVideos(this)
        } ?: remote.fetchInitVideos()

        local.setBrowseToken(res.second)
        return res.first.map { it.toVideo() }
    }

    override suspend fun getRelatedVideos(video: VideoEntity): VideoEntity {
        return video.copy(
            relatedVideos = getRelative(video.id)
        ).also {
            onRelatedVideoChagned?.invoke(it)
        }
    }

    override suspend fun getRelative(videoId: String): List<VideoEntity> {
        val (videos, token) = local.getRelativeToken()
            ?.run {
                remote.fetchContinueRelativeVideo(this)
            } ?: remote.fetchRelatedVideos(videoId)

        if (videoId != local.getCurrentVideo()?.id) {
            local.setRelatedVideos(emptyList())
        }

        local.setRelativeToken(token)
        return videos.map { it.toVideo() }.also(local::setRelatedVideos)
    }

    override suspend fun getVideoDetails(video: VideoEntity): VideoEntity {
        local.setCurrentVideo(video)
        return remote.getVideoDetails(video.id).toVideo(video)
    }

    override suspend fun getVideoDetails(videoId: String): VideoEntity {
        val video = local.getRelatedVideos().first { videoId == it.id }
        println("TEST: Related -> getDetailVideo $videoId")
        return remote.getVideoDetails(video.id).toVideo(video)
    }

    override suspend fun rating(id: String, rating: Rating): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun subscribe(channelId: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun onRelatedVideosChanged(callback: ((VideoEntity) -> Unit)?) {
        this.onRelatedVideoChagned = callback
    }
}

