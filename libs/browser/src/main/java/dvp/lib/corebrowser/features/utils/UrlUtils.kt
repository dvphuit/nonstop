package dvp.lib.corebrowser.features.utils

import android.net.Uri
import androidx.core.net.toUri

object UrlUtils {
    fun normalizeUrl(string: String): String {
        val trimmedInput = string.trim()
        var uri = Uri.parse(trimmedInput)
        if (uri.scheme.isNullOrEmpty()) {
            uri = Uri.parse("https://$trimmedInput")
        }
        return uri.toString()
    }

    fun getDisplayUrl(url: String): String {
        return normalizeUrl(url).toUri().host.toString()
    }

    fun isValidUrl(potentialUrl: String): Boolean {
        return runCatching { potentialUrl.toUri() }.getOrNull()?.host.isNullOrEmpty().not()
    }
}
