package dvp.app.one

import dvp.data.youtube.youtubeModule
import dvp.lib.core.app.CoreApplication
import dvp.ui.youtube.di.mediaPlayerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.definition.Definition
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

class OneApp : CoreApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@OneApp)
            val mainActivity = module {
                this.single(named("mainActivity")) {
                   MainActivity::class.java
                }
            }

            modules(mainActivity, youtubeModule, mediaPlayerModule)
        }
    }
}