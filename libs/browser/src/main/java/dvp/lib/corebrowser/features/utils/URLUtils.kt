package dvp.lib.corebrowser.features.utils

import android.net.Uri
import androidx.core.net.toUri

class URLUtilsImpl : URLUtils {
    override fun normalizeUrl(string: String): String {
        val trimmedInput = string.trim()
        var uri = Uri.parse(trimmedInput)
        if (uri.scheme.isNullOrEmpty()) {
            uri = Uri.parse("https://$trimmedInput")
        }
        return uri.toString()
    }

    override fun getDisplayUrl(url: String): String {
        return normalizeUrl(url).toUri().host.toString()
    }

    override fun isValidUrl(potentialUrl: String): Boolean {
        return runCatching { potentialUrl.toUri() }.getOrNull()?.host.isNullOrEmpty().not()
    }
}

interface URLUtils {
    fun normalizeUrl(string: String): String
    fun getDisplayUrl(url: String): String
    fun isValidUrl(potentialUrl: String): Boolean
}
