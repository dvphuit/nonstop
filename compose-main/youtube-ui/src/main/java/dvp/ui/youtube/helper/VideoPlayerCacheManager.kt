package dvp.ui.youtube.helper

import android.annotation.SuppressLint
import android.content.Context
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.cache.Cache
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import java.io.File

object VideoPlayerCacheManager {

    private lateinit var cacheInstance: Cache

    @SuppressLint("UnsafeOptInUsageError")
    fun initialize(context: Context, maxCacheBytes: Long) {
        if (VideoPlayerCacheManager::cacheInstance.isInitialized) {
            return
        }

        cacheInstance = SimpleCache(
            File(context.cacheDir, "video"),
            LeastRecentlyUsedCacheEvictor(maxCacheBytes),
            StandaloneDatabaseProvider(context),
        )
    }

    internal fun getCache(): Cache? =
        if (VideoPlayerCacheManager::cacheInstance.isInitialized) {
            cacheInstance
        } else {
            null
        }
}