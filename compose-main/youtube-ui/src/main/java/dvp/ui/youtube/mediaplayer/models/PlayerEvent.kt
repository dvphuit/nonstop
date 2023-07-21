package dvp.ui.youtube.mediaplayer.models

sealed class PlayerEvent {
    data class Init(val media: MediaData): PlayerEvent()
    object PlayPause : PlayerEvent()
    object Backward : PlayerEvent()
    object Forward : PlayerEvent()
    object Stop : PlayerEvent()
    object Background : PlayerEvent()
    object Foreground : PlayerEvent()
    data class UpdateProgress(val newProgress: Float) : PlayerEvent()
}

