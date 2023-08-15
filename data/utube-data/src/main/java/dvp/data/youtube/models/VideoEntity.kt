package dvp.data.youtube.models

data class VideoEntity(
    val id: String,
    val title: String,
    val description: String,
    val thumbnails: List<ThumbnailEntity>,
    val published: String,
    val duration: String,
    val viewCount: String,
    val ownerBadges: String,
    val channel: ChannelEntity?,
    var streamingData: StreamingData? = null,
    var relatedVideos: List<VideoEntity>? = null
) {
    fun getThumbnailUrl(): String {
        return thumbnails.last().url
    }

    fun getVideoUrl(): String? {
        return streamingData?.getVideo()?.url
    }

    fun getAudioUrl(): String? {
        return streamingData?.getAudio()?.url
    }
}