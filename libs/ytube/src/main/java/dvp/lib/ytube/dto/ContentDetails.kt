package dvp.lib.ytube.dto

import kotlinx.serialization.Serializable

@Serializable
data class ContentDetails (
    val duration: String? = null,
    val dimension: String? = null,
    val definition: String? = null,
    val caption: String? = null,
    val licensedContent: Boolean? = null,
    val regionRestriction: RegionRestriction? = null,
    val contentRating: ContentRating? = null,
    val projection: String? = null
)