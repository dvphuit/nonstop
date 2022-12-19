package dvp.data.youtube.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StreamingData(
    val expiresInSeconds: String,
    val formats: List<PurpleFormat>,
    val adaptiveFormats: List<AdaptiveFormatElement>
)

@Serializable
data class PurpleFormat(
    val itag: Long,
    val url: String,
    val mimeType: String,
    val bitrate: Long,
    val width: Long,
    val height: Long,
    val lastModified: String,
    val quality: String,
    val fps: Long,
    val qualityLabel: String,
    val projectionType: String,
    val audioQuality: String,

    @SerialName("approxDurationMs") val approxDurationMS: String,

    val audioSampleRate: String,
    val audioChannels: Long
)


@Serializable
data class AdaptiveFormatElement (
    val itag: Long,
    val url: String,
    val mimeType: String,
    val bitrate: Long,
    val width: Long? = null,
    val height: Long? = null,
    val lastModified: String,
    val quality: String,
    val fps: Long? = null,
    val qualityLabel: String? = null,
    val projectionType: String,
    val audioQuality: String? = null,

    @SerialName("approxDurationMs")
    val approxDurationMS: String,

    val audioSampleRate: String? = null,
    val audioChannels: Long? = null,
    val initRange: Range? = null,
    val indexRange: Range? = null,
    val contentLength: String? = null,
    val averageBitrate: Long? = null,
    val highReplication: Boolean? = null,

    @SerialName("loudnessDb")
    val loudnessDB: Double? = null
)