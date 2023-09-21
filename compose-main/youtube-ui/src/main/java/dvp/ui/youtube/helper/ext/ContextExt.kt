package dvp.ui.youtube.helper.ext

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import coil.request.ImageRequest

tailrec fun Context.findActivity(): Activity? {
    return this as? Activity
        ?: (this as? ContextWrapper)?.baseContext?.findActivity()
}

fun Context.loadImage(url: String) =
    ImageRequest.Builder(this).data(url).crossfade(true).build()