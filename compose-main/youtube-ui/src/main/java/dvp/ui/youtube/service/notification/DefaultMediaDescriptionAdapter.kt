package dvp.ui.youtube.service.notification

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.text.TextUtils
import androidx.media3.common.Player
import coil.request.CachePolicy
import coil.request.ImageRequest


internal class DefaultMediaDescriptionAdapter(private val context: Context, private val pendingIntent: PendingIntent?) :
    MediaDescriptionAdapter {

    override fun getCurrentContentTitle(player: Player?): CharSequence? {
        player ?: return null

        if (!player.isCommandAvailable(Player.COMMAND_GET_MEDIA_ITEMS_METADATA)) {
            return ""
        }
        val displayTitle = player.mediaMetadata.displayTitle
        if (!displayTitle.isNullOrEmpty()) {
            return displayTitle
        }
        val title = player.mediaMetadata.title
        return title ?: ""
    }

    override fun createCurrentContentIntent(player: Player?): PendingIntent? {
        player ?: return null

        return pendingIntent
    }

    override fun getCurrentContentText(player: Player?): CharSequence? {
        player ?: return null

        if (!player.isCommandAvailable(Player.COMMAND_GET_MEDIA_ITEMS_METADATA)) {
            return null
        }
        val artist = player.mediaMetadata.artist
        return if (!TextUtils.isEmpty(artist)) {
            artist
        } else player.mediaMetadata.albumArtist
    }

    @androidx.media3.common.util.UnstableApi
    override fun getCurrentLargeIcon(player: Player?, callback: PlayerNotificationManager.BitmapCallback?): Bitmap? {
        player ?: return null

        if (!player.isCommandAvailable(Player.COMMAND_GET_MEDIA_ITEMS_METADATA)) {
            return null
        }

        player.mediaMetadata.run {
            artworkUri?.run {
                ImageRequest.Builder(context)
                    .data(player.mediaMetadata.artworkUri)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .target { result ->
                        val bitmap = (result as BitmapDrawable).bitmap
                        callback?.onBitmap(bitmap)
                    }
                    .build()
            }
                ?: artworkData?.run {
                    return BitmapFactory.decodeByteArray(this, 0, this.size)
                }
        }

        return null

    }
}
