package dvp.data.youtube.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PlaybackTracking (
    @SerialName("videostatsPlaybackUrl")
    val videostatsPlaybackURL: URL,

    @SerialName("videostatsDelayplayUrl")
    val videostatsDelayplayURL: URL,

    @SerialName("videostatsWatchtimeUrl")
    val videostatsWatchtimeURL: URL,

    @SerialName("ptrackingUrl")
    val ptrackingURL: URL,

    @SerialName("qoeUrl")
    val qoeURL: URL,

    @SerialName("atrUrl")
    val atrURL: AtrURL,

    val videostatsScheduledFlushWalltimeSeconds: List<Long>,
    val videostatsDefaultFlushIntervalSeconds: Long
)

@Serializable
data class AtrURL (
    @SerialName("baseUrl")
    val baseURL: String,

    val elapsedMediaTimeSeconds: Long
)

@Serializable
data class URL (
    @SerialName("baseUrl")
    val baseURL: String
)