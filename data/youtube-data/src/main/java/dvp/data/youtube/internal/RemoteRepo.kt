package dvp.data.youtube.internal

import dvp.lib.ytube.YtExtractor


class RemoteRepo {
    private val extractor by lazy { YtExtractor() }

    suspend fun getVideoInfo(id: String) = extractor.getBasicInfo(id)
}