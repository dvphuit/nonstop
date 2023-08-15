package dvp.lib.ytube.dto.init

import kotlinx.serialization.*

@Serializable
data class ContinuationDto (
    val continuationEndpoint: ContinuationEndpoint? = null
){
    fun getToken(): String? {
        return continuationEndpoint?.continuationCommand?.token
    }
}

@Serializable
data class ContinuationEndpoint (
    val continuationCommand: ContinuationCommand? = null
)

@Serializable
data class ContinuationCommand (
    val token: String? = null,
    val request: String? = null
)
