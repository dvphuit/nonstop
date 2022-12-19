package dvp.lib.ytube

import dvp.lib.ytube.RelativeVideo.getRelatedVideo
import dvp.lib.ytube.network.HttpClient
import dvp.lib.ytube.utils.JsonUtils.findJson
import dvp.lib.ytube.utils.JsonUtils.getNestedObj
import dvp.lib.ytube.utils.forEach
import dvp.lib.ytube.utils.get
import kotlinx.serialization.json.*

class YtExtractor {

    var isTest = false

    suspend fun getBasicInfo(videoId: String): JsonObject {
        return getWatchHTMLPage(videoId)
    }

    private suspend fun getWatchHTMLPage(videoId: String): JsonObject {
        val body = HttpClient.getHtmlWatchPage(videoId)

        return buildJsonObject {
            put("page", "watch")
            put(
                "cver",
                body.substringAfter("{\"key\":\"cver\",\"value\":\"").substringBefore("\"}")
            )

            var playerResponse: JsonElement? = null
            try {
                playerResponse = body.findJson(
                    "watch.html",
                    "player_response",
                    Regex("\\bytInitialPlayerResponse\\s*=\\s*\\{"),
                    "</script>",
                    "{"
                )
                put("player_response", playerResponse)
            } catch (e: Exception) {
//              val args = Regex("\\bytplayer\\.config\\s*=\\s*\\{(.*)</script>").find(body)?.groupValues?.get(1)?.let { return@let "{$it" }
                println("getWatchHTMLPage -> error: $e")
            }

            val response = body.findJson(
                "watch.html",
                "response",
                Regex("\\bytInitialData(\"])?\\s*=\\s*\\{"),
                "</script>",
                "{"
            ).jsonObject
            put("response", element = response)

            val formats = parseFormats(playerResponse as JsonObject)
            put(
                key = "formats",
                element = if (!isTest) {
                    val playerUrl = getHTML5PlayerUrl(body)
                    put("html5player", playerUrl)

                    val html5Player = HttpClient.getYtString(playerUrl)
                    Sig.decipherFormats(formats, html5Player)
                } else {
                    formats
                }
            )

            put("relative_video", getRelatedVideo(response))
        }
    }

    private fun getHTML5PlayerUrl(body: String): String? {
        val html5playerRes =
            Regex("<script\\s+src=\"([^\"]+)\"(?:\\s+type=\"text/javascript\")?\\s+name=\"player_ias/base\"\\s*>|\"jsUrl\":\"([^\"]+)\"")
                .find(body)

        return (html5playerRes?.groups?.get(1) ?: html5playerRes?.groups?.get(2))?.value
    }

    private fun parseFormats(playerResponse: JsonObject) = buildJsonArray {
        playerResponse["streamingData"].let { data ->
            data["formats"].forEach(::add)
            data["adaptiveFormats"].forEach(::add)
        }
    }
}