package dvp.ui.youtube.mediaplayer.service.notification

import android.app.PendingIntent
import android.graphics.Bitmap
import androidx.media3.common.Player

internal interface MediaDescriptionAdapter {
    fun getCurrentContentTitle(player: Player?): CharSequence?
    fun createCurrentContentIntent(player: Player?): PendingIntent?
    fun getCurrentContentText(player: Player?): CharSequence?
    fun getCurrentSubText(player: Player?): CharSequence? = null
    fun getCurrentLargeIcon(player: Player?, callback: PlayerNotificationManager.BitmapCallback?): Bitmap?
}