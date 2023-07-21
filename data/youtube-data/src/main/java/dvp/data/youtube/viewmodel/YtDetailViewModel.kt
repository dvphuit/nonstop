package dvp.data.youtube.viewmodel

import dvp.data.youtube.domain.GetVideoDetailUseCase
import dvp.lib.core.viewmodel.MviViewModel


class YtDetailViewModel(
    private val getVideo: GetVideoDetailUseCase
) : MviViewModel<YoutubeState, YoutubeEvent>() {


    override fun submit(event: YoutubeEvent) {
        when (event) {
            is YoutubeEvent.GetVideoDetail -> getVideoDetail(event.videoId)
            else -> {}
        }
    }

    private fun getVideoDetail(videoId: String) = safeLaunch {
        setLoading()
        val params = GetVideoDetailUseCase.Params(videoId)
        val data = getVideo.invoke(params)
//        setState(BaseViewState.Data(YoutubeState(openingVideo = data)))
    }
}