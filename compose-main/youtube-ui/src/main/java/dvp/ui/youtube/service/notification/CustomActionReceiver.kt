package dvp.ui.youtube.service.notification

import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.media3.common.Player

interface CustomActionReceiver {
    fun createCustomActions(context: Context?, instanceId: Int): Map<String, NotificationCompat.Action>
    fun getCustomActions(player: Player?): List<String>
    fun onCustomAction(player: Player?, action: String, intent: Intent?)
}