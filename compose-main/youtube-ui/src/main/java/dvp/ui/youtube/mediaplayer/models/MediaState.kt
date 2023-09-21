package dvp.ui.youtube.mediaplayer.models

import androidx.media3.common.Player.COMMAND_SEEK_TO_NEXT
import androidx.media3.common.Player.COMMAND_SEEK_TO_PREVIOUS
import androidx.media3.common.Player.Commands
import dvp.ui.youtube.player.SurfaceType

data class MediaState(
    val durationMs: Long = 0,
    val currentMs: Long = 0,
    val bufferedMs: Long = 0,
    val progress: Long = 0,
    val buffered: Long = 0,
    val isLoading: Boolean = false,
    val isPlaying: Boolean = false,
    val isControllerShown: Boolean = false,
    val surfaceType: SurfaceType = SurfaceType.SurfaceView,
    val bps: Float = 0.0f,
    val isServiceRunning: Boolean = false,
    val playlist: List<MediaData> = emptyList(),
    val availableCommands: Commands = Commands.EMPTY
) {
    fun hasSeekToPrev() = availableCommands.contains(COMMAND_SEEK_TO_PREVIOUS)
    fun hasSeekToNext() = availableCommands.contains(COMMAND_SEEK_TO_NEXT)
}

fun MediaState.getProgressTime(): String = "${currentMs.msToTime()}/${durationMs.msToTime()}"
fun MediaState.getRemainingTime(): String = (durationMs - currentMs).msToTime()

private fun Long.msToTime(): String {
    val seconds = (this / 1000).toInt()
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60

    return when {
        hours > 0 -> String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
        minutes > 0 -> String.format("%02d:%02d", minutes, remainingSeconds)
        else -> String.format("%02d", remainingSeconds)
    }
}