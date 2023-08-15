package dvp.lib.ytube.apis

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.cache.*
//import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*

class YoutubeApiClient(private val route: String) {
    private val baseUrl = "https://www.googleapis.com/youtube/v3/"
    private var token: String? = null
    private val client = HttpClient() {
        defaultRequest {
            url(baseUrl)
        }
        HttpResponseValidator {
            validateResponse { response ->
                println("YoutubeApiClient: validateResponse -> ${response.bodyAsText()}")
            }
        }
        install(HttpCache)
    }

    fun setToken(token: String) {
        this.token = token
    }

    suspend fun get(path: String? = "", params: StringValues): HttpResponse {
        return client.get(route + path) {
            this.url.parameters.appendAll(params)
            this.header("authorization", "Bearer $token")
        }
    }

    suspend fun post(path: String? = "", params: StringValues): HttpResponse {
        return client.post(route + path) {
            this.url.parameters.appendAll(params)
            this.header("authorization", "Bearer $token")
        }
    }
}