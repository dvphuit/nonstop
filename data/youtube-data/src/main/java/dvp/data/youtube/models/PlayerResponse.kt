package dvp.data.youtube.models

import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponse (
//    val responseContext: PlayerResponseResponseContext,
    val playabilityStatus: PlayabilityStatus,
    val streamingData: StreamingData,
    val playbackTracking: PlaybackTracking,
    val videoDetails: VideoDetails,
    val annotations: List<Annotation>,
    val playerConfig: PlayerConfig,
    val storyboards: Storyboards,
    val microformat: Microformat,
    val cards: Cards,
    val trackingParams: String,
    val attestation: Attestation,
    val videoQualityPromoSupportedRenderers: VideoQualityPromoSupportedRenderers,
    val messages: List<Overlay>,
    val frameworkUpdates: PlayerResponseFrameworkUpdates
)

//===================================
@Serializable
data class PlayabilityStatus (
    val status: String,
    val playableInEmbed: Boolean,
    val miniplayer: Miniplayer,
    val contextParams: String
)

@Serializable
data class Miniplayer (
    val miniplayerRenderer: MiniplayerRenderer
)

@Serializable
data class MiniplayerRenderer (
    val playbackMode: String
)

//===================================