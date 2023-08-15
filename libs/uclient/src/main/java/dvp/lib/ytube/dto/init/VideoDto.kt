package dvp.lib.ytube.dto.init


import kotlinx.serialization.*

@Serializable
data class VideoDto (
    val videoId: String? = null,
    val thumbnail: ListThumbnail? = null,
    val title: RunTexts? = null,
    val descriptionSnippet: RunTexts? = null,
    val publishedTimeText: Text? = null,
    val lengthText: Text? = null,
    val viewCountText: Text? = null,
    val ownerBadges: List<Badge>? = null,
    val ownerText: RunTexts? = null,
    val channelThumbnailSupportedRenderers: ChannelThumbnail? = null
)


@Serializable
data class NavigationEndpoint (
    val clickTrackingParams: String? = null,
    val commandMetadata: CommandMetadata? = null,
    val browseEndpoint: BrowseEndpoint? = null
)

@Serializable
data class BrowseEndpoint (
    val browseId: String? = null,
    val canonicalBaseUrl: String? = null
)

@Serializable
data class CommandMetadata (
    val webCommandMetadata: WebCommandMetadata? = null
)

@Serializable
data class WebCommandMetadata (
    val url: String? = null,
    val webPageType: String? = null,
    val rootVe: Long? = null,
    val apiUrl: String? = null
)


@Serializable
data class ChannelThumbnail (
    val channelThumbnailWithLinkRenderer: ChannelThumbnailWithLinkRenderer? = null
)

@Serializable
data class ChannelThumbnailWithLinkRenderer (
    val thumbnail: ListThumbnail? = null
)

@Serializable
data class RunTexts (
    val runs: List<Run>? = null
)

@Serializable
data class Run (
    val text: String? = null,
    val navigationEndpoint: NavigationEndpoint? = null
)


@Serializable
data class Badge (
    val metadataBadgeRenderer: MetadataBadgeRenderer? = null
)

@Serializable
data class MetadataBadgeRenderer (
    val icon: Icon? = null,
    val style: String? = null
)

@Serializable
data class Icon (
    val iconType: String? = null
)
