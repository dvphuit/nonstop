package dvp.data.youtube

import dvp.data.youtube.internal.LocalRepo
import dvp.data.youtube.internal.RemoteRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val youtubeModule = module {
    viewModel { YtViewModel(get(), get()) }
    singleOf(::LocalRepo)
    singleOf(::RemoteRepo)
}
