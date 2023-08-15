package dvp.lib.ytube.dto.init


import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class InitResponse (
    val responseContext: ResponseContext? = null,
    val contents: Contents? = null,
    val header: YoutubeBaseResponseHeader? = null,
    val trackingParams: String? = null,
    val topbar: Topbar? = null,
    val frameworkUpdates: FrameworkUpdates? = null
) {
    @Serializable
    data class Contents (
        val twoColumnBrowseResultsRenderer: TwoColumnBrowseResultsRenderer? = null
    )


    @Serializable
    data class TwoColumnBrowseResultsRenderer (
        val tabs: List<Tab>? = null
    )

    @Serializable
    data class Tab (
        val tabRenderer: TabRenderer? = null
    )

    @Serializable
    data class TabRenderer (
        val selected: Boolean? = null,
        val content: TabRendererContent? = null,
        val tabIdentifier: String? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class TabRendererContent (
        val richGridRenderer: RichGridRenderer? = null
    )

    @Serializable
    data class RichGridRenderer (
        val contents: List<RichGridRendererContent>? = null,
        val trackingParams: String? = null,
        val header: RichGridRendererHeader? = null,
        val targetId: String? = null,
        val reflowOptions: ReflowOptions? = null
    )

    @Serializable
    data class RichGridRendererContent (
        val richItemRenderer: PurpleRichItemRenderer? = null,
        val richSectionRenderer: RichSectionRenderer? = null,
        val continuationItemRenderer: ContinuationItemRenderer? = null
    )

    @Serializable
    data class ContinuationItemRenderer (
        val trigger: String? = null,
        val continuationEndpoint: ContinuationEndpoint? = null,
        val ghostCards: GhostCards? = null
    )

    @Serializable
    data class ContinuationEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val continuationCommand: ContinuationEndpointContinuationCommand? = null
    )

    @Serializable
    data class ContinuationEndpointCommandMetadata (
        val webCommandMetadata: PurpleWebCommandMetadata? = null
    )

    @Serializable
    data class PurpleWebCommandMetadata (
        val sendPost: Boolean? = null,
        val apiUrl: String? = null
    )

    @Serializable
    data class ContinuationEndpointContinuationCommand (
        val token: String? = null,
        val request: String? = null
    )

    @Serializable
    data class GhostCards (
        val ghostGridRenderer: GhostGridRenderer? = null
    )

    @Serializable
    data class GhostGridRenderer (
        val rows: Long? = null
    )

    @Serializable
    data class PurpleRichItemRenderer (
        val content: PurpleContent? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class PurpleContent (
        val videoRenderer: VideoRenderer? = null
    )

    @Serializable
    data class VideoRenderer (
        val videoId: String? = null,
        val thumbnail: VideoRendererThumbnail? = null,
        val title: PurpleTitle? = null,
        val descriptionSnippet: TextClass? = null,
        val longBylineText: Text? = null,
        val publishedTimeText: PublishedTimeText? = null,
        val lengthText: LengthTextClass? = null,
        val viewCountText: PublishedTimeText? = null,
        val navigationEndpoint: VideoRendererNavigationEndpoint? = null,
        val ownerBadges: List<Badge>? = null,
        val ownerText: Text? = null,
        val shortBylineText: Text? = null,
        val trackingParams: String? = null,
        val showActionMenu: Boolean? = null,
        val shortViewCountText: LengthTextClass? = null,
        val menu: VideoRendererMenu? = null,
        val channelThumbnailSupportedRenderers: ChannelThumbnailSupportedRenderers? = null,
        val thumbnailOverlays: List<ThumbnailOverlay>? = null,
        val inlinePlaybackEndpoint: InlinePlaybackEndpoint? = null
    )

    @Serializable
    data class ChannelThumbnailSupportedRenderers (
        val channelThumbnailWithLinkRenderer: ChannelThumbnailWithLinkRenderer? = null
    )

    @Serializable
    data class ChannelThumbnailWithLinkRenderer (
        val thumbnail: ChannelThumbnailWithLinkRendererThumbnail? = null,
        val navigationEndpoint: ChannelThumbnailWithLinkRendererNavigationEndpoint? = null,
        val accessibility: AccessibilityData? = null
    )

    @Serializable
    data class AccessibilityData (
        val accessibilityData: Accessibility? = null
    )

    @Serializable
    data class Accessibility (
        val label: String? = null
    )

    @Serializable
    data class ChannelThumbnailWithLinkRendererNavigationEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: EndpointCommandMetadata? = null,
        val browseEndpoint: NavigationEndpointBrowseEndpoint? = null
    )

    @Serializable
    data class NavigationEndpointBrowseEndpoint (
        val browseId: String? = null,
        val canonicalBaseUrl: String? = null
    )

    @Serializable
    data class EndpointCommandMetadata (
        val webCommandMetadata: FluffyWebCommandMetadata? = null
    )

    @Serializable
    data class FluffyWebCommandMetadata (
        val url: String? = null,
        val webPageType: String? = null,
        val rootVe: Long? = null,
        val apiUrl: String? = null
    )

    @Serializable
    data class ChannelThumbnailWithLinkRendererThumbnail (
        val thumbnails: List<Thumbnail>? = null
    )

    @Serializable
    data class TextClass (
        val runs: List<TitleRun>? = null
    )

    @Serializable
    data class TitleRun (
        val text: String? = null
    )

    @Serializable
    data class InlinePlaybackEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: InlinePlaybackEndpointCommandMetadata? = null,
        val watchEndpoint: InlinePlaybackEndpointWatchEndpoint? = null
    )

    @Serializable
    data class InlinePlaybackEndpointCommandMetadata (
        val webCommandMetadata: TentacledWebCommandMetadata? = null
    )

    @Serializable
    data class TentacledWebCommandMetadata (
        val url: String? = null,
        val webPageType: String? = null,
        val rootVe: Long? = null
    )

    @Serializable
    data class InlinePlaybackEndpointWatchEndpoint (
        val videoId: String? = null,
        val playerParams: String? = null,
        val playerExtraUrlParams: List<Param>? = null,
        val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig? = null
    )

    @Serializable
    data class Param (
        val key: String? = null,
        val value: String? = null
    )

    @Serializable
    data class WatchEndpointSupportedOnesieConfig (
        val html5PlaybackOnesieConfig: Html5PlaybackOnesieConfig? = null
    )

    @Serializable
    data class Html5PlaybackOnesieConfig (
        val commonConfig: CommonConfig? = null
    )

    @Serializable
    data class CommonConfig (
        val url: String? = null
    )

    @Serializable
    data class LengthTextClass (
        val accessibility: AccessibilityData? = null,
        val simpleText: String? = null
    )

    @Serializable
    data class Text (
        val runs: List<LongBylineTextRun>? = null
    )

    @Serializable
    data class LongBylineTextRun (
        val text: String? = null,
        val navigationEndpoint: ChannelThumbnailWithLinkRendererNavigationEndpoint? = null
    )

    @Serializable
    data class VideoRendererMenu (
        val menuRenderer: PurpleMenuRenderer? = null
    )

    @Serializable
    data class PurpleMenuRenderer (
        val items: List<PurpleItem>? = null,
        val trackingParams: String? = null,
        val accessibility: AccessibilityData? = null,
        val targetId: String? = null
    )

    @Serializable
    data class PurpleItem (
        val menuServiceItemRenderer: PurpleMenuServiceItemRenderer? = null
    )

    @Serializable
    data class PurpleMenuServiceItemRenderer (
        val text: TextClass? = null,
        val icon: IconImage? = null,
        val serviceEndpoint: PurpleServiceEndpoint? = null,
        val trackingParams: String? = null,
        val hasSeparator: Boolean? = null
    )

    @Serializable
    data class IconImage (
        val iconType: String? = null
    )

    @Serializable
    data class PurpleServiceEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val signalServiceEndpoint: UntoggledServiceEndpointSignalServiceEndpoint? = null,
        val playlistEditEndpoint: ServiceEndpointPlaylistEditEndpoint? = null,
        val addToPlaylistServiceEndpoint: AddToPlaylistServiceEndpoint? = null,
        val shareEntityServiceEndpoint: ShareEntityServiceEndpoint? = null,
        val feedbackEndpoint: PurpleFeedbackEndpoint? = null,
        val getReportFormEndpoint: GetReportFormEndpoint? = null
    )

    @Serializable
    data class AddToPlaylistServiceEndpoint (
        val videoId: String? = null
    )

    @Serializable
    data class PurpleFeedbackEndpoint (
        val feedbackToken: String? = null,
        val uiActions: UIActions? = null,
        val actions: List<PurpleAction>? = null
    )

    @Serializable
    data class PurpleAction (
        val clickTrackingParams: String? = null,
        val replaceEnclosingAction: PurpleReplaceEnclosingAction? = null
    )

    @Serializable
    data class PurpleReplaceEnclosingAction (
        val item: FluffyItem? = null
    )

    @Serializable
    data class FluffyItem (
        val notificationMultiActionRenderer: PurpleNotificationMultiActionRenderer? = null
    )

    @Serializable
    data class PurpleNotificationMultiActionRenderer (
        val responseText: ResponseText? = null,
        val buttons: List<PurpleButton>? = null,
        val trackingParams: String? = null,
        val dismissalViewStyle: String? = null
    )

    @Serializable
    data class PurpleButton (
        val buttonRenderer: PurpleButtonRenderer? = null
    )

    @Serializable
    data class PurpleButtonRenderer (
        val style: String? = null,
        val text: ButtonRendererText? = null,
        val serviceEndpoint: FluffyServiceEndpoint? = null,
        val trackingParams: String? = null,
        val command: PurpleCommand? = null
    )

    @Serializable
    data class PurpleCommand (
        val clickTrackingParams: String? = null,
        val commandMetadata: InlinePlaybackEndpointCommandMetadata? = null,
        val urlEndpoint: URLEndpoint? = null
    )

    @Serializable
    data class URLEndpoint (
        val url: String? = null,
        val target: String? = null
    )

    @Serializable
    data class FluffyServiceEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val undoFeedbackEndpoint: UndoFeedbackEndpoint? = null,
        val signalServiceEndpoint: CommandSignalServiceEndpoint? = null
    )

    @Serializable
    data class CommandSignalServiceEndpoint (
        val signal: String? = null,
        val actions: List<FluffyAction>? = null
    )

    @Serializable
    data class FluffyAction (
        val clickTrackingParams: String? = null,
        val signalAction: Signal? = null
    )

    @Serializable
    data class Signal (
        val signal: String? = null
    )

    @Serializable
    data class UndoFeedbackEndpoint (
        val undoToken: String? = null,
        val actions: List<UndoFeedbackEndpointAction>? = null
    )

    @Serializable
    data class UndoFeedbackEndpointAction (
        val clickTrackingParams: String? = null,
        val undoFeedbackAction: UploadEndpoint? = null
    )

    @Serializable
    data class UploadEndpoint (
        val hack: Boolean? = null
    )

    @Serializable
    data class ButtonRendererText (
        val simpleText: String? = null,
        val runs: List<TitleRun>? = null
    )

    @Serializable
    data class ResponseText (
        val accessibility: AccessibilityData? = null,
        val simpleText: String? = null,
        val runs: List<TitleRun>? = null
    )

    @Serializable
    data class UIActions (
        val hideEnclosingContainer: Boolean? = null
    )

    @Serializable
    data class GetReportFormEndpoint (
        val params: String? = null
    )

    @Serializable
    data class ServiceEndpointPlaylistEditEndpoint (
        val playlistId: String? = null,
        val actions: List<TentacledAction>? = null
    )

    @Serializable
    data class TentacledAction (
        val addedVideoId: String? = null,
        val action: String? = null
    )

    @Serializable
    data class ShareEntityServiceEndpoint (
        val serializedShareEntity: String? = null,
        val commands: List<CommandElement>? = null
    )

    @Serializable
    data class CommandElement (
        val clickTrackingParams: String? = null,
        val openPopupAction: CommandOpenPopupAction? = null
    )

    @Serializable
    data class CommandOpenPopupAction (
        val popup: PurplePopup? = null,
        val popupType: String? = null,
        val beReused: Boolean? = null
    )

    @Serializable
    data class PurplePopup (
        val unifiedSharePanelRenderer: UnifiedSharePanelRenderer? = null
    )

    @Serializable
    data class UnifiedSharePanelRenderer (
        val trackingParams: String? = null,
        val showLoadingSpinner: Boolean? = null
    )

    @Serializable
    data class UntoggledServiceEndpointSignalServiceEndpoint (
        val signal: String? = null,
        val actions: List<StickyAction>? = null
    )

    @Serializable
    data class StickyAction (
        val clickTrackingParams: String? = null,
        val addToPlaylistCommand: AddToPlaylistCommand? = null
    )

    @Serializable
    data class AddToPlaylistCommand (
        val openMiniplayer: Boolean? = null,
        val videoId: String? = null,
        val listType: String? = null,
        val onCreateListCommand: OnCreateListCommand? = null,
        val videoIds: List<String>? = null
    )

    @Serializable
    data class OnCreateListCommand (
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val createPlaylistServiceEndpoint: CreatePlaylistServiceEndpoint? = null
    )

    @Serializable
    data class CreatePlaylistServiceEndpoint (
        val videoIds: List<String>? = null,
        val params: String? = null
    )

    @Serializable
    data class VideoRendererNavigationEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: InlinePlaybackEndpointCommandMetadata? = null,
        val watchEndpoint: NavigationEndpointWatchEndpoint? = null
    )

    @Serializable
    data class NavigationEndpointWatchEndpoint (
        val videoId: String? = null,
        val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig? = null
    )
//
//    @Serializable
//    data class OwnerBadge (
//        val metadataBadgeRenderer: MetadataBadgeRenderer? = null
//    )
//
//    @Serializable
//    data class MetadataBadgeRenderer (
//        val icon: IconImage? = null,
//        val style: String? = null,
//        val tooltip: String? = null,
//        val trackingParams: String? = null,
//        val accessibilityData: Accessibility? = null
//    )

    @Serializable
    data class PublishedTimeText (
        val simpleText: String? = null
    )

    @Serializable
    data class VideoRendererThumbnail (
        val thumbnails: List<Thumbnail>? = null
    )

    @Serializable
    data class Thumbnail (
        val url: String? = null,
        val width: Long? = null,
        val height: Long? = null
    )

    @Serializable
    data class ThumbnailOverlay (
        val thumbnailOverlayTimeStatusRenderer: ThumbnailOverlayTimeStatusRenderer? = null,
        val thumbnailOverlayToggleButtonRenderer: ThumbnailOverlayToggleButtonRenderer? = null,
        val thumbnailOverlayNowPlayingRenderer: ThumbnailOverlayRenderer? = null,
        val thumbnailOverlayLoadingPreviewRenderer: ThumbnailOverlayRenderer? = null,
        val thumbnailOverlayInlineUnplayableRenderer: ThumbnailOverlayInlineUnplayableRenderer? = null
    )

    @Serializable
    data class ThumbnailOverlayInlineUnplayableRenderer (
        val text: TextClass? = null,
        val icon: IconImage? = null
    )

    @Serializable
    data class ThumbnailOverlayRenderer (
        val text: TextClass? = null
    )

    @Serializable
    data class ThumbnailOverlayTimeStatusRenderer (
        val text: LengthTextClass? = null,
        val style: String? = null
    )

    @Serializable
    data class ThumbnailOverlayToggleButtonRenderer (
        val isToggled: Boolean? = null,
        val untoggledIcon: IconImage? = null,
        val toggledIcon: IconImage? = null,
        val untoggledTooltip: String? = null,
        val toggledTooltip: String? = null,
        val untoggledServiceEndpoint: UntoggledServiceEndpoint? = null,
        val toggledServiceEndpoint: ToggledServiceEndpoint? = null,
        val untoggledAccessibility: AccessibilityData? = null,
        val toggledAccessibility: AccessibilityData? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class ToggledServiceEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val playlistEditEndpoint: ToggledServiceEndpointPlaylistEditEndpoint? = null
    )

    @Serializable
    data class ToggledServiceEndpointPlaylistEditEndpoint (
        val playlistId: String? = null,
        val actions: List<IndigoAction>? = null,
    )

    @Serializable
    data class IndigoAction (
        val action: String? = null,
        val removedVideoId: String? = null
    )

    @Serializable
    data class UntoggledServiceEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val playlistEditEndpoint: ServiceEndpointPlaylistEditEndpoint? = null,
        val signalServiceEndpoint: UntoggledServiceEndpointSignalServiceEndpoint? = null,
    )

    @Serializable
    data class PurpleTitle (
        val runs: List<TitleRun>? = null,
        val accessibility: AccessibilityData? = null
    )

    @Serializable
    data class RichSectionRenderer (
        val content: RichSectionRendererContent? = null,
        val trackingParams: String? = null,
        val fullBleed: Boolean? = null
    )

    @Serializable
    data class RichSectionRendererContent (
        val richShelfRenderer: RichShelfRenderer? = null
    )

    @Serializable
    data class RichShelfRenderer (
        val title: TextClass? = null,
        val contents: List<RichShelfRendererContent>? = null,
        val trackingParams: String? = null,
        val menu: RichShelfRendererMenu? = null,
        val showMoreButton: ShowMoreButton? = null,
        val icon: IconImage? = null
    )

    @Serializable
    data class RichShelfRendererContent (
        val richItemRenderer: FluffyRichItemRenderer? = null
    )

    @Serializable
    data class FluffyRichItemRenderer (
        val content: FluffyContent? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class FluffyContent (
        val reelItemRenderer: ReelItemRenderer? = null
    )

    @Serializable
    data class ReelItemRenderer (
        val videoId: String? = null,
        val headline: PublishedTimeText? = null,
        val thumbnail: ReelWatchEndpointThumbnail? = null,
        val viewCountText: LengthTextClass? = null,
        val navigationEndpoint: ReelItemRendererNavigationEndpoint? = null,
        val menu: ReelItemRendererMenu? = null,
        val trackingParams: String? = null,
        val accessibility: AccessibilityData? = null,
        val style: String? = null,
        val dismissalInfo: DismissalInfo? = null,
        val videoType: String? = null,
        val loggingDirectives: LoggingDirectives? = null
    )

    @Serializable
    data class DismissalInfo (
        val feedbackToken: String? = null
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
    data class ReelItemRendererMenu (
        val menuRenderer: FluffyMenuRenderer? = null
    )

    @Serializable
    data class FluffyMenuRenderer (
        val items: List<TentacledItem>? = null,
        val trackingParams: String? = null,
        val accessibility: AccessibilityData? = null
    )

    @Serializable
    data class TentacledItem (
        val menuServiceItemRenderer: FluffyMenuServiceItemRenderer? = null,
        val menuNavigationItemRenderer: MenuNavigationItemRenderer? = null
    )

    @Serializable
    data class MenuNavigationItemRenderer (
        val text: TextClass? = null,
        val icon: IconImage? = null,
        val navigationEndpoint: MenuNavigationItemRendererNavigationEndpoint? = null,
        val trackingParams: String? = null,
        val accessibility: AccessibilityData? = null
    )

    @Serializable
    data class MenuNavigationItemRendererNavigationEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: PurpleCommandMetadata? = null,
        val userFeedbackEndpoint: UserFeedbackEndpoint? = null
    )

    @Serializable
    data class PurpleCommandMetadata (
        val webCommandMetadata: StickyWebCommandMetadata? = null
    )

    @Serializable
    data class StickyWebCommandMetadata (
        val ignoreNavigation: Boolean? = null
    )

    @Serializable
    data class UserFeedbackEndpoint (
        val additionalDatas: List<AdditionalData>? = null
    )

    @Serializable
    data class AdditionalData (
        val userFeedbackEndpointProductSpecificValueData: Param? = null
    )

    @Serializable
    data class FluffyMenuServiceItemRenderer (
        val text: TextClass? = null,
        val icon: IconImage? = null,
        val serviceEndpoint: TentacledServiceEndpoint? = null,
        val trackingParams: String? = null,
        val accessibility: AccessibilityData? = null,
        val hasSeparator: Boolean? = null
    )

    @Serializable
    data class TentacledServiceEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val getReportFormEndpoint: GetReportFormEndpoint? = null,
        val shareEntityServiceEndpoint: ShareEntityServiceEndpoint? = null,
        val feedbackEndpoint: FluffyFeedbackEndpoint? = null
    )

    @Serializable
    data class FluffyFeedbackEndpoint (
        val feedbackToken: String? = null,
        val actions: List<IndecentAction>? = null
    )

    @Serializable
    data class IndecentAction (
        val clickTrackingParams: String? = null,
        val replaceEnclosingAction: FluffyReplaceEnclosingAction? = null,
        val action: String? = null,
        val removedVideoId: String? = null
    )

    @Serializable
    data class FluffyReplaceEnclosingAction (
        val item: StickyItem? = null
    )

    @Serializable
    data class StickyItem (
        val notificationMultiActionRenderer: FluffyNotificationMultiActionRenderer? = null
    )

    @Serializable
    data class FluffyNotificationMultiActionRenderer (
        val responseText: TextClass? = null,
        val buttons: List<FluffyButton>? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class FluffyButton (
        val buttonRenderer: FluffyButtonRenderer? = null
    )

    @Serializable
    data class FluffyButtonRenderer (
        val style: String? = null,
        val text: TextClass? = null,
        val serviceEndpoint: StickyServiceEndpoint? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class StickyServiceEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val undoFeedbackEndpoint: UndoFeedbackEndpoint? = null
    )

    @Serializable
    data class ReelItemRendererNavigationEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: InlinePlaybackEndpointCommandMetadata? = null,
        val reelWatchEndpoint: ReelWatchEndpoint? = null
    )

    @Serializable
    data class ReelWatchEndpoint (
        val videoId: String? = null,
        val playerParams: String? = null,
        val thumbnail: ReelWatchEndpointThumbnail? = null,
        val overlay: Overlay? = null,
        val params: String? = null,
        val sequenceProvider: String? = null,
        val sequenceParams: String? = null,
        val loggingContext: LoggingContext? = null
    )

    @Serializable
    data class LoggingContext (
        val vssLoggingContext: QoeLoggingContextClass? = null,
        val qoeLoggingContext: QoeLoggingContextClass? = null
    )

    @Serializable
    data class QoeLoggingContextClass (
        val serializedContextData: String? = null
    )

    @Serializable
    data class Overlay (
        val reelPlayerOverlayRenderer: ReelPlayerOverlayRenderer? = null
    )

    @Serializable
    data class ReelPlayerOverlayRenderer (
        val style: String? = null,
        val trackingParams: String? = null,
        val reelPlayerNavigationModel: String? = null
    )

    @Serializable
    data class ReelWatchEndpointThumbnail (
        val thumbnails: List<Thumbnail>? = null,
        val isOriginalAspectRatio: Boolean? = null
    )

    @Serializable
    data class RichShelfRendererMenu (
        val menuRenderer: TentacledMenuRenderer? = null
    )

    @Serializable
    data class TentacledMenuRenderer (
        val trackingParams: String? = null,
        val topLevelButtons: List<TopLevelButton>? = null
    )

    @Serializable
    data class TopLevelButton (
        val buttonRenderer: TopLevelButtonButtonRenderer? = null
    )

    @Serializable
    data class TopLevelButtonButtonRenderer (
        val style: String? = null,
        val size: String? = null,
        val isDisabled: Boolean? = null,
        val serviceEndpoint: IndigoServiceEndpoint? = null,
        val icon: IconImage? = null,
        val tooltip: String? = null,
        val trackingParams: String? = null,
        val accessibilityData: AccessibilityData? = null
    )

    @Serializable
    data class IndigoServiceEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val feedbackEndpoint: TentacledFeedbackEndpoint? = null
    )

    @Serializable
    data class TentacledFeedbackEndpoint (
        val feedbackToken: String? = null,
        val uiActions: UIActions? = null,
        val actions: List<HilariousAction>? = null
    )

    @Serializable
    data class HilariousAction (
        val clickTrackingParams: String? = null,
        val replaceEnclosingAction: TentacledReplaceEnclosingAction? = null
    )

    @Serializable
    data class TentacledReplaceEnclosingAction (
        val item: IndigoItem? = null
    )

    @Serializable
    data class IndigoItem (
        val notificationMultiActionRenderer: TentacledNotificationMultiActionRenderer? = null
    )

    @Serializable
    data class TentacledNotificationMultiActionRenderer (
        val responseText: TextClass? = null,
        val buttons: List<TentacledButton>? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class TentacledButton (
        val buttonRenderer: TentacledButtonRenderer? = null
    )

    @Serializable
    data class TentacledButtonRenderer (
        val style: String? = null,
        val text: PublishedTimeText? = null,
        val serviceEndpoint: StickyServiceEndpoint? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class ShowMoreButton (
        val buttonRenderer: ShowMoreButtonButtonRenderer? = null
    )

    @Serializable
    data class ShowMoreButtonButtonRenderer (
        val style: String? = null,
        val size: String? = null,
        val icon: IconImage? = null,
        val accessibility: Accessibility? = null,
        val tooltip: String? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class RichGridRendererHeader (
        val feedFilterChipBarRenderer: FeedFilterChipBarRenderer? = null
    )

    @Serializable
    data class FeedFilterChipBarRenderer (
        val contents: List<FeedFilterChipBarRendererContent>? = null,
        val trackingParams: String? = null,
        val nextButton: NextButtonClass? = null,
        val previousButton: NextButtonClass? = null,
        val styleType: String? = null
    )

    @Serializable
    data class FeedFilterChipBarRendererContent (
        val chipCloudChipRenderer: ChipCloudChipRenderer? = null
    )

    @Serializable
    data class ChipCloudChipRenderer (
        val style: Style? = null,
        val text: TextClass? = null,
        val trackingParams: String? = null,
        val isSelected: Boolean? = null,
        val navigationEndpoint: ChipCloudChipRendererNavigationEndpoint? = null,
        val targetId: String? = null,
        val uniqueId: String? = null
    )

    @Serializable
    data class ChipCloudChipRendererNavigationEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val continuationCommand: NavigationEndpointContinuationCommand? = null
    )

    @Serializable
    data class NavigationEndpointContinuationCommand (
        val token: String? = null,
        val request: String? = null,
        val command: ContinuationCommandCommand? = null
    )

    @Serializable
    data class ContinuationCommandCommand (
        val clickTrackingParams: String? = null,
        val showReloadUiCommand: ShowReloadUiCommand? = null
    )

    @Serializable
    data class ShowReloadUiCommand (
        val targetId: String? = null
    )

    @Serializable
    data class Style (
        val styleType: String? = null
    )

    @Serializable
    data class NextButtonClass (
        val buttonRenderer: NextButtonButtonRenderer? = null
    )

    @Serializable
    data class NextButtonButtonRenderer (
        val style: String? = null,
        val size: String? = null,
        val isDisabled: Boolean? = null,
        val icon: IconImage? = null,
        val tooltip: String? = null,
        val trackingParams: String? = null,
        val accessibilityData: AccessibilityData? = null
    )

    @Serializable
    data class ReflowOptions (
        val minimumRowsOfVideosAtStart: Long? = null,
        val minimumRowsOfVideosBetweenSections: Long? = null
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
        val options: Options? = null
    )

    @Serializable
    data class Options (
        val persistenceOption: String? = null
    )

    @Serializable
    data class Timestamp (
        val seconds: String? = null,
        val nanos: Long? = null
    )

    @Serializable
    data class YoutubeBaseResponseHeader (
        val feedTabbedHeaderRenderer: FeedTabbedHeaderRenderer? = null
    )

    @Serializable
    data class FeedTabbedHeaderRenderer (
        val title: TextClass? = null
    )

    @Serializable
    data class ResponseContext (
        val serviceTrackingParams: List<ServiceTrackingParam>? = null,
        val maxAgeSeconds: Long? = null,
        val mainAppWebResponseContext: MainAppWebResponseContext? = null,
        val webResponseContextExtensionData: WebResponseContextExtensionData? = null,
        val visitorData: String? = null,
    )

    @Serializable
    data class MainAppWebResponseContext (
        val datasyncId: String? = null,
        val loggedOut: Boolean? = null
    )

    @Serializable
    data class ServiceTrackingParam (
        val service: String? = null,
        val params: List<Param>? = null
    )

    @Serializable
    data class WebResponseContextExtensionData (
        val ytConfigData: YtConfigData? = null,
        val hasDecorated: Boolean? = null
    )

    @Serializable
    data class YtConfigData (
        val visitorData: String? = null,
        val sessionIndex: Long? = null,
        val rootVisualElementType: Long? = null
    )

    @Serializable
    data class Topbar (
        val desktopTopbarRenderer: DesktopTopbarRenderer? = null
    )

    @Serializable
    data class DesktopTopbarRenderer (
        val logo: Logo? = null,
        val searchbox: Searchbox? = null,
        val trackingParams: String? = null,
        val countryCode: String? = null,
        val topbarButtons: List<TopbarButton>? = null,
        val hotkeyDialog: HotkeyDialog? = null,
        val backButton: BackButtonClass? = null,
        val forwardButton: BackButtonClass? = null,

        @SerialName("a11ySkipNavigationButton")
        val a11YSkipNavigationButton: A11YSkipNavigationButton? = null,

        val voiceSearchButton: VoiceSearchButton? = null
    )

    @Serializable
    data class A11YSkipNavigationButton (
        val buttonRenderer: A11YSkipNavigationButtonButtonRenderer? = null
    )

    @Serializable
    data class A11YSkipNavigationButtonButtonRenderer (
        val style: String? = null,
        val size: String? = null,
        val isDisabled: Boolean? = null,
        val text: TextClass? = null,
        val trackingParams: String? = null,
        val command: FluffyCommand? = null
    )

    @Serializable
    data class FluffyCommand (
        val clickTrackingParams: String? = null,
        val commandMetadata: FluffyCommandMetadata? = null,
        val signalServiceEndpoint: CommandSignalServiceEndpoint? = null
    )

    @Serializable
    data class FluffyCommandMetadata (
        val webCommandMetadata: IndigoWebCommandMetadata? = null
    )

    @Serializable
    data class IndigoWebCommandMetadata (
        val sendPost: Boolean? = null
    )

    @Serializable
    data class BackButtonClass (
        val buttonRenderer: BackButtonButtonRenderer? = null
    )

    @Serializable
    data class BackButtonButtonRenderer (
        val trackingParams: String? = null,
        val command: FluffyCommand? = null
    )

    @Serializable
    data class HotkeyDialog (
        val hotkeyDialogRenderer: HotkeyDialogRenderer? = null
    )

    @Serializable
    data class HotkeyDialogRenderer (
        val title: TextClass? = null,
        val sections: List<Section>? = null,
        val dismissButton: DismissButton? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class DismissButton (
        val buttonRenderer: DismissButtonButtonRenderer? = null
    )

    @Serializable
    data class DismissButtonButtonRenderer (
        val style: String? = null,
        val size: String? = null,
        val isDisabled: Boolean? = null,
        val text: TextClass? = null,
        val trackingParams: String? = null
    )

    @Serializable
    data class Section (
        val hotkeyDialogSectionRenderer: HotkeyDialogSectionRenderer? = null
    )

    @Serializable
    data class HotkeyDialogSectionRenderer (
        val title: TextClass? = null,
        val options: List<Option>? = null
    )

    @Serializable
    data class Option (
        val hotkeyDialogSectionOptionRenderer: HotkeyDialogSectionOptionRenderer? = null
    )

    @Serializable
    data class HotkeyDialogSectionOptionRenderer (
        val label: TextClass? = null,
        val hotkey: String? = null,
        val hotkeyAccessibilityLabel: AccessibilityData? = null
    )

    @Serializable
    data class Logo (
        val topbarLogoRenderer: TopbarLogoRenderer? = null
    )

    @Serializable
    data class TopbarLogoRenderer (
        val iconImage: IconImage? = null,
        val tooltipText: TextClass? = null,
        val endpoint: Endpoint? = null,
        val trackingParams: String? = null,
        val overrideEntityKey: String? = null
    )

    @Serializable
    data class Endpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: EndpointCommandMetadata? = null,
        val browseEndpoint: EndpointBrowseEndpoint? = null
    )

    @Serializable
    data class EndpointBrowseEndpoint (
        val browseId: String? = null
    )

    @Serializable
    data class Searchbox (
        val fusionSearchboxRenderer: FusionSearchboxRenderer? = null
    )

    @Serializable
    data class FusionSearchboxRenderer (
        val icon: IconImage? = null,
        val placeholderText: TextClass? = null,
        val config: Config? = null,
        val trackingParams: String? = null,
        val searchEndpoint: FusionSearchboxRendererSearchEndpoint? = null,
        val clearButton: ClearButtonClass? = null
    )

    @Serializable
    data class ClearButtonClass (
        val buttonRenderer: ClearButtonButtonRenderer? = null
    )

    @Serializable
    data class ClearButtonButtonRenderer (
        val style: String? = null,
        val size: String? = null,
        val isDisabled: Boolean? = null,
        val icon: IconImage? = null,
        val trackingParams: String? = null,
        val accessibilityData: AccessibilityData? = null
    )

    @Serializable
    data class Config (
        val webSearchboxConfig: WebSearchboxConfig? = null
    )

    @Serializable
    data class WebSearchboxConfig (
        val requestLanguage: String? = null,
        val requestDomain: String? = null,
        val hasOnscreenKeyboard: Boolean? = null,
        val focusSearchbox: Boolean? = null
    )

    @Serializable
    data class FusionSearchboxRendererSearchEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: InlinePlaybackEndpointCommandMetadata? = null,
        val searchEndpoint: SearchEndpointSearchEndpoint? = null
    )

    @Serializable
    data class SearchEndpointSearchEndpoint (
        val query: String? = null
    )

    @Serializable
    data class TopbarButton (
        val buttonRenderer: TopbarButtonButtonRenderer? = null,
        val notificationTopbarButtonRenderer: NotificationTopbarButtonRenderer? = null,
        val topbarMenuButtonRenderer: TopbarMenuButtonRenderer? = null
    )

    @Serializable
    data class TopbarButtonButtonRenderer (
        val style: String? = null,
        val size: String? = null,
        val isDisabled: Boolean? = null,
        val icon: IconImage? = null,
        val navigationEndpoint: ButtonRendererNavigationEndpoint? = null,
        val accessibility: Accessibility? = null,
        val tooltip: String? = null,
        val trackingParams: String? = null,
        val accessibilityData: AccessibilityData? = null
    )

    @Serializable
    data class ButtonRendererNavigationEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: InlinePlaybackEndpointCommandMetadata? = null,
        val uploadEndpoint: UploadEndpoint? = null
    )

    @Serializable
    data class NotificationTopbarButtonRenderer (
        val icon: IconImage? = null,
        val menuRequest: MenuRequest? = null,
        val style: String? = null,
        val trackingParams: String? = null,
        val accessibility: AccessibilityData? = null,
        val tooltip: String? = null,
        val updateUnseenCountEndpoint: UpdateUnseenCountEndpoint? = null,
        val notificationCount: Long? = null,
        val handlerDatas: List<String>? = null
    )

    @Serializable
    data class MenuRequest (
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val signalServiceEndpoint: MenuRequestSignalServiceEndpoint? = null
    )

    @Serializable
    data class MenuRequestSignalServiceEndpoint (
        val signal: String? = null,
        val actions: List<AmbitiousAction>? = null
    )

    @Serializable
    data class AmbitiousAction (
        val clickTrackingParams: String? = null,
        val openPopupAction: PurpleOpenPopupAction? = null
    )

    @Serializable
    data class PurpleOpenPopupAction (
        val popup: FluffyPopup? = null,
        val popupType: String? = null,
        val beReused: Boolean? = null
    )

    @Serializable
    data class FluffyPopup (
        val multiPageMenuRenderer: MultiPageMenuRenderer? = null
    )

    @Serializable
    data class MultiPageMenuRenderer (
        val trackingParams: String? = null,
        val style: String? = null,
        val showLoadingSpinner: Boolean? = null
    )

    @Serializable
    data class UpdateUnseenCountEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: ContinuationEndpointCommandMetadata? = null,
        val signalServiceEndpoint: Signal? = null
    )

    @Serializable
    data class TopbarMenuButtonRenderer (
        val avatar: Avatar? = null,
        val menuRequest: MenuRequest? = null,
        val trackingParams: String? = null,
        val accessibility: AccessibilityData? = null,
        val tooltip: String? = null
    )

    @Serializable
    data class Avatar (
        val thumbnails: List<Thumbnail>? = null,
        val accessibility: AccessibilityData? = null
    )

    @Serializable
    data class VoiceSearchButton (
        val buttonRenderer: VoiceSearchButtonButtonRenderer? = null
    )

    @Serializable
    data class VoiceSearchButtonButtonRenderer (
        val style: String? = null,
        val size: String? = null,
        val isDisabled: Boolean? = null,
        val serviceEndpoint: IndecentServiceEndpoint? = null,
        val icon: IconImage? = null,
        val tooltip: String? = null,
        val trackingParams: String? = null,
        val accessibilityData: AccessibilityData? = null
    )

    @Serializable
    data class IndecentServiceEndpoint (
        val clickTrackingParams: String? = null,
        val commandMetadata: FluffyCommandMetadata? = null,
        val signalServiceEndpoint: PurpleSignalServiceEndpoint? = null
    )

    @Serializable
    data class PurpleSignalServiceEndpoint (
        val signal: String? = null,
        val actions: List<CunningAction>? = null
    )

    @Serializable
    data class CunningAction (
        val clickTrackingParams: String? = null,
        val openPopupAction: FluffyOpenPopupAction? = null
    )

    @Serializable
    data class FluffyOpenPopupAction (
        val popup: TentacledPopup? = null,
        val popupType: String? = null
    )

    @Serializable
    data class TentacledPopup (
        val voiceSearchDialogRenderer: VoiceSearchDialogRenderer? = null
    )

    @Serializable
    data class VoiceSearchDialogRenderer (
        val placeholderHeader: TextClass? = null,
        val promptHeader: TextClass? = null,
        val exampleQuery1: TextClass? = null,
        val exampleQuery2: TextClass? = null,
        val promptMicrophoneLabel: TextClass? = null,
        val loadingHeader: TextClass? = null,
        val connectionErrorHeader: TextClass? = null,
        val connectionErrorMicrophoneLabel: TextClass? = null,
        val permissionsHeader: TextClass? = null,
        val permissionsSubtext: TextClass? = null,
        val disabledHeader: TextClass? = null,
        val disabledSubtext: TextClass? = null,
        val microphoneButtonAriaLabel: TextClass? = null,
        val exitButton: ClearButtonClass? = null,
        val trackingParams: String? = null,
        val microphoneOffPromptHeader: TextClass? = null
    )

}

