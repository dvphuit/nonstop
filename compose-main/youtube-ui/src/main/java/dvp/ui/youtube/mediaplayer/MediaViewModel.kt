package dvp.ui.youtube.mediaplayer

import android.net.Uri
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.TransferListener
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MergingMediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import dvp.lib.core.viewmodel.MviViewModel
import dvp.ui.youtube.mediaplayer.models.MediaData
import dvp.ui.youtube.mediaplayer.models.MediaState
import dvp.ui.youtube.mediaplayer.models.PlayerEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay


@androidx.annotation.OptIn(UnstableApi::class)
class MediaViewModel(
    val player: ExoPlayer,
) : MviViewModel<MediaState, PlayerEvent>(),
    Player.Listener,
    TransferListener {


    private val factory = ProgressiveMediaSource.Factory(
        DefaultHttpDataSource.Factory()
            .apply {
                this.setTransferListener(this@MediaViewModel)
            },
    )

    private var progressUpdater: Job? = null

    init {
        player.apply {
            this.clearVideoSurface()
            addListener(this@MediaViewModel)
            playWhenReady = true
            videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
            repeatMode = Player.REPEAT_MODE_ONE
        }

        QueueHelper.registerRelatedVideosChanged {
            updatePlaylist(it)
        }
        println("TEST: mediaViewModel created $this")
    }

    override fun submit(event: PlayerEvent) {
        println("TEST: mediaViewModel event = $event")
        when (event) {
            is PlayerEvent.Init -> setMedia(event.media)
            PlayerEvent.Backward -> player.seekBack()
            PlayerEvent.Forward -> player.seekForward()
            PlayerEvent.PlayPause -> {
                if (player.isPlaying) {
                    player.pause()
                    stopProgressUpdate()
                } else {
                    player.play()
                    startProgressUpdate()
                }

                updateData { copy(isPlaying = player.isPlaying) }
            }

            PlayerEvent.Stop -> {
                setMedia(null)
                stopProgressUpdate()
            }

            PlayerEvent.Background -> background()
            PlayerEvent.Foreground -> foreground()
            is PlayerEvent.SetPlaylist -> updatePlaylist(event.media)
            is PlayerEvent.UpdateProgress -> player.seekTo((player.duration * event.newProgress).toLong())
        }
    }

    private fun setMedia(media: MediaData?) {
        println("TEST: setMedia = $media")
        setData { MediaState() }
        if (media == null) return


        QueueHelper.set(media)

        player.run {
            setMediaSource(buildMediaItem(media))
            prepare()
            playWhenReady = true
        }
    }

    private fun updatePlaylist(playList: List<MediaData>) {
        if (playList.isEmpty()) return
        safeLaunch {
            playList.take(2).forEach {
                val mediaData = QueueHelper.getStreamingData(it.videoId)
                println("TEST: update playList -> item = ${mediaData.title}")
                val source = mediaData.run(::buildMediaItem)
                player.addMediaSource(source)
            }
        }
    }

    private fun buildMediaItem(data: MediaData): MergingMediaSource {
        val metadata = MediaMetadata.Builder()
            .setArtworkUri(Uri.parse(data.artWork))
            .setAlbumTitle(data.author)
            .setDisplayTitle(data.title)
            .build()

        val videoSrc = factory.createMediaSource(
            MediaItem.Builder().setUri(data.videoUrl).build()
        )

        val audioItem = MediaItem.Builder()
            .setUri(data.audioUrl)
            .setMediaMetadata(metadata)
            .build()
        val audioSrc = factory.createMediaSource(audioItem)

        return MergingMediaSource(audioSrc, videoSrc)
    }

    private fun background() {
        getReady()?.let {
            disableVideo(true)
        }
    }

    private fun foreground() {
        getReady()?.let {
            disableVideo(false)
        }
    }

    override fun onCleared() {
        println("TEST: MediaViewModel onCleared")
//        submit(PlayerEvent.Stop)
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            ExoPlayer.STATE_BUFFERING -> {
                println("TEST: onPlaybackStateChanged -> STATE_BUFFERING")
                updateData { copy(buffered = player.bufferedPosition) }
            }

            ExoPlayer.STATE_READY -> {
                println("TEST: onPlaybackStateChanged -> STATE_READY")
                updateData { copy(duration = player.duration) }
            }

            Player.STATE_ENDED -> {
                println("TEST: onPlaybackStateChanged -> STATE_ENDED")
            }

            Player.STATE_IDLE -> {
                println("TEST: onPlaybackStateChanged -> STATE_IDLE")
            }
        }
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        println("TEST: onIsPlayingChanged = $isPlaying")
        updateData { copy(isPlaying = isPlaying) }
        if (isPlaying) {
            startProgressUpdate()
        } else {
            stopProgressUpdate()
        }
    }


    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
        println("TEST: MediaViewModel -> onMediaItemTransition: reason=$reason, title=${mediaItem?.mediaMetadata?.displayTitle}")
        safeLaunch {
            QueueHelper.fetchNextStreams(player.currentMediaItemIndex)?.let {
                val source = buildMediaItem(it)
                player.addMediaSource(source)
            }
        }
    }

    private fun startProgressUpdate() = safeLaunch {
        while (progressUpdater != null) {
            progressUpdater?.run {
                updateData {
                    copy(
                        progress = player.currentPosition,
                        bps = sumBytesPerSec.toFloat() / 1024
                    )
                }
                sumBytesPerSec = 0
                delay(1000)
            }
        }
    }

    private fun stopProgressUpdate() = safeLaunch {
        progressUpdater?.cancel()
        progressUpdater = null
    }

    private fun disableVideo(disable: Boolean) {
        player.trackSelectionParameters = player.trackSelectionParameters
            .buildUpon()
            .setTrackTypeDisabled(C.TRACK_TYPE_VIDEO, disable)
            .build()
    }

    override fun onTransferInitializing(source: DataSource, dataSpec: DataSpec, isNetwork: Boolean) {
    }

    override fun onTransferStart(source: DataSource, dataSpec: DataSpec, isNetwork: Boolean) {
    }

    private var sumBytesPerSec = 0
    override fun onBytesTransferred(source: DataSource, dataSpec: DataSpec, isNetwork: Boolean, bytesTransferred: Int) {
        sumBytesPerSec += bytesTransferred
    }

    override fun onTransferEnd(source: DataSource, dataSpec: DataSpec, isNetwork: Boolean) {
    }
}