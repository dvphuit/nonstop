package dvp.lib.ytube.network

import dvp.lib.ytube.Const
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

object HttpClient {
    private val client = HttpClient(CIO) {
        install(UserAgent) {
            agent =
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.101 Safari/537.36"
        }
    }

    private suspend fun getString(url: String): String {
        return client.get(url).bodyAsText()
    }

    suspend fun getYtString(url: String?): String {
        return getString(Const.YOUTUBE_URL + url)
    }

    suspend fun getHtmlWatchPage(videoId: String) = getString(Const.YOUTUBE_WATCH_URL + videoId)
}