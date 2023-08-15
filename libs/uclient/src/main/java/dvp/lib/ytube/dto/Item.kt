package dvp.lib.ytube.dto

import kotlinx.serialization.Serializable

@Serializable
data class Item (
    val kind: String? = null,
    val etag: String? = null,
    val id: String? = null,
    val snippet: Snippet? = null,
    val contentDetails: ContentDetails? = null,
    val statistics: Statistics? = null
)