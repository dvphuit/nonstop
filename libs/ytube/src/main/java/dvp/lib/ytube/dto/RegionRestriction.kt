package dvp.lib.ytube.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegionRestriction (
    val blocked: List<String>? = null,
    val allowed: List<String>? = null
)