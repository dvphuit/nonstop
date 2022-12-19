package dvp.data.youtube.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class VideoDetails (
    @SerialName("videoId")
    val videoID: String,

    val title: String,
    val lengthSeconds: String,
    val keywords: List<String>,

    @SerialName("channelId")
    val channelID: String,

    val isOwnerViewing: Boolean,
    val shortDescription: String,
    val isCrawlable: Boolean,
    val thumbnail: WatermarkClass,
    val allowRatings: Boolean,
    val viewCount: String,
    val author: String,
    val isPrivate: Boolean,
    val isUnpluggedCorpus: Boolean,
    val isLiveContent: Boolean
)

@Serializable
data class WatermarkClass (
    val thumbnails: List<ThumbnailElement>
)

@Serializable
data class ThumbnailElement (
    val url: String,
    val width: Long,
    val height: Long
)