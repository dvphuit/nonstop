package dvp.data.youtube.models

import kotlinx.serialization.Serializable

@Serializable
data class VideoQualityPromoSupportedRenderers (
    val videoQualityPromoRenderer: VideoQualityPromoRenderer
)

@Serializable
data class VideoQualityPromoRenderer (
    val triggerCriteria: TriggerCriteria,
    val text: Text,
    val endpoint: VideoQualityPromoRendererEndpoint,
    val trackingParams: String,
    val snackbar: Snackbar
)

@Serializable
data class Snackbar (
    val notificationActionRenderer: SnackbarNotificationActionRenderer
)

@Serializable
data class SnackbarNotificationActionRenderer (
    val responseText: DetailsText,
    val actionButton: ActionButton,
    val trackingParams: String
)

@Serializable
data class DetailsText (
    val runs: List<DetailsTextRun>
)

@Serializable
data class DetailsTextRun (
    val text: String
)

@Serializable
data class ActionButton (
    val buttonRenderer: ActionButtonButtonRenderer
)

@Serializable
data class ActionButtonButtonRenderer (
    val text: DetailsText,
    val navigationEndpoint: VideoQualityPromoRendererEndpoint,
    val trackingParams: String
)


@Serializable
data class VideoQualityPromoRendererEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val urlEndpoint: EndpointURLEndpoint
)

@Serializable
data class EndpointURLEndpoint (
    val url: String,
    val target: String
)

@Serializable
data class TriggerCriteria (
    val connectionWhitelist: List<String>,
    val joinLatencySeconds: Long,
    val rebufferTimeSeconds: Long,
    val watchTimeWindowSeconds: Long,
    val refractorySeconds: Long
)
