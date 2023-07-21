package dvp.ui.youtube.mediaplayer.models

import android.os.Parcelable
import dvp.data.youtube.models.StreamingData
import dvp.data.youtube.models.VideoEntity
import kotlinx.parcelize.Parcelize


@Parcelize
data class MediaData(
    val videoId: String,
    val artWork: String? = null,
    val author: String,
    val name: String,
    val videoUrl: String,
    val audioUrl: String
): Parcelable {
    companion object {
        fun fromVideoStreaming(video: VideoEntity, stream: StreamingData) = MediaData(
            videoId = video.id,
            artWork = video.getThumbnailUrl(),
            author = video.channel?.name ?: "",
            name = video.title,
            videoUrl = stream.getVideo().url,
            audioUrl = stream.getAudio().url
        )
    }
}