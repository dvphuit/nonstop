package dvp.lib.ytube.dto.init
import kotlinx.serialization.*

@Serializable
data class VideoDataDto (
    val streamingData: StreamingDataDto? = null,
    val videoDetails: VideoDetails? = null
)

@Serializable
data class StreamingDataDto (
    val expiresInSeconds: String? = null,
    val formats: List<FormatDto>? = null,
    val adaptiveFormats: List<AdaptiveFormatDto>? = null
)

@Serializable
data class AdaptiveFormatDto (
    val itag: Long? = null,
    val url: String? = null,
    val mimeType: String? = null,
    val bitrate: Long? = null,
    val width: Long? = null,
    val height: Long? = null,
    val initRange: Range? = null,
    val indexRange: Range? = null,
    val lastModified: String? = null,
    val contentLength: String? = null,
    val quality: String? = null,
    val fps: Long? = null,
    val qualityLabel: String? = null,
    val projectionType: String? = null,
    val averageBitrate: Long? = null,
    val approxDurationMs: String? = null,
    val colorInfo: ColorInfo? = null,
    val highReplication: Boolean? = null,
    val audioQuality: String? = null,
    val audioSampleRate: String? = null,
    val audioChannels: Long? = null
)

@Serializable
data class ColorInfo (
    val primaries: String? = null,
    val transferCharacteristics: String? = null,
    val matrixCoefficients: String? = null
)

@Serializable
data class Range (
    val start: String? = null,
    val end: String? = null
)

@Serializable
data class FormatDto (
    val itag: Long? = null,
    val url: String? = null,
    val mimeType: String? = null,
    val bitrate: Long? = null,
    val width: Long? = null,
    val height: Long? = null,
    val lastModified: String? = null,
    val contentLength: String? = null,
    val quality: String? = null,
    val fps: Long? = null,
    val qualityLabel: String? = null,
    val projectionType: String? = null,
    val averageBitrate: Long? = null,
    val audioQuality: String? = null,
    val approxDurationMs: String? = null,
    val audioSampleRate: String? = null,
    val audioChannels: Long? = null
)

@Serializable
data class VideoDetails (
    val videoId: String? = null,
    val title: String? = null,
    val lengthSeconds: String? = null,
    val channelId: String? = null,
    val shortDescription: String? = null,
    val thumbnail: ListThumbnail? = null,
    val allowRatings: Boolean? = null,
    val viewCount: String? = null,
    val author: String? = null,
    val isLiveContent: Boolean? = null
)
