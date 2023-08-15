package dvp.ui.youtube.mediaplayer.models

import android.os.Parcelable
import dvp.data.youtube.models.VideoEntity
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize


@Parcelize
data class MediaData(
    val videoId: String,
    val artWork: String? = null,
    val author: String,
    val title: String,
    var videoUrl: String,
    var audioUrl: String,
) : Parcelable {

    @IgnoredOnParcel
    lateinit var instance: VideoEntity

    fun hasStream() = videoUrl.isNotEmpty() && audioUrl.isNotEmpty()

    companion object {
        fun fromVideoStreaming(video: VideoEntity) = MediaData(
            videoId = video.id,
            artWork = video.getThumbnailUrl(),
            author = video.channel?.name ?: "",
            title = video.title,
            videoUrl = video.streamingData?.getVideo()?.url.orEmpty(),
            audioUrl = video.streamingData?.getAudio()?.url.orEmpty(),
        ).apply {
            instance = video
        }
    }
}