package dvp.ui.youtube.mediaplayer.service.notification//package dvp.ui.youtube.mediaplayer.service.notification
//
//import android.Manifest
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.widget.RemoteViews
//import androidx.core.app.ActivityCompat
//import androidx.core.app.NotificationManagerCompat
//import dvp.ui.youtube.R
//
//class NotificationHelper(private val context: Context) {
//
//    companion object {
//        private const val CHANNEL_ID = "test channel"
//    }
//
//    private val notificationManager = NotificationManagerCompat.from(context)
//    private  var remoteViews: RemoteViews
//
//    init {
//        createNotificationChannel()
//        remoteViews = createNoti(context)
//    }
//
//    fun sendNotification() {
//        val notification = androidx.core.app.NotificationCompat.Builder(context, CHANNEL_ID)
//            .apply {
//                setContent(remoteViews)
//                setSmallIcon(R.drawable.ic_microphone)
//                setContentIntent(
//                    PendingIntent.getActivity(
//                        context,
//                        0,
//                        Intent(context, Class.forName("dvp.app.one.MainActivity"))
//                            .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
//                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//                    )
//                )
//                setVisibility(androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC)
//                setAutoCancel(false)
//                setOngoing(true)
//            }
//            .build()
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//
//            return
//        }
//        notificationManager.notify(1, notification)
////        notificationManagerCompat.notify(1, notification)
////        context.startForeground(1, notification)
//    }
//
//    private fun createNoti(context: Context): RemoteViews {
//        val mRemoteViews = RemoteViews(context.packageName, R.layout.media_controller_notification)
//        mRemoteViews.apply {
//            setImageViewResource(
//                R.id.musicIcon_imageView,
//                R.drawable.ic_launcher_lockscreen
//            )
//            setTextViewText(R.id.musicName_textView, "三国杀-汪苏泷")
//            setTextViewText(R.id.playTime_textView, "1:30")
//            setTextViewText(R.id.musicTime_textView, "3:25")
//        }
//
//        val intent = Intent(context, Class.forName("dvp.app.one.MainActivity"))
//        intent.action = "ACTION_NOTIFICATION"
//        intent.putExtra("button", "prev")
//        var pendingIntent = PendingIntent.getForegroundService(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
//
//        mRemoteViews.setOnClickPendingIntent(R.id.imageButton2, pendingIntent)
//
//        intent.putExtra("button", "play")
//        pendingIntent = PendingIntent.getForegroundService(context, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
//        mRemoteViews.setOnClickPendingIntent(R.id.imageButton3, pendingIntent)
//
//        intent.putExtra("button", "next")
//        pendingIntent = PendingIntent.getForegroundService(context, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
//        mRemoteViews.setOnClickPendingIntent(R.id.imageButton4, pendingIntent)
//
//        intent.putExtra("button", "close")
//        pendingIntent = PendingIntent.getForegroundService(context, 4, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
//        mRemoteViews.setOnClickPendingIntent(R.id.imageButton5, pendingIntent)
//
//        return mRemoteViews
//    }
//
//    private fun createNotificationChannel() {
//        val channelName = "音乐播放"
//        val importance = NotificationManager.IMPORTANCE_DEFAULT
//        val notificationChannel = NotificationChannel(CHANNEL_ID, channelName, importance)
//        notificationManager.createNotificationChannel(notificationChannel)
//    }
//}