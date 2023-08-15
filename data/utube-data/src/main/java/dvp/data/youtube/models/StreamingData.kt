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



