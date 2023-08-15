package dvp.data.youtube.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dvp.data.youtube.models.VideoEntity
import dvp.data.youtube.public.UTubeRepo
import dvp.lib.core.domain.UseCase
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class GetRelativeUseCase(private val repo: UTubeRepo) :
    UseCase<GetRelativeUseCase.Params, Flow<PagingData<VideoEntity>>>()  {

    data class Params(
        val videoId: String,
    )

    override fun execute(parameters: Params?): Flow<PagingData<VideoEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { VideoPagingSource(parameters!!) }
        ).flow
    }


    inner class VideoPagingSource(private val parameters: Params) : PagingSource<Int, VideoEntity>() {
        override fun getRefreshKey(state: PagingState<Int, VideoEntity>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoEntity> {
            val page = params.key ?: 1
            return try {
                val response = repo.getRelative(parameters.videoId)
                LoadResult.Page(
                    data = response,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (response.isEmpty()) null else page + 1
                )
            } catch (exception: IOException) {
                return LoadResult.Error(exception)
            }
        }
    }
}
