package dvp.data.youtube

import dvp.data.youtube.public.YoutubeRepo
import dvp.data.youtube.internal.LocalRepo
import dvp.data.youtube.internal.RemoteRepo
import dvp.data.youtube.internal.YtRepoImpl
import dvp.data.youtube.domain.AuthenticatedUseCase
import dvp.data.youtube.domain.GetRelativeUseCase
import dvp.data.youtube.domain.GetVideoDetailUseCase
import dvp.data.youtube.domain.LoadVideoListUseCase
import dvp.data.youtube.viewmodel.YtDetailViewModel
import dvp.data.youtube.viewmodel.UTubeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val youtubeModule = module {
    viewModel {
        UTubeViewModel(
            auth = AuthenticatedUseCase(get()),
            loadVideos = LoadVideoListUseCase(get()),
            getVideo = GetVideoDetailUseCase(get()),
            getRelative = GetRelativeUseCase(get())
        )
    }
    viewModel { YtDetailViewModel(GetVideoDetailUseCase(get())) }

    singleOf(::LocalRepo)
    singleOf(::RemoteRepo)
    single<YoutubeRepo> { YtRepoImpl(get(), get()) }
}
