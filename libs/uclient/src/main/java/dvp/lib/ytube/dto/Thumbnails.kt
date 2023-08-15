package dvp.lib.ytube.dto

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnails (
    val default: Thumbnail? = null,
    val medium: Thumbnail? = null,
    val high: Thumbnail? = null,
    val standard: Thumbnail? = null,
    val maxres: Thumbnail? = null
)
