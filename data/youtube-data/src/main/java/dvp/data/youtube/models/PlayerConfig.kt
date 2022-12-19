package dvp.data.youtube.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerConfig (
    val audioConfig: AudioConfig,
    val streamSelectionConfig: StreamSelectionConfig,
    val mediaCommonConfig: MediaCommonConfig,
    val webPlayerConfig: WebPlayerConfig
)

@Serializable
data class AudioConfig (
    @SerialName("loudnessDb")
    val loudnessDB: Double,

    @SerialName("perceptualLoudnessDb")
    val perceptualLoudnessDB: Double,

    val enablePerFormatLoudness: Boolean
)

@Serializable
data class StreamSelectionConfig (
    val maxBitrate: String
)

@Serializable
data class MediaCommonConfig (
    val dynamicReadaheadConfig: DynamicReadaheadConfig
)

@Serializable
data class DynamicReadaheadConfig (
    @SerialName("maxReadAheadMediaTimeMs")
    val maxReadAheadMediaTimeMS: Long,

    @SerialName("minReadAheadMediaTimeMs")
    val minReadAheadMediaTimeMS: Long,

    @SerialName("readAheadGrowthRateMs")
    val readAheadGrowthRateMS: Long
)


@Serializable
data class WebPlayerConfig (
    val useCobaltTvosDash: Boolean,
    val webPlayerActionsPorting: WebPlayerActionsPorting
)

@Serializable
data class WebPlayerActionsPorting (
    val getSharePanelCommand: GetSharePanelCommand,
    val subscribeCommand: SubscribeCommand,
    val unsubscribeCommand: UnsubscribeCommand,
    val addToWatchLaterCommand: AddToWatchLaterCommand,
    val removeFromWatchLaterCommand: RemoveFromWatchLaterCommand
)

@Serializable
data class GetSharePanelCommand (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val webPlayerShareEntityServiceEndpoint: WebPlayerShareEntityServiceEndpoint
)

@Serializable
data class WebPlayerShareEntityServiceEndpoint (
    val serializedShareEntity: String
)

@Serializable
data class SubscribeCommand (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val subscribeEndpoint: SubscribeEndpoint
)

@Serializable
data class UnsubscribeCommand (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val unsubscribeEndpoint: SubscribeEndpoint
)

@Serializable
data class AddToWatchLaterCommand (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val playlistEditEndpoint: AddToWatchLaterCommandPlaylistEditEndpoint
)

@Serializable
data class AddToWatchLaterCommandPlaylistEditEndpoint (
    @SerialName("playlistId")
    val playlistID: String,

    val actions: List<TentacledAction>
)

@Serializable
data class RemoveFromWatchLaterCommand (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val playlistEditEndpoint: RemoveFromWatchLaterCommandPlaylistEditEndpoint
)

@Serializable
data class UnsubscribeCommandCommandMetadata (
    val webCommandMetadata: FluffyWebCommandMetadata
)

@Serializable
data class RemoveFromWatchLaterCommandPlaylistEditEndpoint (
    @SerialName("playlistId")
    val playlistID: String,

    val actions: List<StickyAction>
)

@Serializable
data class FluffyWebCommandMetadata (
    val sendPost: Boolean,

    @SerialName("apiUrl")
    val apiURL: String? = null
)


@Serializable
data class StickyAction (
    val action: String,

    @SerialName("removedVideoId")
    val removedVideoID: String
)
