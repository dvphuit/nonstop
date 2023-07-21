package dvp.lib.ytube.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Snippet (
    val publishedAt: String? = null,

    @SerialName("channelId")
    val channelID: String? = null,

    val title: String? = null,
    val description: String? = null,
    val thumbnails: Thumbnails? = null,
    val channelTitle: String? = null,
    val tags: List<String>? = null,

    @SerialName("categoryId")
    val categoryID: String? = null,

    val liveBroadcastContent: String? = null,
    val localized: Localized? = null,
    val defaultLanguage: String? = null,
    val defaultAudioLanguage: String? = null
)