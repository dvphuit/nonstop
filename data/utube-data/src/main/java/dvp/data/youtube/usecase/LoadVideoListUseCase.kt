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

class LoadVideoListUseCase(private val repo: UTubeRepo) :
    UseCase<LoadVideoListUseCase.Params, Flow<PagingData<VideoEntity>>>() {

    override fun execute(parameters: Params?): Flow<PagingData<VideoEntity>> {
        return Pager(
            config = parameters?.pagingConfig ?: PagingConfig(pageSize = 20),
            pagingSourceFactory = { VideoPagingSource(repo, parameters?.options ?: emptyMap()) }
        ).flow
    }

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    )

}

class VideoPagingSource(
    private val repo: UTubeRepo,
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
            val response = repo.getVideos()
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