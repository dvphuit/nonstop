package dvp.data.youtube.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attestation (
    val playerAttestationRenderer: PlayerAttestationRenderer
)

@Serializable
data class PlayerAttestationRenderer (
    val challenge: String,
    val botguardData: BotguardData
)

@Serializable
data class BotguardData (
    val program: String,

    @SerialName("interpreterSafeUrl")
    val interpreterSafeURL: InterpreterSafeURL,

    val serverEnvironment: Long
)

@Serializable
data class InterpreterSafeURL (
    @SerialName("privateDoNotAccessOrElseTrustedResourceUrlWrappedValue")
    val privateDoNotAccessOrElseTrustedResourceURLWrappedValue: String
)