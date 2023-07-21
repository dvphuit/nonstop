package dvp.lib.ytube.network

import dvp.lib.ytube.Const
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

object HttpClient {
    private val client = HttpClient(CIO) {
        this.HttpResponseValidator {
            this.validateResponse {
                println("response headers -> ${it.headers}")
            }
        }
    }

    private var headers = mapOf<String, String>()

    fun setHeaders(headers: Map<String, String>) {
        this.headers = headers
    }

    private suspend fun getString(url: String): String {
        return client.get(url){
            this@HttpClient.headers.forEach {
                this.headers.append(it.key, it.value)
            }
        }.bodyAsText()
    }

    suspend fun post(url: String, data: String, agent: Pair<String, String> = "" to ""): String {
        return client.post(url){
            this@HttpClient.headers.forEach {
                this.headers.append(it.key, it.value)
                this.headers.append(agent.first, agent.second)
            }
            contentType(ContentType.Application.Json)
            setBody(data)
        }.bodyAsText()
    }

    suspend fun getSource(url: String? = ""): String {
        return getString(Const.YOUTUBE_URL + url)
    }

    suspend fun getHtmlWatchPage(videoId: String) = getString(Const.YOUTUBE_WATCH_URL + videoId)
}