package dvp.data.youtube

import androidx.lifecycle.SavedStateHandle
import dvp.data.youtube.public.UTubeRepo
import dvp.data.youtube.internal.LocalRepo
import dvp.data.youtube.internal.RemoteRepo
import dvp.data.youtube.internal.UTubeRepoImpl
import dvp.data.youtube.usecase.AuthenticatedUseCase
import dvp.data.youtube.usecase.GetRelativeUseCase
import dvp.data.youtube.usecase.GetVideoDetailUseCase
import dvp.data.youtube.usecase.LoadVideoListUseCase
import dvp.data.youtube.viewmodel.DetailViewModel
import dvp.data.youtube.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val youtubeModule = module {
    viewModel {
        MainViewModel(
            auth = AuthenticatedUseCase(get()),
            loadVideos = LoadVideoListUseCase(get()),
            getVideo = GetVideoDetailUseCase(get()),
        )
    }

    viewModel {
        DetailViewModel(
            getRelative = GetRelativeUseCase(get()),
        )
    }

    singleOf(::LocalRepo)
    singleOf(::RemoteRepo)
    single<UTubeRepo> { UTubeRepoImpl(get(), get()) }
    single {
        SavedStateHandle()
    }
}
