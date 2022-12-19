package dvp.data.youtube.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Annotation(
    val playerAnnotationsExpandedRenderer: PlayerAnnotationsExpandedRenderer
)

@Serializable
data class PlayerAnnotationsExpandedRenderer(
    val featuredChannel: FeaturedChannel,
    val allowSwipeDismiss: Boolean,

    @SerialName("annotationId")
    val annotationID: String
)

@Serializable
data class FeaturedChannel(
    @SerialName("startTimeMs")
    val startTimeMS: String,

    @SerialName("endTimeMs")
    val endTimeMS: String,

    val watermark: WatermarkClass,
    val trackingParams: String,
    val navigationEndpoint: FeaturedChannelNavigationEndpoint,
    val channelName: String,
    val subscribeButton: FeaturedChannelSubscribeButton
)

@Serializable
data class FeaturedChannelNavigationEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val browseEndpoint: EndpointBrowseEndpoint
)

@Serializable
data class EndpointCommandMetadata (
    val webCommandMetadata: PurpleWebCommandMetadata
)

@Serializable
data class PurpleWebCommandMetadata (
    val url: String,
    val webPageType: String,
    val rootVe: Long,

    @SerialName("apiUrl")
    val apiURL: String? = null
)

@Serializable
data class EndpointBrowseEndpoint (
    @SerialName("browseId")
    val browseID: String
)

@Serializable
data class FeaturedChannelSubscribeButton (
    val subscribeButtonRenderer: SubscribeButtonRenderer
)