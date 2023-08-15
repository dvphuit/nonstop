package dvp.lib.ytube.dto

import kotlinx.serialization.*

@Serializable
data class YoutubeVideoResponse (
//    val responseContext: ResponseContext? = null,
//    val playabilityStatus: PlayabilityStatus? = null,
    val streamingData: StreamingData? = null,
//    val playbackTracking: PlaybackTracking? = null,
    val videoDetails: VideoDetails? = null,
//    val playerConfig: PlayerConfig? = null,
//    val storyboards: Storyboards? = null,
//    val trackingParams: String? = null,
//    val attestation: Attestation? = null,
//    val videoQualityPromoSupportedRenderers: VideoQualityPromoSupportedRenderers? = null,
//    val overlay: Overlay? = null,
//    val playerSettingsMenuData: PlayerSettingsMenuData? = null,
//    val frameworkUpdates: FrameworkUpdates? = null
)

@Serializable
data class Attestation (
    val playerAttestationRenderer: PlayerAttestationRenderer? = null
)

@Serializable
data class PlayerAttestationRenderer (
    val challenge: String? = null
)

@Serializable
data class FrameworkUpdates (
    val entityBatchUpdate: EntityBatchUpdate? = null
)

@Serializable
data class EntityBatchUpdate (
    val mutations: List<Mutation>? = null,
    val timestamp: Timestamp? = null
)

@Serializable
data class Mutation (
    val entityKey: String? = null,
    val type: String? = null,
    val payload: Payload? = null
)

@Serializable
data class Payload (
    val offlineabilityEntity: OfflineabilityEntity? = null
)

@Serializable
data class OfflineabilityEntity (
    val key: String? = null,
    val addToOfflineButtonState: String? = null
)

@Serializable
data class Timestamp (
    val seconds: String? = null,
    val nanos: Long? = null
)

@Serializable
class Overlay()

@Serializable
data class PlayabilityStatus (
    val status: String? = null,
    val playableInEmbed: Boolean? = null,
    val contextParams: String? = null
)

@Serializable
data class PlaybackTracking (
    val videostatsPlaybackUrl: URL? = null,
    val videostatsDelayplayUrl: URL? = null,
    val videostatsWatchtimeUrl: URL? = null,
    val ptrackingUrl: URL? = null,
    val qoeUrl: URL? = null,
    val atrUrl: AtrUrl? = null,
    val engageUrl: URL? = null,
    val videostatsScheduledFlushWalltimeSeconds: List<Long>? = null,
    val videostatsDefaultFlushIntervalSeconds: Long? = null
)

@Serializable
data class AtrUrl (
    val baseUrl: String? = null,
    val elapsedMediaTimeSeconds: Long? = null,
    val headers: List<Header>? = null
)

@Serializable
data class Header (
    val headerType: String? = null
)

@Serializable
data class URL (
    val baseUrl: String? = null,
    val headers: List<Header>? = null
)

@Serializable
data class PlayerConfig (
    val audioConfig: AudioConfig? = null,
    val exoPlayerConfig: ExoPlayerConfig? = null,
    val playbackStartConfig: PlaybackStartConfig? = null,
    val adRequestConfig: AdRequestConfig? = null,
    val networkProtocolConfig: NetworkProtocolConfig? = null,
    val androidNetworkStackConfig: AndroidNetworkStackConfig? = null,
    val lidarSdkConfig: LidarSdkConfig? = null,
    val androidMedialibConfig: AndroidMedialibConfig? = null,
    val playerControlsConfig: PlayerControlsConfig? = null,
    val variableSpeedConfig: VariableSpeedConfig? = null,
    val decodeQualityConfig: DecodeQualityConfig? = null,
    val vrConfig: VRConfig? = null,
    val qoeStatsClientConfig: QoeStatsClientConfig? = null,
    val androidPlayerStatsConfig: AndroidPlayerStatsConfig? = null,
    val stickyQualitySelectionConfig: StickyQualitySelectionConfig? = null,
    val adSurveyRequestConfig: AdSurveyRequestConfig? = null,
    val retryConfig: RetryConfig? = null,
    val cmsPathProbeConfig: CMSPathProbeConfig? = null,
    val mediaCommonConfig: MediaCommonConfig? = null,
    val playerGestureConfig: PlayerGestureConfig? = null,
    val taskCoordinatorConfig: TaskCoordinatorConfig? = null
)

@Serializable
data class AdRequestConfig (
    val filterTimeEventsOnDelta: Long? = null,
    val useCriticalExecOnAdsPrep: Boolean? = null,
    val userCriticalExecOnAdsProcessing: Boolean? = null,
    val enableCountdownNextToThumbnailAndroid: Boolean? = null,
    val preskipScalingFactorAndroid: Double? = null,
    val preskipPaddingAndroid: Long? = null
)

@Serializable
data class AdSurveyRequestConfig (
    val useGetRequests: Boolean? = null
)

@Serializable
data class AndroidMedialibConfig (
    val isItag18MainProfile: Boolean? = null,
    val dashManifestVersion: Long? = null,
    val initialBandwidthEstimates: List<InitialBandwidthEstimate>? = null,
    val viewportSizeFraction: Double? = null,
    val enablePrerollPrebuffer: Boolean? = null,
    val prebufferOptimizeForViewportSize: Boolean? = null,
    val hpqViewportSizeFraction: Double? = null
)

@Serializable
data class InitialBandwidthEstimate (
    val detailedNetworkType: String? = null,
    val bandwidthBps: String? = null
)

@Serializable
data class AndroidNetworkStackConfig (
    val networkStack: String? = null,
    val androidCronetResponsePriority: AndroidCronetResponsePriority? = null,
    val androidMetadataNetworkConfig: AndroidMetadataNetworkConfig? = null
)

@Serializable
data class AndroidCronetResponsePriority (
    val priorityValue: String? = null
)

@Serializable
data class AndroidMetadataNetworkConfig (
    val coalesceRequests: Boolean? = null
)

@Serializable
data class AndroidPlayerStatsConfig (
    val usePblForAttestationReporting: Boolean? = null,
    val usePblForHeartbeatReporting: Boolean? = null,
    val usePblForPlaybacktrackingReporting: Boolean? = null,
    val usePblForQoeReporting: Boolean? = null,
    val changeCpnOnFatalPlaybackError: Boolean? = null
)

@Serializable
data class AudioConfig (
    val loudnessDb: Double? = null,
    val perceptualLoudnessDb: Double? = null,
    val enablePerFormatLoudness: Boolean? = null
)

@Serializable
data class CMSPathProbeConfig (
    val cmsPathProbeDelayMs: Long? = null
)

@Serializable
data class DecodeQualityConfig (
    val maximumVideoDecodeVerticalResolution: Long? = null
)

@Serializable
data class ExoPlayerConfig (
    val useExoPlayer: Boolean? = null,
    val useAdaptiveBitrate: Boolean? = null,
    val maxInitialByteRate: Long? = null,
    val minDurationForQualityIncreaseMs: Long? = null,
    val maxDurationForQualityDecreaseMs: Long? = null,
    val minDurationToRetainAfterDiscardMs: Long? = null,
    val lowWatermarkMs: Long? = null,
    val highWatermarkMs: Long? = null,
    val lowPoolLoad: Double? = null,
    val highPoolLoad: Double? = null,
    val sufficientBandwidthOverhead: Double? = null,
    val bufferChunkSizeKb: Long? = null,
    val httpConnectTimeoutMs: Long? = null,
    val httpReadTimeoutMs: Long? = null,
    val numAudioSegmentsPerFetch: Long? = null,
    val numVideoSegmentsPerFetch: Long? = null,
    val minDurationForPlaybackStartMs: Long? = null,
    val enableExoplayerReuse: Boolean? = null,
    val useRadioTypeForInitialQualitySelection: Boolean? = null,
    val blacklistFormatOnError: Boolean? = null,
    val enableBandaidHttpDataSource: Boolean? = null,
    val httpLoadTimeoutMs: Long? = null,
    val canPlayHdDrm: Boolean? = null,
    val videoBufferSegmentCount: Long? = null,
    val audioBufferSegmentCount: Long? = null,
    val useAbruptSplicing: Boolean? = null,
    val minRetryCount: Long? = null,
    val minChunksNeededToPreferOffline: Long? = null,
    val secondsToMaxAggressiveness: Long? = null,
    val enableSurfaceviewResizeWorkaround: Boolean? = null,
    val enableVp9IfThresholdsPass: Boolean? = null,
    val matchQualityToViewportOnUnfullscreen: Boolean? = null,
    val lowAudioQualityConnTypes: List<String>? = null,
    val useDashForLiveStreams: Boolean? = null,
    val enableLibvpxVideoTrackRenderer: Boolean? = null,
    val lowAudioQualityBandwidthThresholdBps: Long? = null,
    val enableVariableSpeedPlayback: Boolean? = null,
    val preferOnesieBufferedFormat: Boolean? = null,
    val minimumBandwidthSampleBytes: Long? = null,
    val useDashForOtfAndCompletedLiveStreams: Boolean? = null,
    val disableCacheAwareVideoFormatEvaluation: Boolean? = null,
    val useLiveDvrForDashLiveStreams: Boolean? = null,
    val cronetResetTimeoutOnRedirects: Boolean? = null,
    val emitVideoDecoderChangeEvents: Boolean? = null,
    val onesieVideoBufferLoadTimeoutMs: String? = null,
    val onesieVideoBufferReadTimeoutMs: String? = null,
    val libvpxEnableGl: Boolean? = null,
    val enableVp9EncryptedIfThresholdsPass: Boolean? = null,
    val enableOpus: Boolean? = null,
    val usePredictedBuffer: Boolean? = null,
    val maxReadAheadMediaTimeMs: Long? = null,
    val useMediaTimeCappedLoadControl: Boolean? = null,
    val allowCacheOverrideToLowerQualitiesWithinRange: Long? = null,
    val allowDroppingUndecodedFrames: Boolean? = null,
    val minDurationForPlaybackRestartMs: Long? = null,
    val serverProvidedBandwidthHeader: String? = null,
    val liveOnlyPegStrategy: String? = null,
    val enableRedirectorHostFallback: Boolean? = null,
    val enableHighlyAvailableFormatFallbackOnPcr: Boolean? = null,
    val recordTrackRendererTimingEvents: Boolean? = null,
    val minErrorsForRedirectorHostFallback: Long? = null,
    val nonHardwareMediaCodecNames: List<String>? = null,
    val enableVp9IfInHardware: Boolean? = null,
    val enableVp9EncryptedIfInHardware: Boolean? = null,
    val useOpusMedAsLowQualityAudio: Boolean? = null,
    val minErrorsForPcrFallback: Long? = null,
    val useStickyRedirectHttpDataSource: Boolean? = null,
    val onlyVideoBandwidth: Boolean? = null,
    val useRedirectorOnNetworkChange: Boolean? = null,
    val enableMaxReadaheadAbrThreshold: Boolean? = null,
    val cacheCheckDirectoryWritabilityOnce: Boolean? = null,
    val predictorType: String? = null,
    val slidingPercentile: Double? = null,
    val slidingWindowSize: Long? = null,
    val maxFrameDropIntervalMs: Long? = null,
    val ignoreLoadTimeoutForFallback: Boolean? = null,
    val serverBweMultiplier: Long? = null,
    val drmMaxKeyfetchDelayMs: Long? = null,
    val maxResolutionForWhiteNoise: Long? = null,
    val whiteNoiseRenderEffectMode: String? = null,
    val enableLibvpxHdr: Boolean? = null,
    val enableCacheAwareStreamSelection: Boolean? = null,
    val useExoCronetDataSource: Boolean? = null,
    val whiteNoiseScale: Long? = null,
    val whiteNoiseOffset: Long? = null,
    val preventVideoFrameLaggingWithLibvpx: Boolean? = null,
    val enableMediaCodecHdr: Boolean? = null,
    val enableMediaCodecSwHdr: Boolean? = null,
    val liveOnlyWindowChunks: Long? = null,
    val bearerMinDurationToRetainAfterDiscardMs: List<Long>? = null,
    val forceWidevineL3: Boolean? = null,
    val useAverageBitrate: Boolean? = null,
    val useMedialibAudioTrackRendererForLive: Boolean? = null,
    val useExoPlayerV2: Boolean? = null,
    val logMediaRequestEventsToCsi: Boolean? = null,
    val onesieFixNonZeroStartTimeFormatSelection: Boolean? = null,
    val liveOnlyReadaheadStepSizeChunks: Long? = null,
    val liveOnlyBufferHealthHalfLifeSeconds: Long? = null,
    val liveOnlyMinBufferHealthRatio: Double? = null,
    val liveOnlyMinLatencyToSeekRatio: Long? = null,
    val manifestlessPartialChunkStrategy: String? = null,
    val ignoreViewportSizeWhenSticky: Boolean? = null,
    val enableLibvpxFallback: Boolean? = null,
    val disableLibvpxLoopFilter: Boolean? = null,
    val enableVpxMediaView: Boolean? = null,
    val hdrMinScreenBrightness: Long? = null,
    val hdrMaxScreenBrightnessThreshold: Long? = null,
    val onesieDataSourceAboveCacheDataSource: Boolean? = null,
    val httpNonplayerLoadTimeoutMs: Long? = null,
    val numVideoSegmentsPerFetchStrategy: String? = null,
    val maxVideoDurationPerFetchMs: Long? = null,
    val maxVideoEstimatedLoadDurationMs: Long? = null,
    val estimatedServerClockHalfLife: Long? = null,
    val estimatedServerClockStrictOffset: Boolean? = null,
    val minReadAheadMediaTimeMs: Long? = null,
    val readAheadGrowthRate: Long? = null,
    val useDynamicReadAhead: Boolean? = null,
    val useYtVodMediaSourceForV2: Boolean? = null,
    val enableV2Gapless: Boolean? = null,
    val useLiveHeadTimeMillis: Boolean? = null,
    val allowTrackSelectionWithUpdatedVideoItagsForExoV2: Boolean? = null,
    val maxAllowableTimeBeforeMediaTimeUpdateSec: Long? = null,
    val enableDynamicHdr: Boolean? = null,
    val v2PerformEarlyStreamSelection: Boolean? = null,
    val v2UsePlaybackStreamSelectionResult: Boolean? = null,
    val v2MinTimeBetweenAbrReevaluationMs: Long? = null,
    val avoidReusePlaybackAcrossLoadvideos: Boolean? = null,
    val checkPlaybackStateBeforeOnPlayingEvent: Boolean? = null,
    val enableInfiniteNetworkLoadingRetries: Boolean? = null,
    val reportExoPlayerStateOnTransition: Boolean? = null,
    val manifestlessSequenceMethod: String? = null,
    val useLiveHeadWindow: Boolean? = null,
    val enableDynamicHdrInHardware: Boolean? = null,
    val ultralowAudioQualityBandwidthThresholdBps: Long? = null,
    val retryLiveNetNocontentWithDelay: Boolean? = null,
    val ignoreUnneededSeeksToLiveHead: Boolean? = null,
    val adaptiveLiveHeadWindow: Boolean? = null,
    val drmMetricsQoeLoggingFraction: Double? = null,
    val liveNetNocontentMaximumErrors: Long? = null,
    val waitForDrmLicenseBeforeProcessingAndroidStuckBufferfull: Boolean? = null,
    val useTimeSeriesBufferPrediction: Boolean? = null,
    val slidingPercentileScalar: Long? = null
)

@Serializable
data class LidarSdkConfig (
    val enableActiveViewReporter: Boolean? = null,
    val useMediaTime: Boolean? = null,
    val sendTosMetrics: Boolean? = null,
    val usePlayerState: Boolean? = null,
    val enableIosAppStateCheck: Boolean? = null,
    val enableImprovedSizeReportingAndroid: Boolean? = null,
    val enableIsAndroidVideoAlwaysMeasurable: Boolean? = null,
    val enableActiveViewAudioMeasurementAndroid: Boolean? = null
)

@Serializable
data class MediaCommonConfig (
    val dynamicReadaheadConfig: DynamicReadaheadConfig? = null,
    val mediaUstreamerRequestConfig: MediaUstreamerRequestConfig? = null,
    val predictedReadaheadConfig: PredictedReadaheadConfig? = null,
    val mediaFetchRetryConfig: MediaFetchRetryConfig? = null,
    val mediaFetchMaximumServerErrors: Long? = null,
    val mediaFetchMaximumNetworkErrors: Long? = null,
    val mediaFetchMaximumErrors: Long? = null,
    val serverReadaheadConfig: ServerReadaheadConfig? = null,
    val useServerDrivenAbr: Boolean? = null
)

@Serializable
data class DynamicReadaheadConfig (
    val maxReadAheadMediaTimeMs: Long? = null,
    val minReadAheadMediaTimeMs: Long? = null,
    val readAheadGrowthRateMs: Long? = null,
    val readAheadWatermarkMarginRatio: Long? = null,
    val minReadAheadWatermarkMarginMs: Long? = null,
    val maxReadAheadWatermarkMarginMs: Long? = null,
    val shouldIncorporateNetworkActiveState: Boolean? = null
)

@Serializable
data class MediaFetchRetryConfig (
    val initialDelayMs: Long? = null,
    val backoffFactor: Double? = null,
    val maximumDelayMs: Long? = null,
    val jitterFactor: Double? = null
)

@Serializable
data class MediaUstreamerRequestConfig (
    val enableVideoPlaybackRequest: Boolean? = null,
    val videoPlaybackUstreamerConfig: String? = null,
    val videoPlaybackPostEmptyBody: Boolean? = null,
    val isVideoPlaybackRequestIdempotent: Boolean? = null
)

@Serializable
data class PredictedReadaheadConfig (
    val minReadaheadMs: Long? = null,
    val maxReadaheadMs: Long? = null
)

@Serializable
data class ServerReadaheadConfig (
    val enable: Boolean? = null,
    val nextRequestPolicy: NextRequestPolicy? = null
)

@Serializable
data class NextRequestPolicy (
    val targetAudioReadaheadMs: Long? = null,
    val targetVideoReadaheadMs: Long? = null
)

@Serializable
data class NetworkProtocolConfig (
    val useQuic: Boolean? = null
)

@Serializable
data class PlaybackStartConfig (
    val startTimeToleranceBeforeMs: String? = null
)

@Serializable
data class PlayerControlsConfig (
    val showCachedInTimebar: Boolean? = null
)

@Serializable
data class PlayerGestureConfig (
    val downAndOutLandscapeAllowed: Boolean? = null,
    val downAndOutPortraitAllowed: Boolean? = null
)

@Serializable
data class QoeStatsClientConfig (
    val batchedEntriesPeriodMs: String? = null
)

@Serializable
data class RetryConfig (
    val retryEligibleErrors: List<String>? = null,
    val retryUnderSameConditionAttempts: Long? = null,
    val retryWithNewSurfaceAttempts: Long? = null,
    val progressiveFallbackOnNonNetworkErrors: Boolean? = null,
    val l3FallbackOnDrmErrors: Boolean? = null,
    val retryAfterCacheRemoval: Boolean? = null,
    val widevineL3EnforcedFallbackOnDrmErrors: Boolean? = null,
    val exoProxyableFormatFallback: Boolean? = null,
    val maxPlayerRetriesWhenNetworkUnavailable: Long? = null,
    val retryWithLibvpx: Boolean? = null,
    val suppressFatalErrorAfterStop: Boolean? = null,
    val fallbackFromHfrToSfrOnFormatDecodeError: Boolean? = null,
    val fallbackToSwDecoderOnFormatDecodeError: Boolean? = null
)

@Serializable
data class StickyQualitySelectionConfig (
    val stickySelectionType: String? = null,
    val expirationTimeSinceLastManualVideoQualitySelectionMs: String? = null,
    val expirationTimeSinceLastPlaybackStartMs: String? = null,
    val stickyCeilingOverridesSimpleBitrateCap: Boolean? = null
)

@Serializable
data class TaskCoordinatorConfig (
    val prefetchCoordinatorBufferedPositionMillisRelease: Long? = null
)

@Serializable
data class VariableSpeedConfig (
    val availablePlaybackSpeeds: List<AvailablePlaybackSpeed>? = null,
    val androidVariableSpeedTimeoutSecs: Long? = null,
    val enableVariableSpeedOnOtf: Boolean? = null
)

@Serializable
data class AvailablePlaybackSpeed (
    val label: Label? = null,
    val value: Double? = null
)

@Serializable
data class Label (
    val runs: List<LabelRun>? = null
)

@Serializable
data class LabelRun (
    val text: String? = null
)

@Serializable
data class VRConfig (
    val allowVr: Boolean? = null,
    val allowSubtitles: Boolean? = null,
    val showHqButton: Boolean? = null,
    val sphericalDirectionLoggingEnabled: Boolean? = null,
    val enableAndroidVr180MagicWindow: Boolean? = null,
    val enableAndroidMagicWindowEduOverlay: Boolean? = null,
    val magicWindowEduOverlayText: String? = null,
    val magicWindowEduOverlayAnimationUrl: String? = null
)

@Serializable
data class PlayerSettingsMenuData (
    val loggingDirectives: LoggingDirectives? = null
)

@Serializable
data class LoggingDirectives (
    val trackingParams: String? = null,
    val visibility: Visibility? = null,
    val enableDisplayloggerExperiment: Boolean? = null
)

@Serializable
data class Visibility (
    val types: String? = null
)

@Serializable
data class ResponseContext (
    val visitorData: String? = null,
    val serviceTrackingParams: List<ServiceTrackingParam>? = null
)

@Serializable
data class ServiceTrackingParam (
    val service: String? = null,
    val params: List<Param>? = null
)

@Serializable
data class Param (
    val key: String? = null,
    val value: String? = null
)

@Serializable
data class Storyboards (
    val playerStoryboardSpecRenderer: PlayerStoryboardSpecRenderer? = null
)

@Serializable
data class PlayerStoryboardSpecRenderer (
    val spec: String? = null,
    val recommendedLevel: Long? = null
)

@Serializable
data class StreamingData (
    val expiresInSeconds: Long? = null,
    val formats: List<Format>? = null,
    val adaptiveFormats: List<Format>? = null
)

@Serializable
data class Format (
    val itag: Long? = null,
    val url: String? = null,
    val mimeType: String? = null,
    val bitrate: Long? = null,
    val width: Long? = null,
    val height: Long? = null,
    val initRange: Range? = null,
    val indexRange: Range? = null,
    val lastModified: String? = null,
    val contentLength: Long? = null,
    val quality: String? = null,
    val fps: Long? = null,
    val qualityLabel: String? = null,
    val projectionType: String? = null,
    val averageBitrate: Long? = null,
    val approxDurationMs: String? = null,
    val colorInfo: ColorInfo? = null,
    val highReplication: Boolean? = null,
    val audioQuality: String? = null,
    val audioSampleRate: String? = null,
    val audioChannels: Long? = null,
    val loudnessDb: Double? = null
)

@Serializable
data class ColorInfo (
    val primaries: String? = null,
    val transferCharacteristics: String? = null,
    val matrixCoefficients: String? = null
)

@Serializable
data class Range (
    val start: String? = null,
    val end: String? = null
)

@Serializable
data class VideoDetails (
    val videoId: String? = null,
    val title: String? = null,
    val lengthSeconds: String? = null,
    val channelId: String? = null,
    val isOwnerViewing: Boolean? = null,
    val shortDescription: String? = null,
    val isCrawlable: Boolean? = null,
    val thumbnail: VideoDetailsThumbnail? = null,
    val allowRatings: Boolean? = null,
    val author: String? = null,
    val isPrivate: Boolean? = null,
    val isUnpluggedCorpus: Boolean? = null,
    val isLiveContent: Boolean? = null
)

@Serializable
data class VideoDetailsThumbnail (
    val thumbnails: List<Thumbnail>? = null
)

@Serializable
data class VideoQualityPromoSupportedRenderers (
    val videoQualityPromoRenderer: VideoQualityPromoRenderer? = null
)

@Serializable
data class VideoQualityPromoRenderer (
    val triggerCriteria: TriggerCriteria? = null,
    val text: Text? = null,
    val endpoint: Endpoint? = null,
    val trackingParams: String? = null,
    val closeButton: CloseButton? = null,
    val snackbar: Snackbar? = null
)

@Serializable
data class CloseButton (
    val videoQualityPromoCloseRenderer: VideoQualityPromoCloseRenderer? = null
)

@Serializable
data class VideoQualityPromoCloseRenderer (
    val trackingParams: String? = null
)

@Serializable
data class Endpoint (
    val clickTrackingParams: String? = null,
    val urlEndpoint: URLEndpoint? = null
)

@Serializable
data class URLEndpoint (
    val url: String? = null
)

@Serializable
data class Snackbar (
    val notificationActionRenderer: NotificationActionRenderer? = null
)

@Serializable
data class NotificationActionRenderer (
    val responseText: Label? = null,
    val actionButton: ActionButton? = null,
    val trackingParams: String? = null
)

@Serializable
data class ActionButton (
    val buttonRenderer: ButtonRenderer? = null
)

@Serializable
data class ButtonRenderer (
    val text: Label? = null,
    val navigationEndpoint: Endpoint? = null,
    val trackingParams: String? = null
)

@Serializable
data class Text (
    val runs: List<PurpleRun>? = null
)

@Serializable
data class PurpleRun (
    val text: String? = null,
    val bold: Boolean? = null
)

@Serializable
data class TriggerCriteria (
    val connectionWhitelist: List<String>? = null,
    val joinLatencySeconds: Long? = null,
    val rebufferTimeSeconds: Long? = null,
    val watchTimeWindowSeconds: Long? = null,
    val refractorySeconds: Long? = null
)