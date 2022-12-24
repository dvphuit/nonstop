package dvp.lib.ytube.apis

import dvp.lib.ytube.apis.auth.models.TokenInfo
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
//import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

import kotlinx.coroutines.*
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

    val authorizationUrlQuery = Parameters.build {
        append("client_id", System.getenv("GOOGLE_CLIENT_ID"))
        append("scope", "https://www.googleapis.com/auth/userinfo.profile")
        append("response_type", "code")
        append("redirect_uri", "http://127.0.0.1:8080")
        append("access_type", "offline")
    }.formUrlEncode()
//    println("https://accounts.google.com/o/oauth2/auth?$authorizationUrlQuery")
//    println("Open a link above, get the authorization code, insert it below, and press Enter.")
    val authorizationCode = readln()

    // Step 2: Create a storage for tokens
    val bearerTokenStorage = mutableListOf<BearerTokens>()

//    val client2 = HttpClient(CIO) {
//        install(ContentNegotiation) {
//            json()
//        }
//
//        install(Auth) {
//            bearer {
//                loadTokens {
//                    bearerTokenStorage.last()
//                }
//                refreshTokens {
//                    val refreshTokenInfo: TokenInfo = client.submitForm(
//                        url = "https://accounts.google.com/o/oauth2/token",
//                        formParameters = Parameters.build {
//                            append("grant_type", "refresh_token")
//                            append("client_id", System.getenv("GOOGLE_CLIENT_ID"))
//                            append("refresh_token", oldTokens?.refreshToken ?: "")
//                        }
//                    ) { markAsRefreshTokenRequest() }.body()
//                    bearerTokenStorage.add(BearerTokens(refreshTokenInfo.accessToken, oldTokens?.refreshToken!!))
//                    bearerTokenStorage.last()
//                }
//                sendWithoutRequest { request ->
//                    request.url.host == "www.googleapis.com"
//                }
//            }
//        }
//    }

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