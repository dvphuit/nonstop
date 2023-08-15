package dvp.lib.ytube.apis

import io.ktor.client.statement.*
import io.ktor.util.*

class ChannelApi {
    private val client = YoutubeApiClient("channels")

    fun setToken(token: String) {
        client.setToken(token)
    }

    /*
    * parts:
    *   auditDetails
        brandingSettings
        contentDetails
        contentOwnerDetails
        id
        localizations
        snippet
        statistics
        status
        topicDetails
    * */
    suspend fun list(
        ids: List<String>,
        parts: List<String> = listOf("snippet", "statistics", "topicDetails")
    ): String {
        val params = valuesOf(
            "id" to ids,
            "part" to parts,
            "maxResults" to listOf("${ids.size}")
        )
        return client.get(params = params).bodyAsText()
    }
}