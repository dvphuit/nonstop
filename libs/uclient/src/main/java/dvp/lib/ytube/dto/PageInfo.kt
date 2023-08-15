package dvp.lib.ytube.dto

import kotlinx.serialization.Serializable

@Serializable
data class PageInfo (
    val totalResults: Long? = null,
    val resultsPerPage: Long? = null
)
