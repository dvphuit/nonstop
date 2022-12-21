package dvp.lib.ytube.apis

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*

abstract class BaseApi {
    private val baseUrl = "https://www.googleapis.com/youtube/v3/"
    abstract val root: String
    private var token: String? = null
    private val client = HttpClient(CIO) {
        defaultRequest {
            url(baseUrl)
        }
//        install(UserAgent) {
////            agent =
////                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.101 Safari/537.36"
//        }

    }

    fun setToken(token: String) {
        this.token = token
    }

    suspend fun get(path: String, params: StringValues): HttpResponse {
        return client.get(path) {

            this.url.parameters.appendAll(params)
            this.header("authorization", "Bearer $token")
        }
    }

    suspend fun post(path: String, params: StringValues): HttpResponse {
        return client.post(path) {
            this.url.parameters.appendAll(params)
            this.header("authorization", "Bearer $token")
        }
    }

    val String.byRoot: String
        get() = "$root/$this"
}