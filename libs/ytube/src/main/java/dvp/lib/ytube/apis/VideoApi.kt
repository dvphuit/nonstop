package dvp.lib.ytube.apis

import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

class VideoApi {

    private val client = YoutubeApiClient("videos")

    fun setToken(token: String){
        client.setToken(token)
    }

    suspend fun list(): HttpResponse {
        val params = valuesOf(
            Pair("part", listOf("snippet", "contentDetails", "statistics")),
            Pair("chart", listOf("mostPopular")),
            Pair("maxResults", listOf("10")),
            Pair("regionCode", listOf("VN")),
        )
        return client.get(params = params)
    }

    //like, dislike, none
    suspend fun rating(value: String): Boolean {
        val params = valuesOf(
            Pair("rating", listOf(value)),
        )
        return client.post("rating", params).status == HttpStatusCode.NoContent
    }

    suspend fun getRating(videoId: String): String {
        val params = valuesOf("id", listOf(videoId))
        return client.get("getRating", params).bodyAsText()
    }
}