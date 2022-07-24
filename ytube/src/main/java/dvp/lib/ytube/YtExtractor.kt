package dvp.lib.ytube

import dvp.lib.ytube.utils.JsonUtils.findJson
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

class YtExtractor {

    private val client = HttpClient(Android){
        install(UserAgent) {
            agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.101 Safari/537.36"
        }
    }
    private val url = "https://www.youtube.com/watch?v=xsPcMO9CRBE"

    private suspend fun getHtml(): String {
        return client.get(url)
    }

    suspend fun getWatchHTMLPage(): Map<String, Any?> {
        val body = getHtml()
        val json = mutableMapOf<String, Any?>()
        json["page"] = "watch"
        json["cver"] = body.substringAfter("{\"key\":\"cver\",\"value\":\"").substringBefore("\"}")
        try {
            json["player_response"] = body.findJson(
                "watch.html",
                "player_response",
                Regex("\\bytInitialPlayerResponse\\s*=\\s*\\{"),
                "</script>",
                "{"
            )
        } catch (e: Exception) {
//            val args = Regex("\\bytplayer\\.config\\s*=\\s*\\{(.*)</script>").find(body)?.groupValues?.get(1)?.let { return@let "{$it" }
            println("getWatchHTMLPage -> error: $e")
        }
        json["response"] = body.findJson(
            "watch.html",
            "response",
            Regex("\\bytInitialData(\"])?\\s*=\\s*\\{"),
            "</script>",
            "{"
        )
        json["html5player"] = getHTML5player(body)

        val formats = parseFormats(json["player_response"] as JsonObject)
        val html5Player = client.get<String>("https://www.youtube.com${json["html5player"]}")

        json["formats"] = Sig.decipherFormats(formats, html5Player)
        return json
    }

    private fun getHTML5player(body: String): String? {
        val html5playerRes =
            Regex("<script\\s+src=\"([^\"]+)\"(?:\\s+type=\"text/javascript\")?\\s+name=\"player_ias/base\"\\s*>|\"jsUrl\":\"([^\"]+)\"")
                .find(body)

        return if (html5playerRes != null)
            return (html5playerRes.groups[1] ?: html5playerRes.groups[2])?.value
        else null
    }

    private fun parseFormats(playerResponse: JsonObject): ArrayList<JsonObject> {
        val formats = arrayListOf<JsonObject>()
        (playerResponse["streamingData"] as? JsonObject)?.let { data ->
            (data["formats"] as? JsonArray)?.forEach { formats.add(it as JsonObject) }
            (data["adaptiveFormats"] as? JsonArray)?.forEach { formats.add(it as JsonObject) }
        }
        return formats
    }
}
