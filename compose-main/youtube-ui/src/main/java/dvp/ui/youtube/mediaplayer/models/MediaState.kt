package dvp.ui.youtube.mediaplayer.models

import dvp.ui.youtube.media.SurfaceType

data class MediaState(
    val duration: Long = 0,
    val progress: Long = 0,
    val buffered: Long = 0,
    val isLoading: Boolean = false,
    val isPlaying: Boolean = false,
    val surfaceType: SurfaceType = SurfaceType.SurfaceView,
    val bps: Float = 0.0f,
    val isServiceRunning: Boolean = false,
    val playlist: List<MediaData> = emptyList()
)