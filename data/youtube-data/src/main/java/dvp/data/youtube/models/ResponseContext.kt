package dvp.data.youtube.models

import kotlinx.serialization.Serializable


@Serializable
data class ResponseContext (
    val serviceTrackingParams: List<ServiceTrackingParam>,
    val mainAppWebResponseContext: MainAppWebResponseContext,
    val webResponseContextExtensionData: PurpleWebResponseContextExtensionData
)

@Serializable
data class MainAppWebResponseContext (
    val loggedOut: Boolean
)

@Serializable
data class ServiceTrackingParam (
    val service: String,
    val params: List<Param>
)

@Serializable
data class Param (
    val key: String,
    val value: String
)

@Serializable
data class PurpleWebResponseContextExtensionData (
    val hasDecorated: Boolean
)