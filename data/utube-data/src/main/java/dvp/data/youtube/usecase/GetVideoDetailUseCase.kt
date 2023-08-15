package dvp.data.youtube.usecase

import dvp.data.youtube.models.VideoEntity
import dvp.data.youtube.public.UTubeRepo
import dvp.lib.core.domain.FlowUseCase
import dvp.lib.core.domain.SuspendUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetVideoDetailUseCase(
    private val repo: UTubeRepo
) : FlowUseCase<GetVideoDetailUseCase.Params, VideoEntity>(Dispatchers.IO) {

    data class Params(
        val video: VideoEntity,
        val loadRelated: Boolean = true,
        val clearPlaylist: Boolean = false,
    )

    override fun execute(parameters: Params): Flow<VideoEntity> {
        return flow {
            val detail = repo.getVideoDetails(parameters.video)
            emit(detail)

            if (!parameters.loadRelated) return@flow

            val related = repo.getRelatedVideos(detail)
            emit(related)
        }
    }
}