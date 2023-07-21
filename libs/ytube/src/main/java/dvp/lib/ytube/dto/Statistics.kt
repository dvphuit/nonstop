package dvp.lib.ytube.dto

import kotlinx.serialization.Serializable

@Serializable
data class Statistics (
    val viewCount: String? = null,
    val likeCount: String? = null,
    val favoriteCount: String? = null,
    val commentCount: String? = null
)
