package dvp.data.youtube.models

import kotlinx.serialization.Serializable


@Serializable
data class PlayerResponseFrameworkUpdates(
    val entityBatchUpdate: PurpleEntityBatchUpdate
)

@Serializable
data class PurpleEntityBatchUpdate(
    val mutations: List<PurpleMutation>,
    val timestamp: Timestamp
)