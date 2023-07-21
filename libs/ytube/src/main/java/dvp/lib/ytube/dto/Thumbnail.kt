package dvp.lib.ytube.dto

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail (
    val url: String? = null,
    val width: Long? = null,
    val height: Long? = null
)