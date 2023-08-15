package dvp.data.youtube.models

data class AudioFormat(
    val itag: Long,
    val url: String,
    val mimeType: String,
    val bitrate: Long,
    val contentLength: Long,
    val audioQuality: String,
    val audioSampleRate: String,
    val audioChannels: Long,
    val loudnessDb: Double = 0.0,
    val highReplication: Boolean = false,
)