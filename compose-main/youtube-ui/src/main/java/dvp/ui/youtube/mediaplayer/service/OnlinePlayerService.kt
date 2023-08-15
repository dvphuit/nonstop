package dvp.ui.youtube.mediaplayer.service

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.PendingIntentCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.IntentCompat
import androidx.lifecycle.LifecycleService
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import dvp.data.youtube.viewmodel.MainViewModel
import dvp.ui.youtube.mediaplayer.models.MediaData
import dvp.ui.youtube.mediaplayer.service.notification.Const
import dvp.ui.youtube.mediaplayer.service.notification.NotificationBuilder
import dvp.ui.youtube.mediaplayer.service.notification.NotificationListener
import dvp.ui.youtube.mediaplayer.service.notification.PlayerNotificationManager
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

@androidx.media3.common.util.UnstableApi
internal class OnlinePlayerService : LifecycleService() {
    private var session: MediaSession? = null
    private val player: ExoPlayer by inject()
    private val mainActivity : Class<Activity> = get(named("mainActivity"))
    private val binder = LocalBinder()
    private lateinit var notification: PlayerNotificationManager


    override fun onCreate() {
        super.onCreate()
        println("TEST: service on create")
        createSession()
        createNotificationChannel()
        val notification = Notification.Builder(this, Const.NOTIFICATION_CHANNEL_ID)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(Const.PLAYER_NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            Const.NOTIFICATION_CHANNEL_ID,
            Const.NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        NotificationManagerCompat.from(this).createNotificationChannel(channel)
    }

    private fun createSession(){
        session = MediaSession.Builder(this, player)
            .apply {
                val intent = Intent(this@OnlinePlayerService, mainActivity).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                val pendingIntent = PendingIntentCompat.getActivity(this@OnlinePlayerService, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT, false)
                setSessionActivity(pendingIntent)
            }
            .build()
    }

    @androidx.media3.common.util.UnstableApi
    private fun createNotification() {
        val mediaSession = session ?: return
        notification = NotificationBuilder(this, Const.PLAYER_NOTIFICATION_ID, Const.NOTIFICATION_CHANNEL_ID, mediaSession.sessionActivity)
            .setNotificationListener(object : NotificationListener {
                override fun onNotificationCancelled(notificationId: Int, dismissedByUser: Boolean) {
                    listener?.invoke()
                }
            })
//            .setChannelImportance(NotificationUtil.IMPORTANCE_HIGH)
            .build()
            .apply {
                this.setUseChronometer(true)
                this.setMediaSessionToken(mediaSession.sessionCompatToken)
                this.setPriority(NotificationCompat.PRIORITY_MAX)
                this.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

                this.setUseStopAction(true)
                this.setUsePlayPauseActions(true)

                this.setUseRewindAction(false)
                this.setUseFastForwardAction(false)

                this.setUsePreviousAction(true)
                this.setUseNextAction(true)
//                this.setForceNextAction(true)

                this.setUseNextActionInCompactView(true)
            }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("TEST: service on start command")
        val data = intent?.let { IntentCompat.getParcelableExtra(it,"data", MediaData::class.java) }!!
//        uTubeViewModel.submit(YoutubeEvent.GetRelative(data.videoId))

        createNotification()

        notification.setPlayer(player)
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onTaskRemoved(rootIntent: Intent?) {
        println("TEST: service task removed")
        super.onTaskRemoved(rootIntent)
        onDestroy()
    }

    override fun onDestroy() {
        println("TEST: service destroy")
        session?.run {
            release()
            player.seekTo(0)
            player.playWhenReady = false
            player.stop()
        }
        notification.setPlayer(null)

        ServiceCompat.stopForeground(this, ServiceCompat.STOP_FOREGROUND_REMOVE)
        stopSelf()
        isRunning = false
        super.onDestroy()
    }

    inner class LocalBinder : Binder() {
        fun getService(): OnlinePlayerService = this@OnlinePlayerService
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        return binder
    }


    companion object {
        private var isRunning = false
//        private var notificationListener: NotificationListener? = null

        private var listener: (() -> Unit)? = null


        fun start(context: Context, initData: MediaData, onStopByNotification: (() -> Unit)? = null) {
            println("TEST: service start | isRunning = $isRunning")
            if (isRunning) return
            val intent = Intent(context, OnlinePlayerService::class.java).also {
                it.putExtra("data", initData)
            }
            context.startForegroundService(intent)
            isRunning = true
            listener = onStopByNotification
        }

        fun stop(context: Context) {
            println("TEST: service stop | isRunning = $isRunning")
            if (!isRunning) return
            val intent = Intent(context, OnlinePlayerService::class.java)
            context.stopService(intent)
            isRunning = false
        }
    }
}
