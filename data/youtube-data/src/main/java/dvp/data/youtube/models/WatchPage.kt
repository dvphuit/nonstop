package dvp.data.youtube.models

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json      = Json(JsonConfiguration.Stable)
// val watchPage = json.parse(WatchPage.serializer(), jsonString)

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class WatchPage (
//    val page: String,
//    val cver: String,

    @SerialName("player_response")
    val playerResponse: PlayerResponse,

    val response: Response,

//    @SerialName("html5player")
//    val html5Player: String,

    val formats: List<AdaptiveFormatElement>,

    @SerialName("relative_video")
    val relativeVideo: List<RelativeVideo>
)


@Serializable
data class Range (
    val start: String,
    val end: String
)


@Serializable
data class SubscribeButtonRenderer (
    val buttonText: DetailsText,
    val subscribed: Boolean,
    val enabled: Boolean,
    val type: String,

    @SerialName("channelId")
    val channelID: String,

    val showPreferences: Boolean,
    val subscribedButtonText: DetailsText,
    val unsubscribedButtonText: DetailsText,
    val trackingParams: String,
    val unsubscribeButtonText: DetailsText,
    val serviceEndpoints: List<ServiceEndpointElement>,
    val subscribeAccessibility: DisabledAccessibilityData,
    val unsubscribeAccessibility: DisabledAccessibilityData,
    val signInEndpoint: SubscribeButtonRendererSignInEndpoint
)

@Serializable
data class ServiceEndpointElement (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val subscribeEndpoint: SubscribeEndpoint? = null,
    val signalServiceEndpoint: PurpleSignalServiceEndpoint? = null
)
@Serializable
data class PurpleSignalServiceEndpoint (
    val signal: String,
    val actions: List<PurpleAction>
)

@Serializable
data class PurpleAction (
    val clickTrackingParams: String,
    val openPopupAction: PurpleOpenPopupAction
)

@Serializable
data class PurpleOpenPopupAction (
    val popup: PurplePopup,
    val popupType: String
)

@Serializable
data class PurplePopup (
    val confirmDialogRenderer: ConfirmDialogRenderer
)

@Serializable
data class ConfirmDialogRenderer (
    val trackingParams: String,
    val dialogMessages: List<DetailsText>,
    val confirmButton: A11YSkipNavigationButtonClass,
    val cancelButton: A11YSkipNavigationButtonClass,
    val primaryIsCancel: Boolean
)

@Serializable
data class A11YSkipNavigationButtonClass (
    val buttonRenderer: A11YSkipNavigationButtonButtonRenderer
)

@Serializable
data class A11YSkipNavigationButtonButtonRenderer (
    val style: String,
    val size: String,
    val isDisabled: Boolean,
    val text: DetailsText? = null,
    val accessibility: Accessibility? = null,
    val trackingParams: String,
    val serviceEndpoint: UnsubscribeCommand? = null,
    val command: PurpleCommand? = null,
    val icon: Icon? = null
)

@Serializable
data class Accessibility (
    val label: String
)

@Serializable
data class PurpleCommand (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val continuationCommand: CommandContinuationCommand? = null,
    val signalServiceEndpoint: CommandSignalServiceEndpoint? = null
)

@Serializable
data class CommandContinuationCommand (
    val token: String,
    val request: String
)

@Serializable
data class CommandSignalServiceEndpoint (
    val signal: String,
    val actions: List<FluffyAction>
)

@Serializable
data class FluffyAction (
    val clickTrackingParams: String,
    val signalAction: SignalAction
)

@Serializable
data class SignalAction (
    val signal: String
)

@Serializable
data class Icon (
    val iconType: String
)


@Serializable
data class SubscribeEndpoint (
    @SerialName("channelIds")
    val channelIDS: List<String>,

    val params: String
)

@Serializable
data class SubscribeButtonRendererSignInEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: SignInEndpointCommandMetadata
)

@Serializable
data class SignInEndpointCommandMetadata (
    val webCommandMetadata: CommonConfig
)

@Serializable
data class CommonConfig (
    val url: String
)

@Serializable
data class DisabledAccessibilityData (
    val accessibilityData: Accessibility
)

@Serializable
data class Teaser (
    val simpleCardTeaserRenderer: SimpleCardTeaserRenderer
)

@Serializable
data class SimpleCardTeaserRenderer (
    val message: HeaderText,
    val trackingParams: String,
    val prominent: Boolean,
    val logVisibilityUpdates: Boolean,
    val onTapCommand: OnTapCommandClass
)

@Serializable
data class OnTapCommandClass (
    val clickTrackingParams: String,
    val changeEngagementPanelVisibilityAction: ChangeEngagementPanelVisibilityAction
)

@Serializable
data class ChangeEngagementPanelVisibilityAction (
    @SerialName("targetId")
    val targetID: String,

    val visibility: String
)

@Serializable
data class Renderer (
    val trackingParams: String
)

@Serializable
data class PurpleMutation (
    val entityKey: String,
    val type: String,
    val payload: Payload
)

@Serializable
data class Payload (
    val offlineabilityEntity: OfflineabilityEntity
)

@Serializable
data class OfflineabilityEntity (
    val key: String,
    val addToOfflineButtonState: String
)

@Serializable
data class Timestamp (
    val seconds: String,
    val nanos: Long
)

@Serializable
data class FeedbackEndpoint (
    val feedbackToken: String,
    val uiActions: UIActions
)

@Serializable
data class UIActions (
    val hideEnclosingContainer: Boolean
)

@Serializable
data class DismissStrategy (
    val type: String
)

@Serializable
data class PromoConfig (
    @SerialName("promoId")
    val promoID: String,

    val impressionEndpoints: List<AcceptCommand>,
    val acceptCommand: AcceptCommand,
    val dismissCommand: AcceptCommand
)



@Serializable
data class Embed (
    @SerialName("iframeUrl")
    val iframeURL: String,

    @SerialName("flashUrl")
    val flashURL: String,

    val width: Long,
    val height: Long,

    @SerialName("flashSecureUrl")
    val flashSecureURL: String
)




@Serializable
data class TentacledAction (
    @SerialName("addedVideoId")
    val addedVideoID: String,

    val action: String
)

@Serializable
data class Text (
    val runs: List<PurpleRun>
)

@Serializable
data class PurpleRun (
    val text: String,
    val bold: Boolean? = null
)

@Serializable
data class RelativeVideo (
    val id: String,
    val title: String,
    val published: String? = null,
    val author: Author,

    @SerialName("view_count")
    val viewCount: String,

    @SerialName("length_text")
    val lengthText: String? = null,

    val thumbnails: List<ThumbnailElement>,

    @SerialName("rich_thumbnails")
    val richThumbnails: List<ThumbnailElement>? = null,

    val isLive: JsonObject? = null
)

@Serializable
data class Author (
    val id: String,
    val name: String,
    val user: String,
    val thumbnails: List<String>,
    val verified: Boolean
)

@Serializable
data class Response (
    val responseContext: ResponseContext,
    val contents: Contents,
    val currentVideoEndpoint: CurrentVideoEndpointClass,
    val trackingParams: String,
    val playerOverlays: PlayerOverlays,
    val overlay: Overlay,
    val onResponseReceivedEndpoints: List<OnResponseReceivedEndpoint>,
    val engagementPanels: List<EngagementPanel>,
    val topbar: Topbar,
    val pageVisualEffects: List<PageVisualEffect>,
    val frameworkUpdates: ResponseFrameworkUpdates
)

@Serializable
data class Contents (
    val twoColumnWatchNextResults: TwoColumnWatchNextResults
)

@Serializable
data class TwoColumnWatchNextResults (
    val results: TwoColumnWatchNextResultsResults,
    val secondaryResults: TwoColumnWatchNextResultsSecondaryResults,
    val autoplay: TwoColumnWatchNextResultsAutoplay
)

@Serializable
data class TwoColumnWatchNextResultsAutoplay (
    val autoplay: AutoplayAutoplay
)

@Serializable
data class AutoplayAutoplay (
    val sets: List<Set>,
    val countDownSecs: Long,
    val trackingParams: String
)

@Serializable
data class Set (
    val mode: String,
    val autoplayVideo: NavigationEndpointElement
)

@Serializable
data class NavigationEndpointElement (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val watchEndpoint: AutoplayVideoWatchEndpoint
)

@Serializable
data class AutoplayVideoWatchEndpoint (
    @SerialName("videoId")
    val videoID: String,

    val params: String,
    val playerParams: String,
    val watchEndpointSupportedPrefetchConfig: WatchEndpointSupportedPrefetchConfig
)

@Serializable
data class WatchEndpointSupportedPrefetchConfig (
    val prefetchHintConfig: PrefetchHintConfig
)

@Serializable
data class PrefetchHintConfig (
    val prefetchPriority: Long,

    @SerialName("countdownUiRelativeSecondsPrefetchCondition")
    val countdownUIRelativeSecondsPrefetchCondition: Long
)

@Serializable
data class TwoColumnWatchNextResultsResults (
    val results: ResultsResults
)

@Serializable
data class ResultsResults (
    val contents: List<ResultsContent>,
    val trackingParams: String
)

@Serializable
data class ResultsContent (
    val videoPrimaryInfoRenderer: VideoPrimaryInfoRenderer? = null,
    val videoSecondaryInfoRenderer: VideoSecondaryInfoRenderer? = null,
    val itemSectionRenderer: PurpleItemSectionRenderer? = null
)

@Serializable
data class PurpleItemSectionRenderer (
    val contents: List<PurpleContent>,
    val trackingParams: String,
    val sectionIdentifier: String,

    @SerialName("targetId")
    val targetID: String? = null
)

@Serializable
data class PurpleContent (
    val commentsEntryPointHeaderRenderer: CommentsEntryPointHeaderRenderer? = null,
    val continuationItemRenderer: ContentContinuationItemRenderer? = null
)

@Serializable
data class CommentsEntryPointHeaderRenderer (
    val headerText: DetailsText,
    val onTap: ShowMoreCommand,
    val trackingParams: String,
    val commentCount: HeaderText,
    val contentRenderer: ContentRenderer,

    @SerialName("targetId")
    val targetID: String
)

@Serializable
data class ContentRenderer (
    val commentsEntryPointTeaserRenderer: CommentsEntryPointTeaserRenderer
)

@Serializable
data class CommentsEntryPointTeaserRenderer (
    val teaserAvatar: TeaserAvatar,
    val teaserContent: HeaderText,
    val trackingParams: String
)

@Serializable
data class TeaserAvatar (
    val thumbnails: List<ThumbnailElement>,
    val accessibility: DisabledAccessibilityData
)

@Serializable
data class ShowMoreCommand (
    val clickTrackingParams: String,
    val commandExecutorCommand: ShowMoreCommandCommandExecutorCommand
)

@Serializable
data class ShowMoreCommandCommandExecutorCommand (
    val commands: List<TentacledCommand>
)

@Serializable
data class TentacledCommand (
    val clickTrackingParams: String,
    val changeEngagementPanelVisibilityAction: ChangeEngagementPanelVisibilityAction? = null,
    val scrollToEngagementPanelCommand: ScrollToEngagementPanelCommandClass? = null
)

@Serializable
data class ScrollToEngagementPanelCommandClass (
    @SerialName("targetId")
    val targetID: String
)

@Serializable
data class ContentContinuationItemRenderer (
    val trigger: String,
    val continuationEndpoint: ContinuationEndpoint
)

@Serializable
data class ContinuationEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val continuationCommand: CommandContinuationCommand
)

@Serializable
data class VideoPrimaryInfoRenderer (
    val title: DetailsText,
    val viewCount: ViewCount,
    val videoActions: VideoActions,
    val trackingParams: String,
    val dateText: HeaderText,
    val relativeDateText: ShortViewCountText
)

@Serializable
data class ShortViewCountText (
    val accessibility: DisabledAccessibilityData,
    val simpleText: String
)

@Serializable
data class VideoActions (
    val menuRenderer: VideoActionsMenuRenderer
)

@Serializable
data class VideoActionsMenuRenderer (
    val items: List<PurpleItem>,
    val trackingParams: String,
    val topLevelButtons: List<TopLevelButtonElement>,
    val accessibility: DisabledAccessibilityData,
    val flexibleItems: List<FlexibleItem>
)

@Serializable
data class FlexibleItem (
    val menuFlexibleItemRenderer: MenuFlexibleItemRenderer
)

@Serializable
data class MenuFlexibleItemRenderer (
    val menuItem: MenuItem,
    val topLevelButton: MenuFlexibleItemRendererTopLevelButton
)

@Serializable
data class MenuItem (
    val menuServiceItemDownloadRenderer: MenuServiceItemDownloadRenderer? = null,
    val menuServiceItemRenderer: MenuItemRenderer? = null
)

@Serializable
data class MenuServiceItemDownloadRenderer (
    val serviceEndpoint: ServiceEndpoint,
    val trackingParams: String
)

@Serializable
data class ServiceEndpoint (
    val clickTrackingParams: String,
    val offlineVideoEndpoint: OfflineVideoEndpoint
)

@Serializable
data class OfflineVideoEndpoint (
    @SerialName("videoId")
    val videoID: String,

    val onAddCommand: OnAddCommand
)

@Serializable
data class OnAddCommand (
    val clickTrackingParams: String,
    val getDownloadActionCommand: GetDownloadActionCommand
)

@Serializable
data class GetDownloadActionCommand (
    @SerialName("videoId")
    val videoID: String,

    val params: String,
    val offlineabilityEntityKey: String
)

@Serializable
data class MenuItemRenderer (
    val text: DetailsText,
    val icon: Icon,
    val serviceEndpoint: MenuNavigationItemRendererServiceEndpoint? = null,
    val trackingParams: String,
    val navigationEndpoint: MenuNavigationItemRendererNavigationEndpoint? = null
)

@Serializable
data class MenuNavigationItemRendererNavigationEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: DefaultNavigationEndpointCommandMetadata,
    val modalEndpoint: PurpleModalEndpoint
)

@Serializable
data class DefaultNavigationEndpointCommandMetadata (
    val webCommandMetadata: TentacledWebCommandMetadata
)

@Serializable
data class TentacledWebCommandMetadata (
    val ignoreNavigation: Boolean
)

@Serializable
data class PurpleModalEndpoint (
    val modal: PurpleModal
)

@Serializable
data class PurpleModal (
    val modalWithTitleAndButtonRenderer: PurpleModalWithTitleAndButtonRenderer
)

@Serializable
data class PurpleModalWithTitleAndButtonRenderer (
    val title: DetailsText,
    val content: DetailsText,
    val button: PurpleButton
)

@Serializable
data class PurpleButton (
    val buttonRenderer: PurpleButtonRenderer
)

@Serializable
data class PurpleButtonRenderer (
    val style: String,
    val size: String,
    val isDisabled: Boolean,
    val text: HeaderText,
    val navigationEndpoint: PurpleNavigationEndpoint,
    val trackingParams: String
)

@Serializable
data class PurpleNavigationEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val signInEndpoint: AdsEngagementPanelContentRenderer
)

@Serializable
data class AdsEngagementPanelContentRenderer (
    val hack: Boolean
)

@Serializable
data class MenuNavigationItemRendererServiceEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: PurpleCommandMetadata,
    val modalEndpoint: ServiceEndpointModalEndpoint? = null,
    val signalServiceEndpoint: FluffySignalServiceEndpoint? = null
)

@Serializable
data class PurpleCommandMetadata (
    val webCommandMetadata: StickyWebCommandMetadata
)

@Serializable
data class StickyWebCommandMetadata (
    val ignoreNavigation: Boolean? = null,
    val sendPost: Boolean? = null
)

@Serializable
data class ServiceEndpointModalEndpoint (
    val modal: FluffyModal
)

@Serializable
data class FluffyModal (
    val modalWithTitleAndButtonRenderer: FluffyModalWithTitleAndButtonRenderer
)

@Serializable
data class FluffyModalWithTitleAndButtonRenderer (
    val title: DetailsText,
    val content: DetailsText,
    val button: FluffyButton
)

@Serializable
data class FluffyButton (
    val buttonRenderer: FluffyButtonRenderer
)

@Serializable
data class FluffyButtonRenderer (
    val style: String,
    val size: String,
    val isDisabled: Boolean,
    val text: HeaderText,
    val navigationEndpoint: FluffyNavigationEndpoint,
    val trackingParams: String
)

@Serializable
data class FluffyNavigationEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val signInEndpoint: PurpleSignInEndpoint
)

@Serializable
data class PurpleSignInEndpoint (
    val nextEndpoint: CurrentVideoEndpointClass,
    val idamTag: String
)

@Serializable
data class CurrentVideoEndpointClass (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val watchEndpoint: CurrentVideoEndpointWatchEndpoint
)

@Serializable
data class CurrentVideoEndpointWatchEndpoint (
    @SerialName("videoId")
    val videoID: String,

    val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig
)

@Serializable
data class WatchEndpointSupportedOnesieConfig (
    val html5PlaybackOnesieConfig: Html5PlaybackOnesieConfig
)

@Serializable
data class Html5PlaybackOnesieConfig (
    val commonConfig: CommonConfig
)

@Serializable
data class FluffySignalServiceEndpoint (
    val signal: String,
    val actions: List<IndigoAction>
)

@Serializable
data class IndigoAction (
    val clickTrackingParams: String,
    val addToPlaylistCommand: AddToPlaylistCommand? = null,
    val openPopupAction: FluffyOpenPopupAction? = null
)

@Serializable
data class AddToPlaylistCommand (
    val openMiniplayer: Boolean,
    val openListPanel: Boolean,

    @SerialName("videoId")
    val videoID: String,

    val listType: String,
    val onCreateListCommand: OnCreateListCommand,

    @SerialName("videoIds")
    val videoIDS: List<String>
)

@Serializable
data class OnCreateListCommand (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val createPlaylistServiceEndpoint: CreatePlaylistServiceEndpoint
)

@Serializable
data class CreatePlaylistServiceEndpoint (
    @SerialName("videoIds")
    val videoIDS: List<String>,

    val params: String
)

@Serializable
data class FluffyOpenPopupAction (
    val popup: FluffyPopup,
    val popupType: String
)

@Serializable
data class FluffyPopup (
    val notificationActionRenderer: PopupNotificationActionRenderer
)

@Serializable
data class PopupNotificationActionRenderer (
    val responseText: HeaderText,
    val trackingParams: String
)

@Serializable
data class MenuFlexibleItemRendererTopLevelButton (
    val downloadButtonRenderer: DownloadButtonRenderer? = null,
    val buttonRenderer: TopLevelButtonButtonRenderer? = null
)

@Serializable
data class TopLevelButtonButtonRenderer (
    val style: String,
    val size: String,
    val isDisabled: Boolean,
    val text: DetailsText,
    val icon: Icon,
    val accessibility: Accessibility? = null,
    val tooltip: String,
    val trackingParams: String,
    val accessibilityData: DisabledAccessibilityData,
    val command: StickyCommand? = null,
    val serviceEndpoint: ServiceEndpointClass? = null
)

@Serializable
data class StickyCommand (
    val clickTrackingParams: String,
    val commandMetadata: DefaultNavigationEndpointCommandMetadata,
    val modalEndpoint: ServiceEndpointModalEndpoint
)

@Serializable
data class ServiceEndpointClass (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val shareEntityServiceEndpoint: ShareEntityServiceEndpoint
)

@Serializable
data class ShareEntityServiceEndpoint (
    val serializedShareEntity: String,
    val commands: List<ShareEntityServiceEndpointCommand>
)

@Serializable
data class ShareEntityServiceEndpointCommand (
    val clickTrackingParams: String,
    val openPopupAction: CommandOpenPopupAction
)

@Serializable
data class CommandOpenPopupAction (
    val popup: TentacledPopup,
    val popupType: String,
    val beReused: Boolean
)

@Serializable
data class TentacledPopup (
    val unifiedSharePanelRenderer: UnifiedSharePanelRenderer
)

@Serializable
data class UnifiedSharePanelRenderer (
    val trackingParams: String,
    val showLoadingSpinner: Boolean
)

@Serializable
data class DownloadButtonRenderer (
    val trackingParams: String,
    val style: String,
    val size: String,

    @SerialName("targetId")
    val targetID: String,

    val command: ServiceEndpoint
)

@Serializable
data class PurpleItem (
    val menuNavigationItemRenderer: MenuItemRenderer
)

@Serializable
data class TopLevelButtonElement (
    val segmentedLikeDislikeButtonRenderer: SegmentedLikeDislikeButtonRenderer? = null,
    val buttonRenderer: TopLevelButtonButtonRenderer? = null
)

@Serializable
data class SegmentedLikeDislikeButtonRenderer (
    val likeButton: LikeButton,
    val dislikeButton: LikeButton
)

@Serializable
data class LikeButton (
    val toggleButtonRenderer: ToggleButtonRenderer
)

@Serializable
data class ToggleButtonRenderer (
    val style: Style,
    val isToggled: Boolean,
    val isDisabled: Boolean,
    val defaultIcon: Icon,
    val accessibility: Accessibility,
    val trackingParams: String,
    val defaultTooltip: String,
    val toggledTooltip: String,
    val toggledStyle: Style,
    val defaultNavigationEndpoint: DefaultNavigationEndpoint,
    val accessibilityData: DisabledAccessibilityData,
    val toggleButtonSupportedData: ToggleButtonSupportedData,

    @SerialName("targetId")
    val targetID: String,

    val defaultText: ShortViewCountText? = null,
    val toggledText: ShortViewCountText? = null
)

@Serializable
data class DefaultNavigationEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: DefaultNavigationEndpointCommandMetadata,
    val modalEndpoint: DefaultNavigationEndpointModalEndpoint
)

@Serializable
data class DefaultNavigationEndpointModalEndpoint (
    val modal: TentacledModal
)

@Serializable
data class TentacledModal (
    val modalWithTitleAndButtonRenderer: TentacledModalWithTitleAndButtonRenderer
)

@Serializable
data class TentacledModalWithTitleAndButtonRenderer (
    val title: HeaderText,
    val content: HeaderText,
    val button: FluffyButton
)

@Serializable
data class Style (
    val styleType: String
)

@Serializable
data class ToggleButtonSupportedData (
    @SerialName("toggleButtonIdData")
    val toggleButtonIDData: ToggleButtonIDData
)

@Serializable
data class ToggleButtonIDData (
    val id: String
)

@Serializable
data class ViewCount (
    val videoViewCountRenderer: VideoViewCountRenderer
)

@Serializable
data class VideoViewCountRenderer (
    val viewCount: HeaderText,
    val shortViewCount: HeaderText
)

@Serializable
data class VideoSecondaryInfoRenderer (
    val owner: Owner,
    val description: Description,
    val subscribeButton: VideoSecondaryInfoRendererSubscribeButton,
    val metadataRowContainer: MetadataRowContainer,
    val showMoreText: HeaderText,
    val showLessText: HeaderText,
    val trackingParams: String,
    val defaultExpanded: Boolean,
    val descriptionCollapsedLines: Long,
    val showMoreCommand: ShowMoreCommand,
    val showLessCommand: OnTapCommandClass
)

@Serializable
data class Description (
    val runs: List<DescriptionRun>
)

@Serializable
data class DescriptionRun (
    val text: String,
    val navigationEndpoint: TentacledNavigationEndpoint? = null
)

@Serializable
data class TentacledNavigationEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val urlEndpoint: PurpleURLEndpoint
)

@Serializable
data class PurpleURLEndpoint (
    val url: String,
    val target: String,
    val nofollow: Boolean
)

@Serializable
data class MetadataRowContainer (
    val metadataRowContainerRenderer: MetadataRowContainerRenderer
)

@Serializable
data class MetadataRowContainerRenderer (
    val rows: List<Row>,
    val collapsedItemCount: Long,
    val trackingParams: String
)

@Serializable
data class Row (
    val metadataRowRenderer: MetadataRowRenderer
)

@Serializable
data class MetadataRowRenderer (
    val title: DetailsText,
    val contents: List<MetadataRowRendererContent>,
    val trackingParams: String
)

@Serializable
data class MetadataRowRendererContent (
    val runs: List<FluffyRun>
)

@Serializable
data class FluffyRun (
    val text: String,
    val navigationEndpoint: StickyNavigationEndpoint
)

@Serializable
data class StickyNavigationEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val urlEndpoint: CommonConfig
)

@Serializable
data class Owner (
    val videoOwnerRenderer: VideoOwnerRenderer
)

@Serializable
data class VideoOwnerRenderer (
    val thumbnail: WatermarkClass,
    val title: Byline,
    val subscriptionButton: DismissStrategy,
    val navigationEndpoint: ChannelNavigationEndpointClass,
    val subscriberCountText: ShortViewCountText,
    val trackingParams: String,
    val badges: List<OwnerBadgeElement>
)

@Serializable
data class OwnerBadgeElement (
    val metadataBadgeRenderer: OwnerBadgeMetadataBadgeRenderer
)

@Serializable
data class OwnerBadgeMetadataBadgeRenderer (
    val icon: Icon,
    val style: String,
    val tooltip: String,
    val trackingParams: String,
    val accessibilityData: Accessibility
)

@Serializable
data class ChannelNavigationEndpointClass (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val browseEndpoint: ChannelNavigationEndpointBrowseEndpoint
)

@Serializable
data class ChannelNavigationEndpointBrowseEndpoint (
    @SerialName("browseId")
    val browseID: String,

    @SerialName("canonicalBaseUrl")
    val canonicalBaseURL: String
)

@Serializable
data class Byline (
    val runs: List<BylineRun>
)

@Serializable
data class BylineRun (
    val text: String,
    val navigationEndpoint: ChannelNavigationEndpointClass
)

@Serializable
data class VideoSecondaryInfoRendererSubscribeButton (
    val buttonRenderer: SubscribeButtonButtonRenderer
)

@Serializable
data class SubscribeButtonButtonRenderer (
    val style: String,
    val size: String,
    val isDisabled: Boolean,
    val text: DetailsText,
    val navigationEndpoint: IndigoNavigationEndpoint,
    val trackingParams: String,

    @SerialName("targetId")
    val targetID: String
)

@Serializable
data class IndigoNavigationEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: DefaultNavigationEndpointCommandMetadata,
    val modalEndpoint: FluffyModalEndpoint
)

@Serializable
data class FluffyModalEndpoint (
    val modal: StickyModal
)

@Serializable
data class StickyModal (
    val modalWithTitleAndButtonRenderer: StickyModalWithTitleAndButtonRenderer
)

@Serializable
data class StickyModalWithTitleAndButtonRenderer (
    val title: HeaderText,
    val content: HeaderText,
    val button: TentacledButton
)

@Serializable
data class TentacledButton (
    val buttonRenderer: TentacledButtonRenderer
)

@Serializable
data class TentacledButtonRenderer (
    val style: String,
    val size: String,
    val isDisabled: Boolean,
    val text: HeaderText,
    val navigationEndpoint: IndecentNavigationEndpoint,
    val trackingParams: String
)

@Serializable
data class IndecentNavigationEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val signInEndpoint: FluffySignInEndpoint
)

@Serializable
data class FluffySignInEndpoint (
    val nextEndpoint: CurrentVideoEndpointClass,
    val continueAction: String,
    val idamTag: String
)

@Serializable
data class TwoColumnWatchNextResultsSecondaryResults (
    val secondaryResults: SecondaryResultsSecondaryResults
)

@Serializable
data class SecondaryResultsSecondaryResults (
    val results: List<SecondaryResultsResult>,
    val trackingParams: String,

    @SerialName("targetId")
    val targetID: String
)

@Serializable
data class SecondaryResultsResult (
    val compactVideoRenderer: CompactVideoRenderer? = null,
    val compactRadioRenderer: CompactRadioRenderer? = null,
    val continuationItemRenderer: ResultContinuationItemRenderer? = null
)

@Serializable
data class CompactRadioRenderer (
    @SerialName("playlistId")
    val playlistID: String,

    val thumbnail: CompactRadioRendererThumbnail,
    val title: HeaderText,
    val navigationEndpoint: SecondaryNavigationEndpointClass,
    val videoCountText: DetailsText,
    val secondaryNavigationEndpoint: SecondaryNavigationEndpointClass,
    val longBylineText: HeaderText,
    val trackingParams: String,
    val thumbnailText: DetailsText,
    val videoCountShortText: DetailsText,

    @SerialName("shareUrl")
    val shareURL: String,

    val thumbnailOverlays: List<CompactRadioRendererThumbnailOverlay>
)

@Serializable
data class SecondaryNavigationEndpointClass (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val watchEndpoint: SecondaryNavigationEndpointWatchEndpoint
)

@Serializable
data class SecondaryNavigationEndpointWatchEndpoint (
    @SerialName("videoId")
    val videoID: String,

    @SerialName("playlistId")
    val playlistID: String,

    val params: String,
    val continuePlayback: Boolean? = null,
    val loggingContext: LoggingContext,
    val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig
)

@Serializable
data class LoggingContext (
    val vssLoggingContext: VssLoggingContext
)

@Serializable
data class VssLoggingContext (
    val serializedContextData: String
)

@Serializable
data class CompactRadioRendererThumbnail (
    val thumbnails: List<ThumbnailElement>,
    val sampledThumbnailColor: SampledThumbnailColor
)

@Serializable
data class SampledThumbnailColor (
    val red: Long,
    val green: Long,
    val blue: Long
)

@Serializable
data class CompactRadioRendererThumbnailOverlay (
    val thumbnailOverlayBottomPanelRenderer: ThumbnailOverlayBottomPanelRenderer? = null,
    val thumbnailOverlayHoverTextRenderer: ThumbnailOverlayHoverTextRenderer? = null,
    val thumbnailOverlayNowPlayingRenderer: ThumbnailOverlayNowPlayingRenderer? = null
)

@Serializable
data class ThumbnailOverlayBottomPanelRenderer (
    val icon: Icon
)

@Serializable
data class ThumbnailOverlayHoverTextRenderer (
    val text: DetailsText,
    val icon: Icon
)

@Serializable
data class ThumbnailOverlayNowPlayingRenderer (
    val text: DetailsText
)

@Serializable
data class CompactVideoRenderer (
    @SerialName("videoId")
    val videoID: String,

    val thumbnail: WatermarkClass,
    val title: ShortViewCountText,
    val longBylineText: Byline,
    val publishedTimeText: HeaderText? = null,
    val viewCountText: Title,
    val lengthText: ShortViewCountText? = null,
    val navigationEndpoint: CompactVideoRendererNavigationEndpoint,
    val shortBylineText: Byline,
    val channelThumbnail: WatermarkClass,
    val trackingParams: String,
    val shortViewCountText: PurpleShortViewCountText,
    val menu: CompactVideoRendererMenu,
    val thumbnailOverlays: List<CompactVideoRendererThumbnailOverlay>,
    val accessibility: DisabledAccessibilityData,
    val richThumbnail: RichThumbnail? = null,
    val badges: List<PurpleBadge>? = null,
    val ownerBadges: List<OwnerBadgeElement>? = null
)

@Serializable
data class PurpleBadge (
    val metadataBadgeRenderer: PurpleMetadataBadgeRenderer
)

@Serializable
data class PurpleMetadataBadgeRenderer (
    val style: String,
    val label: String,
    val trackingParams: String,
    val icon: Icon? = null
)

@Serializable
data class CompactVideoRendererMenu (
    val menuRenderer: MenuMenuRenderer
)

@Serializable
data class MenuMenuRenderer (
    val items: List<FluffyItem>,
    val trackingParams: String,
    val accessibility: DisabledAccessibilityData,

    @SerialName("targetId")
    val targetID: String? = null
)

@Serializable
data class FluffyItem (
    val menuServiceItemRenderer: MenuItemRenderer
)

@Serializable
data class CompactVideoRendererNavigationEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val watchEndpoint: PurpleWatchEndpoint
)

@Serializable
data class PurpleWatchEndpoint (
    @SerialName("videoId")
    val videoID: String,

    val nofollow: Boolean,
    val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig
)

@Serializable
data class RichThumbnail (
    val movingThumbnailRenderer: MovingThumbnailRenderer
)

@Serializable
data class MovingThumbnailRenderer (
    val movingThumbnailDetails: MovingThumbnailDetails? = null,
    val enableHoveredLogging: Boolean,
    val enableOverlay: Boolean
)

@Serializable
data class MovingThumbnailDetails (
    val thumbnails: List<ThumbnailElement>,
    val logAsMovingThumbnail: Boolean
)

@Serializable
data class PurpleShortViewCountText (
    val accessibility: DisabledAccessibilityData? = null,
    val simpleText: String? = null,
    val runs: List<DetailsTextRun>? = null
)

@Serializable
data class CompactVideoRendererThumbnailOverlay (
    val thumbnailOverlayTimeStatusRenderer: ThumbnailOverlayTimeStatusRenderer? = null,
    val thumbnailOverlayToggleButtonRenderer: ThumbnailOverlayToggleButtonRenderer? = null,
    val thumbnailOverlayNowPlayingRenderer: ThumbnailOverlayNowPlayingRenderer? = null
)

@Serializable
data class ThumbnailOverlayTimeStatusRenderer (
    val text: ShortViewCountText,
    val style: String
)

@Serializable
data class ThumbnailOverlayToggleButtonRenderer (
    val isToggled: Boolean? = null,
    val untoggledIcon: Icon,
    val toggledIcon: Icon,
    val untoggledTooltip: String,
    val toggledTooltip: String,
    val untoggledServiceEndpoint: UntoggledServiceEndpoint,
    val toggledServiceEndpoint: RemoveFromWatchLaterCommand? = null,
    val untoggledAccessibility: DisabledAccessibilityData,
    val toggledAccessibility: DisabledAccessibilityData,
    val trackingParams: String
)

@Serializable
data class UntoggledServiceEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val playlistEditEndpoint: AddToWatchLaterCommandPlaylistEditEndpoint? = null,
    val signalServiceEndpoint: UntoggledServiceEndpointSignalServiceEndpoint? = null
)

@Serializable
data class UntoggledServiceEndpointSignalServiceEndpoint (
    val signal: String,
    val actions: List<IndecentAction>
)

@Serializable
data class IndecentAction (
    val clickTrackingParams: String,
    val addToPlaylistCommand: AddToPlaylistCommand
)

@Serializable
data class Title (
    val simpleText: String? = null,
    val runs: List<DetailsTextRun>? = null
)

@Serializable
data class ResultContinuationItemRenderer (
    val trigger: String,
    val continuationEndpoint: ContinuationEndpoint,
    val button: A11YSkipNavigationButtonClass
)

@Serializable
data class EngagementPanel (
    val engagementPanelSectionListRenderer: EngagementPanelSectionListRenderer
)

@Serializable
data class EngagementPanelSectionListRenderer (
    val content: EngagementPanelSectionListRendererContent,

    @SerialName("targetId")
    val targetID: String,

    val visibility: String,
    val loggingDirectives: LoggingDirectives,
    val panelIdentifier: String? = null,
    val header: Header? = null,
    val veType: Long? = null
)

@Serializable
data class EngagementPanelSectionListRendererContent (
    val adsEngagementPanelContentRenderer: AdsEngagementPanelContentRenderer? = null,
    val structuredDescriptionContentRenderer: StructuredDescriptionContentRenderer? = null,
    val sectionListRenderer: SectionListRenderer? = null
)

@Serializable
data class SectionListRenderer (
    val contents: List<SectionListRendererContent>,
    val trackingParams: String
)

@Serializable
data class SectionListRendererContent (
    val itemSectionRenderer: FluffyItemSectionRenderer
)

@Serializable
data class FluffyItemSectionRenderer (
    val contents: List<FluffyContent>,
    val trackingParams: String,
    val sectionIdentifier: String,

    @SerialName("targetId")
    val targetID: String
)

@Serializable
data class FluffyContent (
    val continuationItemRenderer: ContentContinuationItemRenderer
)

@Serializable
data class StructuredDescriptionContentRenderer (
    val items: List<StructuredDescriptionContentRendererItem>
)

@Serializable
data class StructuredDescriptionContentRendererItem (
    val videoDescriptionHeaderRenderer: VideoDescriptionHeaderRenderer? = null,
    val expandableVideoDescriptionBodyRenderer: ExpandableVideoDescriptionBodyRenderer? = null
)

@Serializable
data class ExpandableVideoDescriptionBodyRenderer (
    val descriptionBodyText: Description,
    val showMoreText: HeaderText,
    val showLessText: HeaderText
)

@Serializable
data class VideoDescriptionHeaderRenderer (
    val title: DetailsText,
    val channel: HeaderText,
    val views: HeaderText,
    val publishDate: HeaderText,
    val factoid: List<Factoid>,
    val channelNavigationEndpoint: ChannelNavigationEndpointClass,
    val channelThumbnail: ChannelThumbnail
)

@Serializable
data class ChannelThumbnail (
    val thumbnails: List<CommonConfig>
)

@Serializable
data class Factoid (
    val factoidRenderer: FactoidRenderer
)

@Serializable
data class FactoidRenderer (
    val value: HeaderText,
    val label: HeaderText,
    val accessibilityText: String
)

@Serializable
data class Header (
    val engagementPanelTitleHeaderRenderer: EngagementPanelTitleHeaderRenderer
)

@Serializable
data class EngagementPanelTitleHeaderRenderer (
    val title: Title,
    val visibilityButton: ClearButtonClass,
    val trackingParams: String,
    val contextualInfo: DetailsText? = null,
    val menu: EngagementPanelTitleHeaderRendererMenu? = null
)

@Serializable
data class EngagementPanelTitleHeaderRendererMenu (
    val sortFilterSubMenuRenderer: SortFilterSubMenuRenderer
)

@Serializable
data class SortFilterSubMenuRenderer (
    val subMenuItems: List<SubMenuItem>,
    val icon: Icon,
    val accessibility: DisabledAccessibilityData,
    val trackingParams: String
)

@Serializable
data class SubMenuItem (
    val title: String,
    val selected: Boolean,
    val serviceEndpoint: SubMenuItemServiceEndpoint,
    val trackingParams: String
)

@Serializable
data class SubMenuItemServiceEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val continuationCommand: ServiceEndpointContinuationCommand
)

@Serializable
data class ServiceEndpointContinuationCommand (
    val token: String,
    val request: String,
    val command: ContinuationCommandCommand
)

@Serializable
data class ContinuationCommandCommand (
    val clickTrackingParams: String,

    @SerialName("showReloadUiCommand")
    val showReloadUICommand: ScrollToEngagementPanelCommandClass
)

@Serializable
data class ClearButtonClass (
    val buttonRenderer: ClearButtonButtonRenderer
)

@Serializable
data class ClearButtonButtonRenderer (
    val icon: Icon,
    val trackingParams: String,
    val accessibilityData: DisabledAccessibilityData,
    val command: IndigoCommand? = null,
    val style: String? = null,
    val size: String? = null,
    val isDisabled: Boolean? = null
)

@Serializable
data class IndigoCommand (
    val clickTrackingParams: String,
    val commandExecutorCommand: FluffyCommandExecutorCommand? = null,
    val changeEngagementPanelVisibilityAction: ChangeEngagementPanelVisibilityAction? = null
)

@Serializable
data class FluffyCommandExecutorCommand (
    val commands: List<IndecentCommand>
)

@Serializable
data class IndecentCommand (
    val clickTrackingParams: String,
    val changeEngagementPanelVisibilityAction: ChangeEngagementPanelVisibilityAction? = null,
    val updateToggleButtonStateCommand: UpdateToggleButtonStateCommand? = null
)

@Serializable
data class UpdateToggleButtonStateCommand (
    val toggled: Boolean,

    @SerialName("buttonId")
    val buttonID: String
)

@Serializable
data class LoggingDirectives (
    val trackingParams: String,
    val visibility: Visibility,
    val enableDisplayloggerExperiment: Boolean
)

@Serializable
data class Visibility (
    val types: String
)

@Serializable
data class ResponseFrameworkUpdates (
    val entityBatchUpdate: FluffyEntityBatchUpdate
)

@Serializable
data class FluffyEntityBatchUpdate (
    val mutations: List<FluffyMutation>,
    val timestamp: Timestamp
)

@Serializable
data class FluffyMutation (
    val entityKey: String,
    val type: String,
    val options: Options
)

@Serializable
data class Options (
    val persistenceOption: String
)

@Serializable
data class OnResponseReceivedEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: OnResponseReceivedEndpointCommandMetadata? = null,
    val signalServiceEndpoint: CommandSignalServiceEndpoint? = null,
    val changeKeyedMarkersVisibilityCommand: ChangeKeyedMarkersVisibilityCommand? = null,
    val loadMarkersCommand: LoadMarkersCommand? = null
)

@Serializable
data class ChangeKeyedMarkersVisibilityCommand (
    val isVisible: Boolean,
    val key: String
)

@Serializable
data class OnResponseReceivedEndpointCommandMetadata (
    val webCommandMetadata: IndigoWebCommandMetadata
)

@Serializable
data class IndigoWebCommandMetadata (
    val sendPost: Boolean
)

@Serializable
data class LoadMarkersCommand (
    val entityKeys: List<String>
)

@Serializable
data class PageVisualEffect (
    val cinematicContainerRenderer: CinematicContainerRenderer
)

@Serializable
data class CinematicContainerRenderer (
    val gradientColorConfig: List<GradientColorConfig>,
    val presentationStyle: String,
    val config: CinematicContainerRendererConfig
)

@Serializable
data class CinematicContainerRendererConfig (
    val lightThemeBackgroundColor: Long,
    val darkThemeBackgroundColor: Long,
    val animationConfig: AnimationConfig,
    val colorSourceSizeMultiplier: Double,
    val applyClientImageBlur: Boolean,
    val bottomColorSourceHeightMultiplier: Double,
    val maxBottomColorSourceHeight: Long,
    val colorSourceWidthMultiplier: Double,
    val colorSourceHeightMultiplier: Long,
    val blurStrength: Long
)

@Serializable
data class AnimationConfig (
    @SerialName("minImageUpdateIntervalMs")
    val minImageUpdateIntervalMS: Long,

    @SerialName("crossfadeDurationMs")
    val crossfadeDurationMS: Long,

    val crossfadeStartOffset: Long,
    val maxFrameRate: Long
)

@Serializable
data class GradientColorConfig (
    val darkThemeColor: Long,
    val startLocation: Long? = null
)

@Serializable
data class PlayerOverlays (
    val playerOverlayRenderer: PlayerOverlayRenderer
)

@Serializable
data class PlayerOverlayRenderer (
    val endScreen: EndScreen,
    val autoplay: PlayerOverlayRendererAutoplay,
    val shareButton: ShareButtonClass,
    val addToMenu: AddToMenu,
    val videoDetails: PlayerOverlayRendererVideoDetails,
    val autonavToggle: AutonavToggle,
    val decoratedPlayerBarRenderer: PlayerOverlayRendererDecoratedPlayerBarRenderer
)

@Serializable
data class AddToMenu (
    val menuRenderer: Renderer
)

@Serializable
data class AutonavToggle (
    val autoplaySwitchButtonRenderer: AutoplaySwitchButtonRenderer
)

@Serializable
data class AutoplaySwitchButtonRenderer (
    val onEnabledCommand: OnEnabledCommand,
    val onDisabledCommand: OnDisabledCommand,
    val enabledAccessibilityData: DisabledAccessibilityData,
    val disabledAccessibilityData: DisabledAccessibilityData,
    val trackingParams: String
)

@Serializable
data class OnDisabledCommand (
    val clickTrackingParams: String,
    val disableAutoplayCommand: AdsEngagementPanelContentRenderer
)

@Serializable
data class OnEnabledCommand (
    val clickTrackingParams: String,
    val enableAutoplayCommand: AdsEngagementPanelContentRenderer
)

@Serializable
data class PlayerOverlayRendererAutoplay (
    val playerOverlayAutoplayRenderer: PlayerOverlayAutoplayRenderer
)

@Serializable
data class PlayerOverlayAutoplayRenderer (
    val title: HeaderText,
    val videoTitle: ShortViewCountText,
    val byline: Byline,
    val pauseText: HeaderText,
    val background: WatermarkClass,
    val countDownSecs: Long,
    val cancelButton: NextButtonClass,
    val nextButton: NextButtonClass,
    val trackingParams: String,
    val closeButton: A11YSkipNavigationButtonClass,
    val thumbnailOverlays: List<PlayerOverlayAutoplayRendererThumbnailOverlay>,
    val preferImmediateRedirect: Boolean,

    @SerialName("videoId")
    val videoID: String,

    val publishedTimeText: HeaderText,
    val webShowNewAutonavCountdown: Boolean,
    val webShowBigThumbnailEndscreen: Boolean,
    val shortViewCountText: ShortViewCountText,
    val countDownSecsForFullscreen: Long
)

@Serializable
data class NextButtonClass (
    val buttonRenderer: NextButtonButtonRenderer
)

@Serializable
data class NextButtonButtonRenderer (
    val style: String,
    val size: String,
    val isDisabled: Boolean,
    val text: HeaderText? = null,
    val accessibility: Accessibility,
    val trackingParams: String,
    val accessibilityData: DisabledAccessibilityData,
    val command: HilariousCommand? = null,
    val navigationEndpoint: CurrentVideoEndpointClass? = null
)

@Serializable
data class HilariousCommand (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val getSurveyCommand: GetSurveyCommand
)

@Serializable
data class GetSurveyCommand (
    val endpoint: GetSurveyCommandEndpoint,
    val action: String
)

@Serializable
data class GetSurveyCommandEndpoint (
    val watch: AdsEngagementPanelContentRenderer
)

@Serializable
data class PlayerOverlayAutoplayRendererThumbnailOverlay (
    val thumbnailOverlayTimeStatusRenderer: ThumbnailOverlayTimeStatusRenderer
)

@Serializable
data class PlayerOverlayRendererDecoratedPlayerBarRenderer (
    val decoratedPlayerBarRenderer: DecoratedPlayerBarRendererDecoratedPlayerBarRenderer
)

@Serializable
data class DecoratedPlayerBarRendererDecoratedPlayerBarRenderer (
    val playerBar: PlayerBar
)

@Serializable
data class PlayerBar (
    val multiMarkersPlayerBarRenderer: MultiMarkersPlayerBarRenderer
)

@Serializable
data class MultiMarkersPlayerBarRenderer (
    val visibleOnLoad: VisibleOnLoad,
    val markersMap: List<MarkersMap>
)

@Serializable
data class MarkersMap (
    val key: String,
    val value: Value
)

@Serializable
data class Value (
    val trackingParams: String,
    val heatmap: Heatmap
)

@Serializable
data class Heatmap (
    val heatmapRenderer: HeatmapRenderer
)

@Serializable
data class HeatmapRenderer (
    @SerialName("maxHeightDp")
    val maxHeightDP: Long,

    @SerialName("minHeightDp")
    val minHeightDP: Long,

    val showHideAnimationDurationMillis: Long,
    val heatMarkers: List<HeatMarker>,
    val heatMarkersDecorations: List<HeatMarkersDecoration>
)

@Serializable
data class HeatMarker (
    val heatMarkerRenderer: HeatMarkerRenderer
)

@Serializable
data class HeatMarkerRenderer (
    val timeRangeStartMillis: Long,
    val markerDurationMillis: Long,
    val heatMarkerIntensityScoreNormalized: Double
)

@Serializable
data class HeatMarkersDecoration (
    val timedMarkerDecorationRenderer: TimedMarkerDecorationRenderer
)

@Serializable
data class TimedMarkerDecorationRenderer (
    val visibleTimeRangeStartMillis: Long,
    val visibleTimeRangeEndMillis: Long,
    val decorationTimeMillis: Long,
    val label: DetailsText,
    val icon: String,
    val trackingParams: String
)

@Serializable
data class VisibleOnLoad (
    val key: String
)

@Serializable
data class EndScreen (
    val watchNextEndScreenRenderer: WatchNextEndScreenRenderer
)

@Serializable
data class WatchNextEndScreenRenderer (
    val results: List<WatchNextEndScreenRendererResult>,
    val title: HeaderText,
    val trackingParams: String
)

@Serializable
data class WatchNextEndScreenRendererResult (
    val endScreenVideoRenderer: EndScreenVideoRenderer? = null,
    val endScreenPlaylistRenderer: EndScreenPlaylistRenderer? = null
)

@Serializable
data class EndScreenPlaylistRenderer (
    @SerialName("playlistId")
    val playlistID: String,

    val title: HeaderText,
    val thumbnail: WatermarkClass,
    val longBylineText: HeaderText,
    val videoCountText: DetailsText,
    val navigationEndpoint: SecondaryNavigationEndpointClass,
    val trackingParams: String
)

@Serializable
data class EndScreenVideoRenderer (
    @SerialName("videoId")
    val videoID: String,

    val thumbnail: WatermarkClass,
    val title: ShortViewCountText,
    val shortBylineText: Byline,
    val lengthText: ShortViewCountText,
    val lengthInSeconds: Long,
    val navigationEndpoint: CurrentVideoEndpointClass,
    val trackingParams: String,
    val shortViewCountText: ShortViewCountText,
    val publishedTimeText: HeaderText,
    val thumbnailOverlays: List<EndScreenVideoRendererThumbnailOverlay>
)

@Serializable
data class EndScreenVideoRendererThumbnailOverlay (
    val thumbnailOverlayTimeStatusRenderer: ThumbnailOverlayTimeStatusRenderer? = null,
    val thumbnailOverlayNowPlayingRenderer: ThumbnailOverlayNowPlayingRenderer? = null
)

@Serializable
data class ShareButtonClass (
    val buttonRenderer: ShareButtonButtonRenderer
)

@Serializable
data class ShareButtonButtonRenderer (
    val style: String,
    val size: String,
    val isDisabled: Boolean,
    val icon: Icon,
    val navigationEndpoint: ServiceEndpointClass? = null,
    val tooltip: String,
    val trackingParams: String,
    val serviceEndpoint: ButtonRendererServiceEndpoint? = null,
    val accessibilityData: DisabledAccessibilityData? = null
)

@Serializable
data class ButtonRendererServiceEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: OnResponseReceivedEndpointCommandMetadata,
    val signalServiceEndpoint: TentacledSignalServiceEndpoint
)

@Serializable
data class TentacledSignalServiceEndpoint (
    val signal: String,
    val actions: List<HilariousAction>
)

@Serializable
data class HilariousAction (
    val clickTrackingParams: String,
    val openPopupAction: TentacledOpenPopupAction
)

@Serializable
data class TentacledOpenPopupAction (
    val popup: StickyPopup,
    val popupType: String
)

@Serializable
data class StickyPopup (
    val voiceSearchDialogRenderer: VoiceSearchDialogRenderer
)

@Serializable
data class VoiceSearchDialogRenderer (
    val placeholderHeader: DetailsText,
    val promptHeader: DetailsText,
    val exampleQuery1: DetailsText,
    val exampleQuery2: DetailsText,
    val promptMicrophoneLabel: DetailsText,
    val loadingHeader: DetailsText,
    val connectionErrorHeader: DetailsText,
    val connectionErrorMicrophoneLabel: DetailsText,
    val permissionsHeader: DetailsText,
    val permissionsSubtext: DetailsText,
    val disabledHeader: DetailsText,
    val disabledSubtext: DetailsText,
    val microphoneButtonAriaLabel: DetailsText,
    val exitButton: ClearButtonClass,
    val trackingParams: String,
    val microphoneOffPromptHeader: DetailsText
)

@Serializable
data class PlayerOverlayRendererVideoDetails (
    val playerOverlayVideoDetailsRenderer: PlayerOverlayVideoDetailsRenderer
)

@Serializable
data class PlayerOverlayVideoDetailsRenderer (
    val title: HeaderText,
    val subtitle: DetailsText
)

@Serializable
data class FluffyWebResponseContextExtensionData (
    val ytConfigData: YtConfigData,
    val webPrefetchData: WebPrefetchData,
    val hasDecorated: Boolean
)

@Serializable
data class WebPrefetchData (
    val navigationEndpoints: List<NavigationEndpointElement>
)

@Serializable
data class YtConfigData (
    val visitorData: String,
    val rootVisualElementType: Long
)

@Serializable
data class Topbar (
    val desktopTopbarRenderer: DesktopTopbarRenderer
)

@Serializable
data class DesktopTopbarRenderer (
    val logo: Logo,
    val searchbox: Searchbox,
    val trackingParams: String,
    val countryCode: String,
    val topbarButtons: List<TopbarButton>,
    val hotkeyDialog: HotkeyDialog,
    val backButton: BackButtonClass,
    val forwardButton: BackButtonClass,

    @SerialName("a11ySkipNavigationButton")
    val a11YSkipNavigationButton: A11YSkipNavigationButtonClass,

    val voiceSearchButton: ShareButtonClass
)

@Serializable
data class BackButtonClass (
    val buttonRenderer: BackButtonButtonRenderer
)

@Serializable
data class BackButtonButtonRenderer (
    val trackingParams: String,
    val command: AmbitiousCommand
)

@Serializable
data class AmbitiousCommand (
    val clickTrackingParams: String,
    val commandMetadata: OnResponseReceivedEndpointCommandMetadata,
    val signalServiceEndpoint: CommandSignalServiceEndpoint
)

@Serializable
data class HotkeyDialog (
    val hotkeyDialogRenderer: HotkeyDialogRenderer
)

@Serializable
data class HotkeyDialogRenderer (
    val title: DetailsText,
    val sections: List<Section>,
    val dismissButton: DismissButton,
    val trackingParams: String
)

@Serializable
data class Section (
    val hotkeyDialogSectionRenderer: HotkeyDialogSectionRenderer
)

@Serializable
data class HotkeyDialogSectionRenderer (
    val title: DetailsText,
    val options: List<Option>
)

@Serializable
data class Option (
    val hotkeyDialogSectionOptionRenderer: HotkeyDialogSectionOptionRenderer
)

@Serializable
data class HotkeyDialogSectionOptionRenderer (
    val label: DetailsText,
    val hotkey: String,
    val hotkeyAccessibilityLabel: DisabledAccessibilityData? = null
)

@Serializable
data class Logo (
    val topbarLogoRenderer: TopbarLogoRenderer
)

@Serializable
data class TopbarLogoRenderer (
    val iconImage: Icon,
    val tooltipText: DetailsText,
    val endpoint: FeaturedChannelNavigationEndpoint,
    val trackingParams: String,
    val overrideEntityKey: String
)

@Serializable
data class Searchbox (
    val fusionSearchboxRenderer: FusionSearchboxRenderer
)

@Serializable
data class FusionSearchboxRenderer (
    val icon: Icon,
    val placeholderText: DetailsText,
    val config: FusionSearchboxRendererConfig,
    val trackingParams: String,
    val searchEndpoint: FusionSearchboxRendererSearchEndpoint,
    val clearButton: ClearButtonClass
)

@Serializable
data class FusionSearchboxRendererConfig (
    val webSearchboxConfig: WebSearchboxConfig
)

@Serializable
data class WebSearchboxConfig (
    val requestLanguage: String,
    val requestDomain: String,
    val hasOnscreenKeyboard: Boolean,
    val focusSearchbox: Boolean
)

@Serializable
data class FusionSearchboxRendererSearchEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val searchEndpoint: SearchEndpointSearchEndpoint
)

@Serializable
data class SearchEndpointSearchEndpoint (
    val query: String
)

@Serializable
data class TopbarButton (
    val topbarMenuButtonRenderer: TopbarMenuButtonRenderer? = null,
    val buttonRenderer: TopbarButtonButtonRenderer? = null
)

@Serializable
data class TopbarButtonButtonRenderer (
    val style: String,
    val size: String,
    val text: DetailsText,
    val icon: Icon,
    val navigationEndpoint: HilariousNavigationEndpoint,
    val trackingParams: String,

    @SerialName("targetId")
    val targetID: String
)

@Serializable
data class HilariousNavigationEndpoint (
    val clickTrackingParams: String,
    val commandMetadata: EndpointCommandMetadata,
    val signInEndpoint: TentacledSignInEndpoint
)

@Serializable
data class TentacledSignInEndpoint (
    val idamTag: String
)

@Serializable
data class TopbarMenuButtonRenderer (
    val icon: Icon,
    val menuRequest: MenuRequest,
    val trackingParams: String,
    val accessibility: DisabledAccessibilityData,
    val tooltip: String,
    val style: String
)

@Serializable
data class MenuRequest (
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val signalServiceEndpoint: MenuRequestSignalServiceEndpoint
)

@Serializable
data class MenuRequestSignalServiceEndpoint (
    val signal: String,
    val actions: List<AmbitiousAction>
)

@Serializable
data class AmbitiousAction (
    val clickTrackingParams: String,
    val openPopupAction: StickyOpenPopupAction
)

@Serializable
data class StickyOpenPopupAction (
    val popup: IndigoPopup,
    val popupType: String,
    val beReused: Boolean
)

@Serializable
data class IndigoPopup (
    val multiPageMenuRenderer: MultiPageMenuRenderer
)

@Serializable
data class MultiPageMenuRenderer (
    val trackingParams: String,
    val style: String,
    val showLoadingSpinner: Boolean
)
