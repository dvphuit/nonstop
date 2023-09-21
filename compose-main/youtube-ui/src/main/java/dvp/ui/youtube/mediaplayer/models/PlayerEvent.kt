package dvp.ui.youtube.mediaplayer.models

sealed class PlayerEvent {
    data class Init(val media: MediaData): PlayerEvent()
    data class SetPlaylist(val media: List<MediaData>): PlayerEvent()
    object PlayPause : PlayerEvent()
    object NextVideo : PlayerEvent()
    object PreVideo : PlayerEvent()
    data class Seek(val nextMs: Long): PlayerEvent()

    object Stop : PlayerEvent()
    object Background : PlayerEvent()
    object Foreground : PlayerEvent()
}

