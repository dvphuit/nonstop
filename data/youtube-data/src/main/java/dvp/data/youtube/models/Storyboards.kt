package dvp.data.youtube.models

import kotlinx.serialization.Serializable

@Serializable
data class Storyboards (
    val playerStoryboardSpecRenderer: PlayerStoryboardSpecRenderer
)

@Serializable
data class PlayerStoryboardSpecRenderer (
    val spec: String
)