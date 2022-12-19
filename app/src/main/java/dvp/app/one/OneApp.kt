package dvp.app.one

import android.app.Application
import dvp.data.youtube.youtubeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class OneApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@OneApp)
            modules(youtubeModule)
        }
    }
}