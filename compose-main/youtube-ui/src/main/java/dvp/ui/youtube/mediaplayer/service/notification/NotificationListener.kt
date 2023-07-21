package dvp.ui.youtube.mediaplayer.service.notification

import android.app.Notification

interface NotificationListener {
    fun onNotificationCancelled(notificationId: Int, dismissedByUser: Boolean) {}
    fun onNotificationPosted(notificationId: Int, notification: Notification?, ongoing: Boolean) {}
}