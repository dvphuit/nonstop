package dvp.data.youtube.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Microformat (
    val playerMicroformatRenderer: PlayerMicroformatRenderer
)

@Serializable
data class PlayerMicroformatRenderer (
    val thumbnail: WatermarkClass,
    val embed: Embed,
    val title: HeaderText,
    val description: HeaderText,
    val lengthSeconds: String,

    @SerialName("ownerProfileUrl")
    val ownerProfileURL: String,

    @SerialName("externalChannelId")
    val externalChannelID: String,

    val isFamilySafe: Boolean,
    val availableCountries: List<String>,
    val isUnlisted: Boolean,
    val hasYpcMetadata: Boolean,
    val viewCount: String,
    val category: String,
    val publishDate: String,
    val ownerChannelName: String,
    val uploadDate: String
)

@Serializable
data class HeaderText (
    val simpleText: String
)