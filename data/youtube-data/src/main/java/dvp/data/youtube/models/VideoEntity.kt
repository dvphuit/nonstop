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
) {
    fun getThumbnailUrl(): String {
        return thumbnails.last().url
    }
}