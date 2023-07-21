package dvp.lib.ytube.dto.init


import kotlinx.serialization.Serializable

@Serializable
data class NextResponse(
    val responseContext: ResponseContext? = null,
    val trackingParams: String? = null,
    val onResponseReceivedActions: List<OnResponseReceivedAction>? = null
) {

    @Serializable
    data class OnResponseReceivedAction(
        val clickTrackingParams: String? = null,
        val appendContinuationItemsAction: AppendContinuationItemsAction? = null
    )

    @Serializable
    data class AppendContinuationItemsAction(
        val continuationItems: List<ContinuationItem>? = null,
        val targetId: String? = null
    )

    @Serializable
    data class ContinuationItem(
        val richItemRenderer: RichItemRenderer? = null,
        val continuationItemRenderer: ContinuationItemRenderer? = null
    )

    @Serializable
    data class ContinuationItemRenderer(
        val trigger: String? = null,
        val continuationEndpoint: ContinuationEndpoint? = null,
        val ghostCards: GhostCards? = null
    )

    @Serializable
    data class ContinuationEndpoint(
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val continuationCommand: ContinuationCommand? = null
    )

    @Serializable
    data class ContinuationEndpointCommandMetadata(
        val webCommandMetadata: PurpleWebCommandMetadata? = null
    )

    @Serializable
    data class PurpleWebCommandMetadata(
        val sendPost: Boolean? = null,
        val apiUrl: String? = null
    )

    @Serializable
    data class ContinuationCommand(
        val token: String? = null,
        val request: String? = null
    )

    @Serializable
    data class GhostCards(
        val ghostGridRenderer: GhostGridRenderer? = null
    )

    @Serializable
    data class GhostGridRenderer(
        val rows: Long? = null
    )

    @Serializable
    data class RichItemRenderer(
        val content: Content? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class Content(
        val videoRenderer: InitResponse.VideoRenderer? = null,
        val radioRenderer: RadioRenderer? = null
    )

    @Serializable
    data class RadioRenderer(
        val playlistId: String? = null,
        val title: LongBylineText? = null,
        val thumbnail: RadioRendererThumbnail? = null,
        val videoCountText: VideoCountShortText? = null,
        val navigationEndpoint: RadioRendererNavigationEndpoint? = null,
        val trackingParams: String? = null,
        val videos: List<Video>? = null,
        val thumbnailText: ThumbnailText? = null,
        val longBylineText: LongBylineText? = null,
        val menu: RadioRendererMenu? = null,
        val thumbnailOverlays: List<RadioRendererThumbnailOverlay>? = null,
        val videoCountShortText: VideoCountShortText? = null
    )

    @Serializable
    data class LongBylineText(
        val simpleText: String? = null
    )

    @Serializable
    data class RadioRendererMenu(
        val menuRenderer: PurpleMenuRenderer? = null
    )

    @Serializable
    data class PurpleMenuRenderer(
        val items: List<PurpleItem>? = null,
        val trackingParams: String? = null,
        val accessibility: Accessibility? = null
    )

    @Serializable
    data class Accessibility(
        val accessibilityData: AccessibilityData? = null
    )

    @Serializable
    data class AccessibilityData(
        val label: String? = null
    )

    @Serializable
    data class PurpleItem(
        val menuServiceItemRenderer: PurpleMenuServiceItemRenderer? = null
    )

    @Serializable
    data class PurpleMenuServiceItemRenderer(
        val text: VideoCountShortText? = null,
        val icon: Icon? = null,
        val serviceEndpoint: PurpleServiceEndpoint? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class Icon(
        val iconType: String? = null
    )

    @Serializable
    data class PurpleServiceEndpoint(
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val feedbackEndpoint: PurpleFeedbackEndpoint? = null
    )

    @Serializable
    data class PurpleFeedbackEndpoint(
        val feedbackToken: String? = null,
        val uiActions: UIActions? = null,
        val actions: List<PurpleAction>? = null
    )

    @Serializable
    data class PurpleAction(
        val clickTrackingParams: String? = null,
        val replaceEnclosingAction: PurpleReplaceEnclosingAction? = null
    )

    @Serializable
    data class PurpleReplaceEnclosingAction(
        val item: FluffyItem? = null
    )

    @Serializable
    data class FluffyItem(
        val notificationTextRenderer: NotificationTextRenderer? = null
    )

    @Serializable
    data class NotificationTextRenderer(
        val successResponseText: VideoCountShortText? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class VideoCountShortText(
        val runs: List<VideoCountShortTextRun>? = null
    )

    @Serializable
    data class VideoCountShortTextRun(
        val text: String? = null
    )

    @Serializable
    data class UIActions(
        val hideEnclosingContainer: Boolean? = null
    )

    @Serializable
    data class RadioRendererNavigationEndpoint(
        val clickTrackingParams: String? = null,
        val commandMetadata: InlinePlaybackEndpointCommandMetadata? = null,
        val watchEndpoint: PurpleWatchEndpoint? = null
    )

    @Serializable
    data class InlinePlaybackEndpointCommandMetadata(
        val webCommandMetadata: FluffyWebCommandMetadata? = null
    )

    @Serializable
    data class FluffyWebCommandMetadata(
        val url: String? = null,
        val webPageType: String? = null,
        val rootVe: Long? = null
    )

    @Serializable
    data class PurpleWatchEndpoint(
        val videoId: String? = null,
        val playlistId: String? = null,
        val params: String? = null,
        val continuePlayback: Boolean? = null,
        val loggingContext: LoggingContext? = null,
        val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig? = null
    )

    @Serializable
    data class LoggingContext(
        val vssLoggingContext: VssLoggingContext? = null
    )

    @Serializable
    data class VssLoggingContext(
        val serializedContextData: String? = null
    )

    @Serializable
    data class WatchEndpointSupportedOnesieConfig(
        val html5PlaybackOnesieConfig: Html5PlaybackOnesieConfig? = null
    )

    @Serializable
    data class Html5PlaybackOnesieConfig(
        val commonConfig: CommonConfig? = null
    )

    @Serializable
    data class CommonConfig(
        val url: String? = null
    )

    @Serializable
    data class RadioRendererThumbnail(
        val thumbnails: List<PurpleThumbnail>? = null,
        val sampledThumbnailColor: SampledThumbnailColor? = null
    )

    @Serializable
    data class SampledThumbnailColor(
        val red: Long? = null,
        val green: Long? = null,
        val blue: Long? = null
    )

    @Serializable
    data class PurpleThumbnail(
        val url: String? = null,
        val width: Long? = null,
        val height: Long? = null
    )

    @Serializable
    data class RadioRendererThumbnailOverlay(
        val thumbnailOverlayBottomPanelRenderer: ThumbnailOverlayBottomPanelRenderer? = null,
        val thumbnailOverlayHoverTextRenderer: ThumbnailOverlayHoverTextRendererClass? = null,
        val thumbnailOverlayNowPlayingRenderer: ThumbnailOverlayNowPlayingRendererClass? = null
    )

    @Serializable
    data class ThumbnailOverlayBottomPanelRenderer(
        val icon: Icon? = null
    )

    @Serializable
    data class ThumbnailOverlayHoverTextRendererClass(
        val text: VideoCountShortText? = null,
        val icon: Icon? = null
    )

    @Serializable
    data class ThumbnailOverlayNowPlayingRendererClass(
        val text: VideoCountShortText? = null
    )

    @Serializable
    data class ThumbnailText(
        val runs: List<ThumbnailTextRun>? = null
    )

    @Serializable
    data class ThumbnailTextRun(
        val text: String? = null,
        val bold: Boolean? = null
    )

    @Serializable
    data class Video(
        val childVideoRenderer: ChildVideoRenderer? = null
    )

    @Serializable
    data class ChildVideoRenderer(
        val title: LongBylineText? = null,
        val navigationEndpoint: ChildVideoRendererNavigationEndpoint? = null,
        val lengthText: LengthTextClass? = null,
        val videoId: String? = null
    )

    @Serializable
    data class LengthTextClass(
        val accessibility: Accessibility? = null,
        val simpleText: String? = null
    )

    @Serializable
    data class ChildVideoRendererNavigationEndpoint(
        val clickTrackingParams: String? = null,
        val commandMetadata: InlinePlaybackEndpointCommandMetadata? = null,
        val watchEndpoint: FluffyWatchEndpoint? = null
    )

    @Serializable
    data class FluffyWatchEndpoint(
        val videoId: String? = null,
        val playlistId: String? = null,
        val params: String? = null,
        val loggingContext: LoggingContext? = null,
        val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig? = null
    )

//    @Serializable
//    data class VideoRenderer(
//        val videoId: String? = null,
//        val thumbnail: VideoRendererThumbnail? = null,
//        val title: Title? = null,
//        val descriptionSnippet: VideoCountShortText? = null,
//        val longBylineText: LongBylineTextClass? = null,
//        val publishedTimeText: LongBylineText? = null,
//        val lengthText: LengthTextClass? = null,
//        val viewCountText: ViewCountTextClass? = null,
//        val navigationEndpoint: VideoRendererNavigationEndpoint? = null,
//        val ownerText: LongBylineTextClass? = null,
//        val shortBylineText: LongBylineTextClass? = null,
//        val trackingParams: String? = null,
//        val showActionMenu: Boolean? = null,
//        val shortViewCountText: ShortViewCountTextClass? = null,
//        val menu: VideoRendererMenu? = null,
//        val channelThumbnailSupportedRenderers: ChannelThumbnailSupportedRenderers? = null,
//        val thumbnailOverlays: List<VideoRendererThumbnailOverlay>? = null,
//        val inlinePlaybackEndpoint: InlinePlaybackEndpoint? = null,
//        val ownerBadges: List<OwnerBadge>? = null,
//        val badges: List<Badge>? = null
//    )

    @Serializable
    data class Badge(
        val metadataBadgeRenderer: BadgeMetadataBadgeRenderer? = null
    )

    @Serializable
    data class BadgeMetadataBadgeRenderer(
        val icon: Icon? = null,
        val style: String? = null,
        val label: String? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class ChannelThumbnailSupportedRenderers(
        val channelThumbnailWithLinkRenderer: ChannelThumbnailWithLinkRenderer? = null
    )

    @Serializable
    data class ChannelThumbnailWithLinkRenderer(
        val thumbnail: ChannelThumbnailWithLinkRendererThumbnail? = null,
        val navigationEndpoint: ChannelThumbnailWithLinkRendererNavigationEndpoint? = null,
        val accessibility: Accessibility? = null
    )

    @Serializable
    data class ChannelThumbnailWithLinkRendererNavigationEndpoint(
        val clickTrackingParams: String? = null,
        val commandMetadata: PurpleCommandMetadata? = null,
        val browseEndpoint: BrowseEndpoint? = null
    )

    @Serializable
    data class BrowseEndpoint(
        val browseId: String? = null,
        val canonicalBaseUrl: String? = null
    )

    @Serializable
    data class PurpleCommandMetadata(
        val webCommandMetadata: TentacledWebCommandMetadata? = null
    )

    @Serializable
    data class TentacledWebCommandMetadata(
        val url: String? = null,
        val webPageType: String? = null,
        val rootVe: Long? = null,
        val apiUrl: String? = null
    )

    @Serializable
    data class ChannelThumbnailWithLinkRendererThumbnail(
        val thumbnails: List<FluffyThumbnail>? = null
    )

    @Serializable
    data class FluffyThumbnail(
        val url: String? = null,
        val width: Long? = null,
        val height: Long? = null
    )

    @Serializable
    data class InlinePlaybackEndpoint(
        val clickTrackingParams: String? = null,
        val commandMetadata: InlinePlaybackEndpointCommandMetadata? = null,
        val watchEndpoint: InlinePlaybackEndpointWatchEndpoint? = null
    )

    @Serializable
    data class InlinePlaybackEndpointWatchEndpoint(
        val videoId: String? = null,
        val playerParams: String? = null,
        val playerExtraUrlParams: List<Param>? = null,
        val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig? = null
    )

    @Serializable
    data class Param(
        val key: String? = null,
        val value: String? = null
    )

    @Serializable
    data class LongBylineTextClass(
        val runs: List<LongBylineTextRun>? = null
    )

    @Serializable
    data class LongBylineTextRun(
        val text: String? = null,
        val navigationEndpoint: ChannelThumbnailWithLinkRendererNavigationEndpoint? = null
    )

    @Serializable
    data class VideoRendererMenu(
        val menuRenderer: FluffyMenuRenderer? = null
    )

    @Serializable
    data class FluffyMenuRenderer(
        val items: List<TentacledItem>? = null,
        val trackingParams: String? = null,
        val accessibility: Accessibility? = null
    )

    @Serializable
    data class TentacledItem(
        val menuServiceItemRenderer: FluffyMenuServiceItemRenderer? = null
    )

    @Serializable
    data class FluffyMenuServiceItemRenderer(
        val text: VideoCountShortText? = null,
        val icon: Icon? = null,
        val serviceEndpoint: FluffyServiceEndpoint? = null,
        val trackingParams: String? = null,
        val hasSeparator: Boolean? = null
    )

    @Serializable
    data class FluffyServiceEndpoint(
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val signalServiceEndpoint: UntoggledServiceEndpointSignalServiceEndpoint? = null,
        val playlistEditEndpoint: ServiceEndpointPlaylistEditEndpoint? = null,
        val addToPlaylistServiceEndpoint: AddToPlaylistServiceEndpoint? = null,
        val shareEntityServiceEndpoint: ShareEntityServiceEndpoint? = null,
        val feedbackEndpoint: FluffyFeedbackEndpoint? = null,
        val getReportFormEndpoint: GetReportFormEndpoint? = null
    )

    @Serializable
    data class AddToPlaylistServiceEndpoint(
        val videoId: String? = null
    )

    @Serializable
    data class FluffyFeedbackEndpoint(
        val feedbackToken: String? = null,
        val uiActions: UIActions? = null,
        val actions: List<FluffyAction>? = null
    )

    @Serializable
    data class FluffyAction(
        val clickTrackingParams: String? = null,
        val replaceEnclosingAction: FluffyReplaceEnclosingAction? = null
    )

    @Serializable
    data class FluffyReplaceEnclosingAction(
        val item: StickyItem? = null
    )

    @Serializable
    data class StickyItem(
        val notificationMultiActionRenderer: NotificationMultiActionRenderer? = null
    )

    @Serializable
    data class NotificationMultiActionRenderer(
        val responseText: ShortViewCountTextClass? = null,
        val buttons: List<Button>? = null,
        val trackingParams: String? = null,
        val dismissalViewStyle: String? = null
    )

    @Serializable
    data class Button(
        val buttonRenderer: ButtonRenderer? = null
    )

    @Serializable
    data class ButtonRenderer(
        val style: String? = null,
        val text: ViewCountTextClass? = null,
        val serviceEndpoint: ButtonRendererServiceEndpoint? = null,
        val trackingParams: String? = null,
        val command: ButtonRendererCommand? = null
    )

    @Serializable
    data class ButtonRendererCommand(
        val clickTrackingParams: String? = null,
        val commandMetadata: InlinePlaybackEndpointCommandMetadata? = null,
        val urlEndpoint: URLEndpoint? = null
    )

    @Serializable
    data class URLEndpoint(
        val url: String? = null,
        val target: String? = null
    )

    @Serializable
    data class ButtonRendererServiceEndpoint(
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val undoFeedbackEndpoint: UndoFeedbackEndpoint? = null,
        val signalServiceEndpoint: PurpleSignalServiceEndpoint? = null
    )

    @Serializable
    data class PurpleSignalServiceEndpoint(
        val signal: String? = null,
        val actions: List<TentacledAction>? = null
    )

    @Serializable
    data class TentacledAction(
        val clickTrackingParams: String? = null,
        val signalAction: SignalAction? = null
    )

    @Serializable
    data class SignalAction(
        val signal: String? = null
    )

    @Serializable
    data class UndoFeedbackEndpoint(
        val undoToken: String? = null,
        val actions: List<UndoFeedbackEndpointAction>? = null
    )

    @Serializable
    data class UndoFeedbackEndpointAction(
        val clickTrackingParams: String? = null,
        val undoFeedbackAction: UndoFeedbackAction? = null
    )

    @Serializable
    data class UndoFeedbackAction(
        val hack: Boolean? = null
    )

    @Serializable
    data class ViewCountTextClass(
        val simpleText: String? = null,
        val runs: List<VideoCountShortTextRun>? = null
    )

    @Serializable
    data class ShortViewCountTextClass(
        val accessibility: Accessibility? = null,
        val simpleText: String? = null,
        val runs: List<VideoCountShortTextRun>? = null
    )

    @Serializable
    data class GetReportFormEndpoint(
        val params: String? = null
    )

    @Serializable
    data class ServiceEndpointPlaylistEditEndpoint(
        val playlistId: String? = null,
        val actions: List<StickyAction>? = null
    )

    @Serializable
    data class StickyAction(
        val addedVideoId: String? = null,
        val action: String? = null
    )

    @Serializable
    data class ShareEntityServiceEndpoint(
        val serializedShareEntity: String? = null,
        val commands: List<CommandElement>? = null
    )

    @Serializable
    data class CommandElement(
        val clickTrackingParams: String? = null,
        val openPopupAction: OpenPopupAction? = null
    )

    @Serializable
    data class OpenPopupAction(
        val popup: Popup? = null,
        val popupType: String? = null,
        val beReused: Boolean? = null
    )

    @Serializable
    data class Popup(
        val unifiedSharePanelRenderer: UnifiedSharePanelRenderer? = null
    )

    @Serializable
    data class UnifiedSharePanelRenderer(
        val trackingParams: String? = null,
        val showLoadingSpinner: Boolean? = null
    )

    @Serializable
    data class UntoggledServiceEndpointSignalServiceEndpoint(
        val signal: String? = null,
        val actions: List<IndigoAction>? = null
    )

    @Serializable
    data class IndigoAction(
        val clickTrackingParams: String? = null,
        val addToPlaylistCommand: AddToPlaylistCommand? = null
    )

    @Serializable
    data class AddToPlaylistCommand(
        val openMiniplayer: Boolean? = null,
        val videoId: String? = null,
        val listType: String? = null,
        val onCreateListCommand: OnCreateListCommand? = null,
        val videoIds: List<String>? = null
    )

    @Serializable
    data class OnCreateListCommand(
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val createPlaylistServiceEndpoint: CreatePlaylistServiceEndpoint? = null
    )

    @Serializable
    data class CreatePlaylistServiceEndpoint(
        val videoIds: List<String>? = null,
        val params: String? = null
    )

    @Serializable
    data class VideoRendererNavigationEndpoint(
        val clickTrackingParams: String? = null,
        val commandMetadata: InlinePlaybackEndpointCommandMetadata? = null,
        val watchEndpoint: TentacledWatchEndpoint? = null
    )

    @Serializable
    data class TentacledWatchEndpoint(
        val videoId: String? = null,
        val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig? = null
    )

    @Serializable
    data class OwnerBadge(
        val metadataBadgeRenderer: OwnerBadgeMetadataBadgeRenderer? = null
    )

    @Serializable
    data class OwnerBadgeMetadataBadgeRenderer(
        val icon: Icon? = null,
        val style: String? = null,
        val tooltip: String? = null,
        val trackingParams: String? = null,
        val accessibilityData: AccessibilityData? = null
    )

    @Serializable
    data class VideoRendererThumbnail(
        val thumbnails: List<PurpleThumbnail>? = null
    )

    @Serializable
    data class VideoRendererThumbnailOverlay(
        val thumbnailOverlayTimeStatusRenderer: ThumbnailOverlayTimeStatusRenderer? = null,
        val thumbnailOverlayToggleButtonRenderer: ThumbnailOverlayToggleButtonRenderer? = null,
        val thumbnailOverlayNowPlayingRenderer: ThumbnailOverlayNowPlayingRendererClass? = null,
        val thumbnailOverlayLoadingPreviewRenderer: ThumbnailOverlayNowPlayingRendererClass? = null,
        val thumbnailOverlayInlineUnplayableRenderer: ThumbnailOverlayHoverTextRendererClass? = null,
        val thumbnailOverlayEndorsementRenderer: ThumbnailOverlayEndorsementRenderer? = null
    )

    @Serializable
    data class ThumbnailOverlayEndorsementRenderer(
        val text: VideoCountShortText? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class ThumbnailOverlayTimeStatusRenderer(
        val text: LengthTextClass? = null,
        val style: String? = null
    )

    @Serializable
    data class ThumbnailOverlayToggleButtonRenderer(
        val isToggled: Boolean? = null,
        val untoggledIcon: Icon? = null,
        val toggledIcon: Icon? = null,
        val untoggledTooltip: String? = null,
        val toggledTooltip: String? = null,
        val untoggledServiceEndpoint: UntoggledServiceEndpoint? = null,
        val toggledServiceEndpoint: ToggledServiceEndpoint? = null,
        val untoggledAccessibility: Accessibility? = null,
        val toggledAccessibility: Accessibility? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class ToggledServiceEndpoint(
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val playlistEditEndpoint: ToggledServiceEndpointPlaylistEditEndpoint? = null
    )

    @Serializable
    data class ToggledServiceEndpointPlaylistEditEndpoint(
        val playlistId: String? = null,
        val actions: List<IndecentAction>? = null
    )

    @Serializable
    data class IndecentAction(
        val action: String? = null,
        val removedVideoId: String? = null
    )

    @Serializable
    data class UntoggledServiceEndpoint(
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val playlistEditEndpoint: ServiceEndpointPlaylistEditEndpoint? = null,
        val signalServiceEndpoint: UntoggledServiceEndpointSignalServiceEndpoint? = null
    )

    @Serializable
    data class Title(
        val runs: List<VideoCountShortTextRun>? = null,
        val accessibility: Accessibility? = null
    )

    @Serializable
    data class ResponseContext(
        val visitorData: String? = null,
        val serviceTrackingParams: List<ServiceTrackingParam>? = null,
        val maxAgeSeconds: Long? = null,
        val mainAppWebResponseContext: MainAppWebResponseContext? = null,
        val webResponseContextExtensionData: WebResponseContextExtensionData? = null
    )

    @Serializable
    data class MainAppWebResponseContext(
        val datasyncId: String? = null,
        val loggedOut: Boolean? = null
    )

    @Serializable
    data class ServiceTrackingParam(
        val service: String? = null,
        val params: List<Param>? = null
    )

    @Serializable
    data class WebResponseContextExtensionData(
        val hasDecorated: Boolean? = null
    )
}
