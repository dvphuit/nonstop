package dvp.lib.ytube.dto

import kotlinx.serialization.Serializable

@Serializable
data class Localized (
    val title: String? = null,
    val description: String? = null
)
