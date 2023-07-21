package dvp.data.youtube.internal

import dvp.data.youtube.models.VideoEntity


internal class LocalRepo {
    var cacheVideos = mutableListOf<VideoEntity>()

    fun init() {
        this.cacheVideos.clear()
    }

    fun addVideos(data: List<VideoEntity>) {
        this.cacheVideos.addAll(data)
    }
}