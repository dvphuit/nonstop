package dvp.data.youtube.domain

import androidx.paging.*
import dvp.data.youtube.internal.RemoteRepo
import dvp.data.youtube.models.VideoEntity
import dvp.lib.core.domain.UseCase
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class LoadVideoListUseCase(private val remote: RemoteRepo) :
    UseCase<LoadVideoListUseCase.Params, Flow<PagingData<VideoEntity>>>() {

    override fun execute(parameters: Params?): Flow<PagingData<VideoEntity>> {
        return Pager(
            config = parameters?.pagingConfig ?: PagingConfig(pageSize = 20),
            pagingSourceFactory = { VideoPagingSource(remote, parameters?.options ?: emptyMap()) }
        ).flow
    }

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    )

}

class VideoPagingSource(
    private val repo: RemoteRepo,
    val options: Map<String, String>
) : PagingSource<Int, VideoEntity>() {
    override fun getRefreshKey(state: PagingState<Int, VideoEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoEntity> {
        val page = params.key ?: 1
        return try {
            val response = repo.fetchVideos()
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