package dvp.ui.youtube.service.notification

import android.app.PendingIntent
import android.content.Context
import androidx.annotation.IntRange
import androidx.media3.common.util.NotificationUtil
import androidx.media3.common.util.NotificationUtil.Importance
import androidx.media3.common.util.UnstableApi
import dvp.ui.youtube.R

@UnstableApi
internal class NotificationBuilder(
    private val context: Context,
    @param:IntRange(from = 1) private val notificationId: Int,
    private val channelId: String,
    pendingIntent: PendingIntent?
) {
    private var notificationListener: NotificationListener? = null
    private var customActionReceiver: CustomActionReceiver? = null
    private var mediaDescriptionAdapter: MediaDescriptionAdapter
    private var channelNameResourceId = 0
    private var channelDescriptionResourceId = 0
    private var channelImportance: Int
    private var smallIconResourceId: Int
    private var rewindActionIconResourceId: Int
    private var playActionIconResourceId: Int
    private var pauseActionIconResourceId: Int
    private var stopActionIconResourceId: Int
    private var fastForwardActionIconResourceId: Int
    private var previousActionIconResourceId: Int
    private var nextActionIconResourceId: Int
    private var groupKey: String? = null

    init {
        require(notificationId > 0)
        channelImportance = NotificationUtil.IMPORTANCE_LOW
        mediaDescriptionAdapter = DefaultMediaDescriptionAdapter(context, pendingIntent)
        smallIconResourceId = R.drawable.ic_launcher_lockscreen
        playActionIconResourceId = R.drawable.ic_play
        pauseActionIconResourceId = R.drawable.ic_pause
        stopActionIconResourceId = R.drawable.ic_stop
        rewindActionIconResourceId = R.drawable.ic_rewind_md
        fastForwardActionIconResourceId = R.drawable.ic_forward_md
        previousActionIconResourceId = R.drawable.ic_prev
        nextActionIconResourceId = R.drawable.ic_next
    }

    fun setChannelNameResourceId(channelNameResourceId: Int): NotificationBuilder {
        this.channelNameResourceId = channelNameResourceId
        return this
    }

    fun setChannelDescriptionResourceId(channelDescriptionResourceId: Int): NotificationBuilder {
        this.channelDescriptionResourceId = channelDescriptionResourceId
        return this
    }

    fun setChannelImportance(channelImportance: @Importance Int): NotificationBuilder {
        this.channelImportance = channelImportance
        return this
    }

    fun setNotificationListener(notificationListener: NotificationListener?): NotificationBuilder {
        this.notificationListener = notificationListener
        return this
    }

    fun setCustomActionReceiver(customActionReceiver: CustomActionReceiver?): NotificationBuilder {
        this.customActionReceiver = customActionReceiver
        return this
    }

    fun setSmallIconResourceId(smallIconResourceId: Int): NotificationBuilder {
        this.smallIconResourceId = smallIconResourceId
        return this
    }

    fun setPlayActionIconResourceId(playActionIconResourceId: Int): NotificationBuilder {
        this.playActionIconResourceId = playActionIconResourceId
        return this
    }

    fun setPauseActionIconResourceId(pauseActionIconResourceId: Int): NotificationBuilder {
        this.pauseActionIconResourceId = pauseActionIconResourceId
        return this
    }

    fun setStopActionIconResourceId(stopActionIconResourceId: Int): NotificationBuilder {
        this.stopActionIconResourceId = stopActionIconResourceId
        return this
    }

    fun setRewindActionIconResourceId(rewindActionIconResourceId: Int): NotificationBuilder {
        this.rewindActionIconResourceId = rewindActionIconResourceId
        return this
    }

    fun setFastForwardActionIconResourceId(fastForwardActionIconResourceId: Int): NotificationBuilder {
        this.fastForwardActionIconResourceId = fastForwardActionIconResourceId
        return this
    }

    fun setPreviousActionIconResourceId(previousActionIconResourceId: Int): NotificationBuilder {
        this.previousActionIconResourceId = previousActionIconResourceId
        return this
    }

    fun setNextActionIconResourceId(nextActionIconResourceId: Int): NotificationBuilder {
        this.nextActionIconResourceId = nextActionIconResourceId
        return this
    }

    fun setGroup(groupKey: String?): NotificationBuilder {
        this.groupKey = groupKey
        return this
    }

    fun setMediaDescriptionAdapter(mediaDescriptionAdapter: MediaDescriptionAdapter): NotificationBuilder {
        this.mediaDescriptionAdapter = mediaDescriptionAdapter
        return this
    }

    fun build(): PlayerNotificationManager {
        if (channelNameResourceId != 0) {
            NotificationUtil.createNotificationChannel(
                context,
                channelId,
                channelNameResourceId,
                channelDescriptionResourceId,
                channelImportance
            )
        }
        return PlayerNotificationManager(
            context,
            channelId,
            notificationId,
            mediaDescriptionAdapter,
            notificationListener,
            customActionReceiver,
            smallIconResourceId,
            playActionIconResourceId,
            pauseActionIconResourceId,
            stopActionIconResourceId,
            rewindActionIconResourceId,
            fastForwardActionIconResourceId,
            previousActionIconResourceId,
            nextActionIconResourceId,
            groupKey
        )
    }
}