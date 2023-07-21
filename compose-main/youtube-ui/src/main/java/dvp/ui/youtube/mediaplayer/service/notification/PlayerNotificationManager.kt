package dvp.ui.youtube.mediaplayer.service.notification

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v4.media.session.MediaSessionCompat
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.common.util.Assertions
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util


import androidx.media3.ui.PlayerNotificationManager.ACTION_PREVIOUS
import dvp.ui.youtube.R
import dvp.ui.youtube.mediaplayer.service.notification.Const.ACTION_DISMISS
import dvp.ui.youtube.mediaplayer.service.notification.Const.ACTION_FAST_FORWARD
import dvp.ui.youtube.mediaplayer.service.notification.Const.ACTION_NEXT
import dvp.ui.youtube.mediaplayer.service.notification.Const.ACTION_PAUSE
import dvp.ui.youtube.mediaplayer.service.notification.Const.ACTION_PLAY
import dvp.ui.youtube.mediaplayer.service.notification.Const.ACTION_REWIND
import dvp.ui.youtube.mediaplayer.service.notification.Const.ACTION_STOP
import dvp.ui.youtube.mediaplayer.service.notification.Const.EXTRA_INSTANCE_ID

@UnstableApi
internal class PlayerNotificationManager internal constructor(
    context: Context,
    channelId: String,
    notificationId: Int,
    mediaDescriptionAdapter: MediaDescriptionAdapter,
    notificationListener: NotificationListener?,
    customActionReceiver: CustomActionReceiver?,
    smallIconResourceId: Int,
    playActionIconResourceId: Int,
    pauseActionIconResourceId: Int,
    stopActionIconResourceId: Int,
    rewindActionIconResourceId: Int,
    fastForwardActionIconResourceId: Int,
    previousActionIconResourceId: Int,
    nextActionIconResourceId: Int,
    groupKey: String?
) {
    @Target(AnnotationTarget.TYPE)
    @IntDef(NotificationCompat.VISIBILITY_PRIVATE, NotificationCompat.VISIBILITY_PUBLIC, NotificationCompat.VISIBILITY_SECRET)
    annotation class Visibility

    @Target(AnnotationTarget.TYPE)
    @IntDef(
        NotificationCompat.PRIORITY_DEFAULT,
        NotificationCompat.PRIORITY_MAX,
        NotificationCompat.PRIORITY_HIGH,
        NotificationCompat.PRIORITY_LOW,
        NotificationCompat.PRIORITY_MIN
    )
    annotation class Priority

    private val context: Context
    private val channelId: String
    private val notificationId: Int

    private val mediaDescriptionAdapter: MediaDescriptionAdapter
    private val notificationListener: NotificationListener?
    private val customActionReceiver: CustomActionReceiver?
    private val mainHandler: Handler
    private val notificationManager: NotificationManagerCompat
    private val intentFilter: IntentFilter
    private val playerListener: Player.Listener
    private val notificationBroadcastReceiver: NotificationBroadcastReceiver
    private val playbackActions: Map<String, NotificationCompat.Action>
    private val customActions: Map<String, NotificationCompat.Action>
    private val dismissPendingIntent: PendingIntent
    private val instanceId: Int
    private var builder: NotificationCompat.Builder? = null
    private var builderActions: List<NotificationCompat.Action>? = null
    private var player: Player? = null
    private var isNotificationStarted = false
    private var currentNotificationTag = 0
    private var mediaSessionToken: MediaSessionCompat.Token? = null
    private var usePreviousAction: Boolean
    private var useNextAction: Boolean
    private var usePreviousActionInCompactView = false
    private var useNextActionInCompactView = false
    private var useRewindAction: Boolean
    private var useFastForwardAction: Boolean
    private var useRewindActionInCompactView = false
    private var useFastForwardActionInCompactView = false
    private var usePlayPauseActions: Boolean
    private var useStopAction = false
    private var badgeIconType: Int
    private var colorized: Boolean
    private var defaults: Int
    private var color: Int

    @DrawableRes
    private var smallIconResourceId: Int
    private var visibility: Int
    private var priority: @Priority Int
    private var useChronometer: Boolean
    private val groupKey: String?

    init {
        this.context = context
        this.channelId = channelId
        this.notificationId = notificationId
        this.mediaDescriptionAdapter = mediaDescriptionAdapter
        this.notificationListener = notificationListener
        this.customActionReceiver = customActionReceiver
        this.smallIconResourceId = smallIconResourceId
        this.groupKey = groupKey
        instanceId = instanceIdCounter++
        // This fails the nullness checker because handleMessage() is 'called' while `this` is still
        // @UnderInitialization. No tasks are scheduled on mainHandler before the constructor completes,
        // so this is safe and we can suppress the warning.
        this.mainHandler = Util.createHandler(Looper.getMainLooper()) { msg: Message -> handleMessage(msg) }
        notificationManager = NotificationManagerCompat.from(context)
        playerListener = PlayerListener()
        notificationBroadcastReceiver = NotificationBroadcastReceiver()
        intentFilter = IntentFilter()
        usePreviousAction = true
        useNextAction = true
        usePlayPauseActions = true
        useRewindAction = true
        useFastForwardAction = true
        colorized = true
        useChronometer = true
        color = Color.TRANSPARENT
        defaults = 0
        priority = NotificationCompat.PRIORITY_LOW
        badgeIconType = NotificationCompat.BADGE_ICON_SMALL
        visibility = NotificationCompat.VISIBILITY_PUBLIC

        // initialize actions
        playbackActions = createPlaybackActions(
            context,
            instanceId,
            playActionIconResourceId,
            pauseActionIconResourceId,
            stopActionIconResourceId,
            rewindActionIconResourceId,
            fastForwardActionIconResourceId,
            previousActionIconResourceId,
            nextActionIconResourceId
        )
        for (action in playbackActions.keys) {
            intentFilter.addAction(action)
        }
        customActions = customActionReceiver?.createCustomActions(context, instanceId) ?: emptyMap()
        for (action in customActions.keys) {
            intentFilter.addAction(action)
        }
        dismissPendingIntent = createBroadcastIntent(ACTION_DISMISS, context, instanceId)
        intentFilter.addAction(ACTION_DISMISS)
    }


    fun setPlayer(player: Player?) {
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper())
        Assertions.checkArgument(player == null || player.applicationLooper == Looper.getMainLooper())
        if (this.player === player) {
            return
        }
        if (this.player != null) {
            this.player!!.removeListener(playerListener)
            if (player == null) {
                stopNotification(false)
            }
        }
        this.player = player
        if (player != null) {
            player.addListener(playerListener)
            postStartOrUpdateNotification()
        }
    }

    fun setUseNextAction(useNextAction: Boolean) {
        if (this.useNextAction != useNextAction) {
            this.useNextAction = useNextAction
            invalidate()
        }
    }

    fun setUsePreviousAction(usePreviousAction: Boolean) {
        if (this.usePreviousAction != usePreviousAction) {
            this.usePreviousAction = usePreviousAction
            invalidate()
        }
    }

    fun setUseNextActionInCompactView(useNextActionInCompactView: Boolean) {
        if (this.useNextActionInCompactView != useNextActionInCompactView) {
            this.useNextActionInCompactView = useNextActionInCompactView
            if (useNextActionInCompactView) {
                useFastForwardActionInCompactView = false
            }
            invalidate()
        }
    }

    fun setUsePreviousActionInCompactView(usePreviousActionInCompactView: Boolean) {
        if (this.usePreviousActionInCompactView != usePreviousActionInCompactView) {
            this.usePreviousActionInCompactView = usePreviousActionInCompactView
            if (usePreviousActionInCompactView) {
                useRewindActionInCompactView = false
            }
            invalidate()
        }
    }

    fun setUseFastForwardAction(useFastForwardAction: Boolean) {
        if (this.useFastForwardAction != useFastForwardAction) {
            this.useFastForwardAction = useFastForwardAction
            invalidate()
        }
    }

    fun setUseRewindAction(useRewindAction: Boolean) {
        if (this.useRewindAction != useRewindAction) {
            this.useRewindAction = useRewindAction
            invalidate()
        }
    }

    fun setUseFastForwardActionInCompactView(
        useFastForwardActionInCompactView: Boolean
    ) {
        if (this.useFastForwardActionInCompactView != useFastForwardActionInCompactView) {
            this.useFastForwardActionInCompactView = useFastForwardActionInCompactView
            if (useFastForwardActionInCompactView) {
                useNextActionInCompactView = false
            }
            invalidate()
        }
    }

    fun setUseRewindActionInCompactView(useRewindActionInCompactView: Boolean) {
        if (this.useRewindActionInCompactView != useRewindActionInCompactView) {
            this.useRewindActionInCompactView = useRewindActionInCompactView
            if (useRewindActionInCompactView) {
                usePreviousActionInCompactView = false
            }
            invalidate()
        }
    }

    fun setUsePlayPauseActions(usePlayPauseActions: Boolean) {
        if (this.usePlayPauseActions != usePlayPauseActions) {
            this.usePlayPauseActions = usePlayPauseActions
            invalidate()
        }
    }

    fun setUseStopAction(useStopAction: Boolean) {
        if (this.useStopAction == useStopAction) {
            return
        }
        this.useStopAction = useStopAction
        invalidate()
    }

    fun setMediaSessionToken(token: MediaSessionCompat.Token?) {
        if (!Util.areEqual(mediaSessionToken, token)) {
            mediaSessionToken = token
            invalidate()
        }
    }

    fun setBadgeIconType(@NotificationCompat.BadgeIconType badgeIconType: Int) {
        if (this.badgeIconType == badgeIconType) {
            return
        }
        when (badgeIconType) {
            NotificationCompat.BADGE_ICON_NONE, NotificationCompat.BADGE_ICON_SMALL, NotificationCompat.BADGE_ICON_LARGE -> this.badgeIconType =
                badgeIconType

            else -> throw IllegalArgumentException()
        }
        invalidate()
    }

    fun setColorized(colorized: Boolean) {
        if (this.colorized != colorized) {
            this.colorized = colorized
            invalidate()
        }
    }

    fun setDefaults(defaults: Int) {
        if (this.defaults != defaults) {
            this.defaults = defaults
            invalidate()
        }
    }

    fun setColor(color: Int) {
        if (this.color != color) {
            this.color = color
            invalidate()
        }
    }

    fun setPriority(priority: @Priority Int) {
        if (this.priority == priority) {
            return
        }
        when (priority) {
            NotificationCompat.PRIORITY_DEFAULT, NotificationCompat.PRIORITY_MAX, NotificationCompat.PRIORITY_HIGH, NotificationCompat.PRIORITY_LOW, NotificationCompat.PRIORITY_MIN -> this.priority =
                priority

            else -> throw IllegalArgumentException()
        }
        invalidate()
    }

    fun setSmallIcon(@DrawableRes smallIconResourceId: Int) {
        if (this.smallIconResourceId != smallIconResourceId) {
            this.smallIconResourceId = smallIconResourceId
            invalidate()
        }
    }

    fun setUseChronometer(useChronometer: Boolean) {
        if (this.useChronometer != useChronometer) {
            this.useChronometer = useChronometer
            invalidate()
        }
    }

    fun setVisibility(visibility: @Visibility Int) {
        if (this.visibility == visibility) {
            return
        }
        when (visibility) {
            NotificationCompat.VISIBILITY_PRIVATE, NotificationCompat.VISIBILITY_PUBLIC, NotificationCompat.VISIBILITY_SECRET ->
                this.visibility = visibility
            else -> throw IllegalStateException()
        }
        invalidate()
    }

    private fun invalidate() {
        if (isNotificationStarted) {
            postStartOrUpdateNotification()
        }
    }

    @SuppressLint("MissingPermission")
    private fun startOrUpdateNotification(player: Player, bitmap: Bitmap?) {
        val ongoing = getOngoing(player)
        builder = createNotification(player, builder, ongoing, bitmap)
        if (builder == null) {
            stopNotification(false)
            return
        }
        val notification = builder!!.build()
        notificationManager.notify(notificationId, notification)
        if (!isNotificationStarted) {
            Util.registerReceiverNotExported(context, notificationBroadcastReceiver, intentFilter)
        }
        notificationListener?.onNotificationPosted(
            notificationId, notification, ongoing || !isNotificationStarted
        )
        isNotificationStarted = true
    }

    private fun stopNotification(dismissedByUser: Boolean) {
        if (isNotificationStarted) {
            isNotificationStarted = false
            mainHandler.removeMessages(MSG_START_OR_UPDATE_NOTIFICATION)
            notificationManager.cancel(notificationId)
            context.unregisterReceiver(notificationBroadcastReceiver)
            notificationListener?.onNotificationCancelled(notificationId, dismissedByUser)
        }
    }

    private fun createNotification(
        player: Player,
        _builder: NotificationCompat.Builder?,
        ongoing: Boolean,
        _largeIcon: Bitmap?
    ): NotificationCompat.Builder? {
        var builder = _builder
        var largeIcon = _largeIcon
        if (player.playbackState == Player.STATE_IDLE && player.isCommandAvailable(Player.COMMAND_GET_TIMELINE)
            && player.currentTimeline.isEmpty
        ) {
            builderActions = null
            return null
        }
        val actionNames = getActions(player)
        val actions: MutableList<NotificationCompat.Action> = ArrayList(actionNames.size)
        for (i in actionNames.indices) {
            val actionName = actionNames[i]
            val action = if (playbackActions.containsKey(actionName)) playbackActions[actionName] else customActions[actionName]
            if (action != null) {
                actions.add(action)
            }
        }
        if (builder == null || actions != builderActions) {
            builder = NotificationCompat.Builder(context, channelId)
            builderActions = actions
            for (i in actions.indices) {
                builder.addAction(actions[i])
            }
        }
        val mediaStyle = androidx.media.app.NotificationCompat.MediaStyle()
        if (mediaSessionToken != null) {
            mediaStyle.setMediaSession(mediaSessionToken)
        }
        mediaStyle.setShowActionsInCompactView(*getActionIndicesForCompactView(actionNames, player))
        // Configure dismiss action prior to API 21 ('x' button).
        mediaStyle.setShowCancelButton(!ongoing)
        mediaStyle.setCancelButtonIntent(dismissPendingIntent)
        builder.setStyle(mediaStyle)

        // Set intent which is sent if the user selects 'clear all'
        builder.setDeleteIntent(dismissPendingIntent)

        // Set notification properties from getters.
        builder
            .setBadgeIconType(badgeIconType)
            .setOngoing(ongoing)
            .setColor(color)
            .setColorized(colorized)
            .setSmallIcon(smallIconResourceId)
            .setVisibility(visibility)
            .setPriority(priority)
            .setDefaults(defaults)

        if ((useChronometer
                    && player.isCommandAvailable(Player.COMMAND_GET_CURRENT_MEDIA_ITEM)
                    && player.isPlaying
                    && !player.isPlayingAd
                    && !player.isCurrentMediaItemDynamic) && player.playbackParameters.speed == 1f
        ) {
            builder
                .setWhen(System.currentTimeMillis() - player.contentPosition)
                .setShowWhen(true)
                .setUsesChronometer(true)
        } else {
            builder.setShowWhen(false).setUsesChronometer(false)
        }

        builder.setContentTitle(mediaDescriptionAdapter.getCurrentContentTitle(player))
        builder.setContentText(mediaDescriptionAdapter.getCurrentContentText(player))
        builder.setSubText(mediaDescriptionAdapter.getCurrentSubText(player))
        if (largeIcon == null) {
            largeIcon = mediaDescriptionAdapter.getCurrentLargeIcon(
                player, BitmapCallback(++currentNotificationTag)
            )
        }
        setLargeIcon(builder, largeIcon)
        builder.setContentIntent(mediaDescriptionAdapter.createCurrentContentIntent(player))
        if (groupKey != null) {
            builder.setGroup(groupKey)
        }
        builder.setOnlyAlertOnce(true)
        return builder
    }

    private fun getActions(player: Player): List<String> {
        val enablePrevious = player.isCommandAvailable(Player.COMMAND_SEEK_TO_PREVIOUS)
        val enableRewind = player.isCommandAvailable(Player.COMMAND_SEEK_BACK)
        val enableFastForward = player.isCommandAvailable(Player.COMMAND_SEEK_FORWARD)
        val enableNext = player.isCommandAvailable(Player.COMMAND_SEEK_TO_NEXT)
        val stringActions: MutableList<String> = ArrayList()
        if (usePreviousAction && enablePrevious) {
            stringActions.add(ACTION_PREVIOUS)
        }
        if (useRewindAction && enableRewind) {
            stringActions.add(ACTION_REWIND)
        }
        if (usePlayPauseActions) {
            if (shouldShowPauseButton(player)) {
                stringActions.add(ACTION_PAUSE)
            } else {
                stringActions.add(ACTION_PLAY)
            }
        }
        if (useFastForwardAction && enableFastForward) {
            stringActions.add(ACTION_FAST_FORWARD)
        }
        if (useNextAction && enableNext) {
            stringActions.add(ACTION_NEXT)
        }
        if (customActionReceiver != null) {
            stringActions.addAll(customActionReceiver.getCustomActions(player))
        }
        if (useStopAction) {
            stringActions.add(ACTION_STOP)
        }
        return stringActions
    }


    private fun getActionIndicesForCompactView(actionNames: List<String>, player: Player): IntArray {
        val pauseActionIndex = actionNames.indexOf(ACTION_PAUSE)
        val playActionIndex = actionNames.indexOf(ACTION_PLAY)
        val leftSideActionIndex =
            if (usePreviousActionInCompactView) actionNames.indexOf(ACTION_PREVIOUS) else if (useRewindActionInCompactView) actionNames.indexOf(
                ACTION_REWIND
            ) else -1
        val rightSideActionIndex =
            if (useNextActionInCompactView) actionNames.indexOf(ACTION_NEXT) else if (useFastForwardActionInCompactView) actionNames.indexOf(
                ACTION_FAST_FORWARD
            ) else -1
        val actionIndices = IntArray(3)
        var actionCounter = 0
        if (leftSideActionIndex != -1) {
            actionIndices[actionCounter++] = leftSideActionIndex
        }
        val shouldShowPauseButton = shouldShowPauseButton(player)
        if (pauseActionIndex != -1 && shouldShowPauseButton) {
            actionIndices[actionCounter++] = pauseActionIndex
        } else if (playActionIndex != -1 && !shouldShowPauseButton) {
            actionIndices[actionCounter++] = playActionIndex
        }
        if (rightSideActionIndex != -1) {
            actionIndices[actionCounter++] = rightSideActionIndex
        }
        return intArrayOf(*actionIndices, actionCounter)
    }

    private fun getOngoing(player: Player): Boolean {
        val playbackState = player.playbackState
        return ((playbackState == Player.STATE_BUFFERING || playbackState == Player.STATE_READY)
                && player.playWhenReady)
    }

    private fun shouldShowPauseButton(player: Player): Boolean {
        return player.playbackState != Player.STATE_ENDED && player.playbackState != Player.STATE_IDLE && player.playWhenReady
    }

    private fun postStartOrUpdateNotification() {
        if (!mainHandler.hasMessages(MSG_START_OR_UPDATE_NOTIFICATION)) {
            mainHandler.sendEmptyMessage(MSG_START_OR_UPDATE_NOTIFICATION)
        }
    }

    private fun postUpdateNotificationBitmap(bitmap: Bitmap, notificationTag: Int) {
        mainHandler.obtainMessage(MSG_UPDATE_NOTIFICATION_BITMAP, notificationTag, C.INDEX_UNSET, bitmap)
            .sendToTarget()
    }

    private fun handleMessage(msg: Message): Boolean {
        when (msg.what) {
            MSG_START_OR_UPDATE_NOTIFICATION -> if (player != null) {
                startOrUpdateNotification(player!!, null)
            }

            MSG_UPDATE_NOTIFICATION_BITMAP -> if (player != null && isNotificationStarted && currentNotificationTag == msg.arg1) {
                startOrUpdateNotification(player!!, msg.obj as Bitmap)
            }

            else -> return false
        }
        return true
    }

    private inner class PlayerListener : Player.Listener {
        override fun onEvents(player: Player, events: Player.Events) {
            if (events.containsAny(
                    Player.EVENT_PLAYBACK_STATE_CHANGED,
                    Player.EVENT_PLAY_WHEN_READY_CHANGED,
                    Player.EVENT_IS_PLAYING_CHANGED,
                    Player.EVENT_TIMELINE_CHANGED,
                    Player.EVENT_PLAYBACK_PARAMETERS_CHANGED,
                    Player.EVENT_POSITION_DISCONTINUITY,
                    Player.EVENT_REPEAT_MODE_CHANGED,
                    Player.EVENT_SHUFFLE_MODE_ENABLED_CHANGED,
                    Player.EVENT_MEDIA_METADATA_CHANGED
                )
            ) {
                postStartOrUpdateNotification()
            }
        }
    }

    inner class BitmapCallback(private val notificationTag: Int) {
        fun onBitmap(bitmap: Bitmap?) {
            bitmap?.let { postUpdateNotificationBitmap(it, notificationTag) }
        }
    }

    private inner class NotificationBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val player = player
            if (player == null || !isNotificationStarted || intent.getIntExtra(EXTRA_INSTANCE_ID, instanceId) != instanceId) {
                return
            }
            when (val action = intent.action) {
                ACTION_PLAY -> {
                    if (player.playbackState == Player.STATE_IDLE && player.isCommandAvailable(Player.COMMAND_PREPARE)) {
                        player.prepare()
                    } else if (player.playbackState == Player.STATE_ENDED && player.isCommandAvailable(Player.COMMAND_SEEK_TO_DEFAULT_POSITION)) {
                        player.seekToDefaultPosition()
                    }
                    if (player.isCommandAvailable(Player.COMMAND_PLAY_PAUSE)) {
                        player.play()
                    }
                }

                ACTION_PAUSE -> {
                    if (player.isCommandAvailable(Player.COMMAND_PLAY_PAUSE)) {
                        player.pause()
                    }
                }

                ACTION_PREVIOUS -> {
                    if (player.isCommandAvailable(Player.COMMAND_SEEK_TO_PREVIOUS)) {
                        player.seekToPrevious()
                    }
                }

                ACTION_REWIND -> {
                    if (player.isCommandAvailable(Player.COMMAND_SEEK_BACK)) {
                        player.seekBack()
                    }
                }

                ACTION_FAST_FORWARD -> {
                    if (player.isCommandAvailable(Player.COMMAND_SEEK_FORWARD)) {
                        player.seekForward()
                    }
                }

                ACTION_NEXT -> {
                    if (player.isCommandAvailable(Player.COMMAND_SEEK_TO_NEXT)) {
                        player.seekToNext()
                    }
                }

                ACTION_STOP -> {
                    if (player.isCommandAvailable(Player.COMMAND_STOP)) {
                        player.stop()
                    }
                    if (player.isCommandAvailable(Player.COMMAND_CHANGE_MEDIA_ITEMS)) {
                        player.clearMediaItems()
                    }
                }

                ACTION_DISMISS -> stopNotification(true)

                else -> {
                    if (action != null && customActionReceiver != null && customActions.containsKey(action)) {
                        customActionReceiver.onCustomAction(player, action, intent)
                    }
                }
            }
        }
    }

    companion object {
        private const val MSG_START_OR_UPDATE_NOTIFICATION = 0
        private const val MSG_UPDATE_NOTIFICATION_BITMAP = 1
        private var instanceIdCounter = 0

        private fun createPlaybackActions(
            context: Context,
            instanceId: Int,
            playActionIconResourceId: Int,
            pauseActionIconResourceId: Int,
            stopActionIconResourceId: Int,
            rewindActionIconResourceId: Int,
            fastForwardActionIconResourceId: Int,
            previousActionIconResourceId: Int,
            nextActionIconResourceId: Int
        ): Map<String, NotificationCompat.Action> {
            val actions: MutableMap<String, NotificationCompat.Action> = HashMap()
            actions[ACTION_PLAY] = NotificationCompat.Action(
                playActionIconResourceId,
                context.getString(R.string.exo_controls_play_description),
                createBroadcastIntent(ACTION_PLAY, context, instanceId)
            )
            actions[ACTION_PAUSE] = NotificationCompat.Action(
                pauseActionIconResourceId,
                context.getString(R.string.exo_controls_pause_description),
                createBroadcastIntent(ACTION_PAUSE, context, instanceId)
            )
            actions[ACTION_STOP] = NotificationCompat.Action(
                stopActionIconResourceId,
                context.getString(R.string.exo_controls_stop_description),
                createBroadcastIntent(ACTION_STOP, context, instanceId)
            )
            actions[ACTION_REWIND] = NotificationCompat.Action(
                rewindActionIconResourceId,
                context.getString(R.string.exo_controls_rewind_description),
                createBroadcastIntent(ACTION_REWIND, context, instanceId)
            )
            actions[ACTION_FAST_FORWARD] = NotificationCompat.Action(
                fastForwardActionIconResourceId,
                context.getString(R.string.exo_controls_fastforward_description),
                createBroadcastIntent(ACTION_FAST_FORWARD, context, instanceId)
            )
            actions[ACTION_PREVIOUS] = NotificationCompat.Action(
                previousActionIconResourceId,
                context.getString(R.string.exo_controls_previous_description),
                createBroadcastIntent(ACTION_PREVIOUS, context, instanceId)
            )
            actions[ACTION_NEXT] = NotificationCompat.Action(
                nextActionIconResourceId,
                context.getString(R.string.exo_controls_next_description),
                createBroadcastIntent(ACTION_NEXT, context, instanceId)
            )
            return actions
        }

        private fun createBroadcastIntent(
            action: String, context: Context, instanceId: Int
        ): PendingIntent {
            val intent = Intent(action).setPackage(context.packageName)
            intent.putExtra(EXTRA_INSTANCE_ID, instanceId)
            return PendingIntent.getBroadcast(
                context,
                instanceId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

        private fun setLargeIcon(builder: NotificationCompat.Builder, largeIcon: Bitmap?) {
            builder.setLargeIcon(largeIcon)
        }
    }
}