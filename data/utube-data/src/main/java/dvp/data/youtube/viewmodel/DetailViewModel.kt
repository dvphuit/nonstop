package dvp.data.youtube.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dvp.data.youtube.usecase.GetRelativeUseCase
import dvp.lib.core.viewmodel.MviViewModel


class DetailViewModel(
    private val getRelative: GetRelativeUseCase,
) : MviViewModel<DetailState, DetailEvent>() {

    init {
        println("TEST: DetailViewModel init")
//        submit(DetailEvent.Init(videoId))
    }

    override fun submit(event: DetailEvent) {
        println("TEST: DetailViewModel event = $event")
        when (event) {
            is DetailEvent.Init -> initData(event.videoId)
        }
    }

    private fun initData(videoId: String) = safeLaunch {
        setLoading()

        val related = getRelative
            .invoke(GetRelativeUseCase.Params(videoId))
            .cachedIn(scope = viewModelScope)

        setData {
            this?.copy(related = related) ?: DetailState(related = related)
        }
    }
}