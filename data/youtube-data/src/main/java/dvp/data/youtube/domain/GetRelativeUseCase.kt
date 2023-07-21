package dvp.data.youtube.domain

import dvp.data.youtube.internal.RemoteRepo
import dvp.data.youtube.models.VideoEntity
import dvp.lib.core.domain.SuspendUseCase
import dvp.lib.core.domain.UseCase
import dvp.lib.ytube.dto.YoutubeVideoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRelativeUseCase(private val remote: RemoteRepo) :
    SuspendUseCase<GetRelativeUseCase.Params, YoutubeVideoResponse>(Dispatchers.IO) {

    data class Params(
        val videoId: String,
    )

    override suspend fun execute(parameters: Params): YoutubeVideoResponse {
        return remote.getRelative(parameters.videoId)
    }

}
