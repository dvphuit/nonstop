package dvp.lib.ytube.dto.init


import dvp.lib.ytube.dto.Thumbnail
import kotlinx.serialization.*


@Serializable
data class CompactVideoDto (
    val videoId: String? = null,
    val thumbnail: ListThumbnail? = null,
    val title: Text? = null,
    val publishedTimeText: Text? = null,
    val viewCountText: Text? = null,
    val lengthText: Text? = null,
    val ownerBadges: List<Badge>? = null,
    val channelThumbnail: ListThumbnail? = null,
    val richThumbnail: RichThumbnail? = null,
    val shortBylineText: RunTexts? = null
)

@Serializable
data class Text (
    val simpleText: String? = null,
)

@Serializable
data class RichThumbnail (
    val movingThumbnailRenderer: MovingThumbnailRenderer? = null
)

@Serializable
data class MovingThumbnailRenderer (
    val movingThumbnailDetails: MovingThumbnailDetails? = null,
)

@Serializable
data class MovingThumbnailDetails (
    val thumbnails: List<Thumbnail>? = null,
    val logAsMovingThumbnail: Boolean? = null
)

@Serializable
data class ListThumbnail (
    val thumbnails: List<Thumbnail>? = null
)

