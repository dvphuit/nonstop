package dvp.data.youtube.models

data class VideoFormat(
    val itag: Long,
    val url: String,
    val mimeType: String,
    val bitrate: Long,
    val width: Long,
    val height: Long,
    val qualityLabel: String,
    val contentLength: Long,
    val highReplication: Boolean = false,
)