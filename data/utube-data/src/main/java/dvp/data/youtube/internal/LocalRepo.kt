package dvp.data.youtube.internal

import dvp.data.youtube.models.VideoEntity


internal class LocalRepo {
    private var browseToken: String? = null
    private var relativeToken: String? = null
    private var commentToken: String? = null

    private var currentVideo: VideoEntity? = null
    private var relatedVideos: List<VideoEntity> = emptyList()


    fun setCurrentVideo(video: VideoEntity?) {
        this.currentVideo = video
    }

    fun getCurrentVideo(): VideoEntity? {
        return currentVideo
    }

    fun setBrowseToken(token: String?) {
        this.browseToken = token
    }

    fun getBrowseToken(): String? {
        return this.browseToken
    }

    fun setRelativeToken(token: String?) {
        this.relativeToken = token
    }

    fun getRelativeToken(): String? {
        return this.relativeToken
    }

    fun setRelatedVideos(videos: List<VideoEntity>) {
        println("TEST: UTubeRepo setRelatedVideos ${videos.size}")
        this.relatedVideos = videos
    }

    fun getRelatedVideos(): List<VideoEntity> = relatedVideos.also {
        println("TEST: UTubeRepo getRepstiveVideo ${it.size}")
    }
}