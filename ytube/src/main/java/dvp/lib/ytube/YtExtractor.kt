package dvp.lib.ytube

import dvp.lib.ytube.network.HttpClient
import dvp.lib.ytube.utils.JsonUtils.findJson
import kotlinx.serialization.json.*

class YtExtractor {

    var isTest = false

    suspend fun getBasicInfo(videoId: String): Map<String, Any?> {
        return getWatchHTMLPage(videoId)
    }

    private suspend fun getWatchHTMLPage(videoId: String): Map<String, Any?> {
        val body = HttpClient.getHtmlWatchPage(videoId)
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
        val html5Player = HttpClient.getString(Const.YOUTUBE_URL + json["html5player"])

        json["formats"] = if (!isTest) {
            Sig.decipherFormats(formats, html5Player)
        } else {
            formats
        }

        val relatives = getRelativedVideo(json["response"] as JsonObject)
        return json
    }

    private fun getRelativedVideo(response: JsonObject): ArrayList<JsonObject?> {
        val videos = arrayListOf<JsonObject?>()
        val rvsParams: JsonArray? = null
//
        try {
//            rvsParams = response.getNestedObj2("webWatchNextResponseExtensionData", "relatedVideoArgs").split(',').map(e => qs.parse(e));
        } catch (err: Exception) {
            // Do nothing.
        }
        try {
            val secondaryResults = response.getNestedObj(
                "contents",
                "twoColumnWatchNextResults",
                "secondaryResults",
                "secondaryResults",
                "results"
            ) as JsonArray
            secondaryResults.forEach {
                val details = (it as JsonObject)["compactVideoRenderer"] as? JsonObject
                val video = parseRelatedVideo(details, rvsParams)
                videos.add(video)
//            } else {
//                let autoplay = result.compactAutoplayRenderer || result.itemSectionRenderer;
//                if (!autoplay || !Array.isArray(autoplay.contents)) continue;
//                for (let content of autoplay.contents) {
//                    let video = parseRelatedVideo(content.compactVideoRenderer, rvsParams);
//                    if (video) videos.push(video);
//                }
//            }
            }
        } catch (err: Exception) {
            return arrayListOf()
        }

        return videos
    }

    fun getText(obj: JsonObject?): String? {
        if (obj != null) {
            val runs = obj["runs"]
            return (if (runs != null && runs is JsonArray) {
                (runs[0] as JsonObject)["text"]
            } else {
                obj["simpleText"]
            })?.jsonPrimitive?.content
        }
        return null
    }

    fun parseRelatedVideo(details: JsonObject?, rvsParams: JsonArray?): JsonObject? {
        if (details == null) return null

        try {
            val viewCount = getText(details["viewCountText"] as JsonObject)
            val shortViewCount = getText(details["shortViewCountText"] as JsonObject)
            val rvsDetails = rvsParams?.find { it.jsonPrimitive.jsonObject["id"] == details["videoId"] }?.jsonObject

            val browseEndpoint = (details.getNestedObj("shortBylineText", "runs") as JsonArray)[0].jsonObject.getNestedObj("navigationEndpoint", "browseEndpoint")?.jsonObject
            val channelId = browseEndpoint?.get("browseId")
            val name = getText(details["shortBylineText"]?.jsonObject)
            val user = browseEndpoint?.get("canonicalBaseUrl")?.jsonPrimitive?.content?.substringAfterLast("/")
            val video = JsonObject(buildMap {
                "id" to details["videoId"]
                "title" to JsonPrimitive(getText(details["title"] as JsonObject))
                "published" to JsonPrimitive(getText(details["publishedTimeText"] as JsonObject))
                "author" to JsonObject(buildMap {
                    "id" to channelId
                    "name" to name
                    "user" to user
                    "thumbnails" to (details.getNestedObj(
                        "channelThumbnail",
                        "thumbnails"
                    ) as JsonArray).map { it.jsonObject["url"] }
                    "verified" to isVerified(details["ownerBadges"] as? JsonArray)
                })
                "short_view_count_text" to JsonPrimitive(shortViewCount?.split(" ")?.first())
                "view_count" to JsonPrimitive(viewCount?.replace(",", ""))
                "length_text" to JsonPrimitive(getText(details["lengthText"] as JsonObject))
                "thumbnails" to details.getNestedObj("thumbnail", "thumbnails")
                val richThumbnails = details["richThumbnail"]
                "rich_thumbnails" to if (richThumbnails is JsonObject) {
                    richThumbnails.getNestedObj(
                        "movingThumbnailRenderer",
                        "movingThumbnailDetails",
                        "thumbnails"
                    ) as JsonArray
                } else {
                    JsonArray(emptyList())
                }

                "isLive" to details["badges"]?.jsonPrimitive?.jsonArray?.find {
                    (it as JsonObject).getNestedObj(
                        "metadataBadgeRenderer",
                        "label"
                    )?.jsonPrimitive?.content == "LIVE NOW"
                }
            })

            return video
        } catch (err: Exception) {
            println("Error: parseRelatedVideo === $err")
            return null
        }
    }

    fun isVerified(badges: JsonArray?): Boolean {
        if (badges == null) return false
        return badges.find {
            (it as JsonObject).getNestedObj(
                "metadataBadgeRenderer",
                "style"
            )?.jsonPrimitive?.content == "BADGE_STYLE_TYPE_VERIFIED"
        } != null
    }

    private fun getHTML5player(body: String): String? {
        val html5playerRes =
            Regex("<script\\s+src=\"([^\"]+)\"(?:\\s+type=\"text/javascript\")?\\s+name=\"player_ias/base\"\\s*>|\"jsUrl\":\"([^\"]+)\"")
                .find(body)

        return (html5playerRes?.groups?.get(1) ?: html5playerRes?.groups?.get(2))?.value
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

private fun JsonObject.getNestedObj(vararg keys: String): JsonElement? {
    if (keys.isEmpty()) return this

    val ret = this[keys[0]]
    if (ret is JsonObject) {
        return ret.getNestedObj(*keys.drop(1).toTypedArray())
    }
    return ret
}
