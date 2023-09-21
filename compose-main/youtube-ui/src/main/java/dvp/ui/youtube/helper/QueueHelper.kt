package dvp.ui.youtube.helper

import dvp.data.youtube.public.UTubeRepo
import dvp.ui.youtube.viewmodel.models.MediaData
import org.koin.java.KoinJavaComponent.inject

internal object QueueHelper {

    private val repo: UTubeRepo by inject(UTubeRepo::class.java)
    private var playlist = mutableListOf<MediaData>()

    private var index: Int = 0

    fun registerRelatedVideosChanged(relatedVides: (List<MediaData>) -> Unit) {
        repo.onRelatedVideosChanged {
            it.relatedVideos
                ?.map(MediaData::fromVideoStreaming)
                ?.run {
                    playlist.addAll(this)
                    relatedVides.invoke(this)
                }
        }
    }


    fun set(mediaData: MediaData){
        playlist.clear()
        playlist.add(mediaData)
    }

    fun setPlayList(list: List<MediaData>) {
        playlist = list.toMutableList()
    }

    fun clear() {
        playlist.clear()
    }

    suspend fun getStreamingData(videoId: String): MediaData {
        val entity = repo.getVideoDetails(videoId)
        val ret = MediaData.fromVideoStreaming(entity)
        updateStreamData(ret)
        return ret
    }

    // todo: fetch continues related videos also
    suspend fun fetchNextStreams(currentIndex: Int): MediaData? {
        if (playlist.isEmpty()) return null

        index = currentIndex
        if (playlist.lastIndex == index) return null
        val next = playlist[index + 1]
        println("TEST: queue -> fetch index = ${index + 1} | hasStream = ${next.hasStream()}")
        if (next.hasStream()) return null

        return getStreamingData(next.videoId)
    }

    private fun updateStreamData(mediaData: MediaData) {
        playlist.forEach {
            if (it.videoId == mediaData.videoId) {
                it.videoUrl = mediaData.videoUrl
                it.audioUrl = mediaData.audioUrl
            }
        }
    }

}
