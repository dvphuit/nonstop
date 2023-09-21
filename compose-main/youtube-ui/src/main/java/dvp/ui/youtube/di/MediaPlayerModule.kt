package dvp.ui.youtube.di

import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.PendingIntentCompat
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.session.MediaSession
import dvp.ui.youtube.mediaplayer.MediaViewModel
import dvp.ui.youtube.mediaplayer.VideoPlayerCacheManager
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.lang.ref.WeakReference


@UnstableApi
val mediaPlayerModule = module {

    single {
        AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
            .setUsage(C.USAGE_MEDIA)
            .build()
    }

    single<DataSource.Factory> {
        DefaultHttpDataSource.Factory()
    }

    single {
        ExoPlayer.Builder(androidContext())
//            .setTrackSelector(DefaultTrackSelector(androidContext()))
//            .setAudioAttributes(
//                 AudioAttributes.Builder()
//                    .setUsage(C.USAGE_MEDIA)
//                    .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
//                    .build(),
//                 true
//            )
//            .setBandwidthMeter(DefaultBandwidthMeter.Builder(androidContext()).build())
//            .setRenderersFactory(
//                DefaultRenderersFactory(androidContext())
//                    .setEnableAudioTrackPlaybackParams(true)
//                    .setEnableAudioOffload(true)
//                    .forceEnableMediaCodecAsynchronousQueueing()
//                    .setExtensionRendererMode(DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER)
//                    .setEnableDecoderFallback(true)
//            )
////            .setMediaSourceFactory(
////                DemuxedMediaSourceFactory(
////                    mediaSourceFactory = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory()),
////                    repository = innerTube
////                )
////            )
//            .setUseLazyPreparation(true)
//            .setLoadControl(
//                DefaultLoadControl.Builder()
//                    .setBufferDurationsMs(
//                        /* minBufferMs = */ 2000,
//                        /* maxBufferMs = */ 5000,
//                        /* bufferForPlaybackMs = */ 1500,
//                        /* bufferForPlaybackAfterRebufferMs = */ 2000
//                    )
//                    .setPrioritizeTimeOverSizeThresholds(true)
//                    .setTargetBufferBytes(C.LENGTH_UNSET)
//                    .setBackBuffer(
//               10000,
//                        false
//                    )
//                    .setAllocator(DefaultAllocator(true, 16))
//                    .build()
//            )
//            .setWakeMode(C.WAKE_MODE_NETWORK)
//            .setHandleAudioBecomingNoisy(true)
//            .setSeekBackIncrementMs(15000)
//            .setSeekForwardIncrementMs(15000)
//            .setPauseAtEndOfMediaItems(false)
//            .setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)
            .apply {
                val cache = VideoPlayerCacheManager.getCache()
                if (cache != null) {
                    val cacheDataSourceFactory = CacheDataSource.Factory()
                        .setCache(cache)
                        .setUpstreamDataSourceFactory(DefaultDataSource.Factory(get(), get()))
                    setMediaSourceFactory(DefaultMediaSourceFactory(cacheDataSourceFactory))
                }
            }
            .build()
    }

//    single {
//        MediaSession.Builder(androidContext(), get<ExoPlayer>())
//            .apply {
//                val intent = Intent(androidContext(), get(named("mainActivity"))).apply {
//                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                }
//                val pendingIntent = PendingIntentCompat.getActivity(androidContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE, false)
//                setSessionActivity(pendingIntent)
//            }
//            .build()
//    }

    viewModel { MediaViewModel(get()) }
}
