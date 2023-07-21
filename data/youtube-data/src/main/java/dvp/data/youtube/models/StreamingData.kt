package dvp.data.youtube.models


data class StreamingData(
    val expireIn: Long,
    val videoFormats: List<VideoFormat>,
    val audioFormats: List<AudioFormat>
) {
    fun getVideo(): VideoFormat {
        return videoFormats.first { it.qualityLabel == "480p" }
    }

    fun getAudio(): AudioFormat {
        return audioFormats.first { it.bitrate > 100000 }
    }
}

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

data class AudioFormat(
    val itag: Long,
    val url: String,
    val mimeType: String,
    val bitrate: Long,
    val contentLength: Long,
    val audioQuality: String,
    val audioSampleRate: String,
    val audioChannels: Long,
    val loudnessDb: Double,
    val highReplication: Boolean = false,
)