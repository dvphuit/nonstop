package dvp.data.youtube.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Overlay(
    val tooltipRenderer: TooltipRenderer
)

@Serializable
data class TooltipRenderer(
    val promoConfig: PromoConfig,

    @SerialName("targetId")
    val targetID: String,

    val text: DetailsText,
    val detailsText: DetailsText,
    val dismissButton: DismissButton,
    val suggestedPosition: DismissStrategy,
    val dismissStrategy: DismissStrategy,

    @SerialName("dwellTimeMs")
    val dwellTimeMS: String,

    val trackingParams: String
)

@Serializable
data class DismissButton(
    val buttonRenderer: DismissButtonButtonRenderer
)

@Serializable
data class DismissButtonButtonRenderer(
    val size: String,
    val text: DetailsText,
    val trackingParams: String,
    val command: FluffyCommand? = null,
    val style: String? = null,
    val isDisabled: Boolean? = null
)

@Serializable
data class FluffyCommand(
    val clickTrackingParams: String,
    val commandExecutorCommand: PurpleCommandExecutorCommand
)

@Serializable
data class PurpleCommandExecutorCommand(
    val commands: List<AcceptCommand>
)

@Serializable
data class AcceptCommand(
    val clickTrackingParams: String,
    val commandMetadata: UnsubscribeCommandCommandMetadata,
    val feedbackEndpoint: FeedbackEndpoint
)
