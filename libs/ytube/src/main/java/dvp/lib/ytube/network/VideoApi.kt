package dvp.lib.ytube.network

import dvp.lib.ytube.apis.BaseApi
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

class VideoApi : BaseApi() {
    override val root: String
        get() = "videos"

    suspend fun list(): HttpResponse {
        setToken("")
        val params = valuesOf(
            Pair("part", listOf("snippet", "contentDetails", "statistics")),
            Pair("chart", listOf("mostPopular")),
            Pair("maxResults", listOf("10")),
            Pair("regionCode", listOf("VN")),
        )
        return get(root, params)
    }

    //like, dislike, none
    suspend fun rating(value: String): Boolean {
        setToken("")
        val params = valuesOf(
            Pair("rating", listOf(value)),
        )
        return post("rating".byRoot, params).status == HttpStatusCode.NoContent
    }

    suspend fun getRating(videoId: String): String {
        setToken("")
        val params = valuesOf("id", listOf(videoId))
        return get("getRating".byRoot, params).bodyAsText()
    }
}