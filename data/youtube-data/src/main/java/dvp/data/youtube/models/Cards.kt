package dvp.data.youtube.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cards (
    val cardCollectionRenderer: CardCollectionRenderer
)

@Serializable
data class CardCollectionRenderer (
    val cards: List<Card>,
    val headerText: HeaderText,
    val icon: CloseButton,
    val closeButton: CloseButton,
    val trackingParams: String,
    val allowTeaserDismiss: Boolean,
    val logIconVisibilityUpdates: Boolean
)

@Serializable
data class Card (
    val cardRenderer: CardRenderer
)


@Serializable
data class CardRenderer (
    val teaser: Teaser,
    val cueRanges: List<CueRange>,
    val trackingParams: String
)

@Serializable
data class CueRange (
    @SerialName("startCardActiveMs")
    val startCardActiveMS: String,

    @SerialName("endCardActiveMs")
    val endCardActiveMS: String,

    @SerialName("teaserDurationMs")
    val teaserDurationMS: String,

    @SerialName("iconAfterTeaserMs")
    val iconAfterTeaserMS: String
)

@Serializable
data class CloseButton (
    val infoCardIconRenderer: Renderer
)
