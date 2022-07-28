package dvp.lib.ytube

import dvp.lib.ytube.utils.*
import kotlinx.serialization.json.*


internal object RelativeVideo {

    fun getRelatedVideo(response: JsonObject) = buildJsonArray {

        val rvsParams: JsonArray? = null
//
        try {
//            rvsParams = response.getNestedObj2("webWatchNextResponseExtensionData", "relatedVideoArgs").split(',').map(e => qs.parse(e));
        } catch (err: Exception) {
            // Do nothing.
        }
        try {
            val secondaryResults = response["contents"]["twoColumnWatchNextResults"]["secondaryResults"]["secondaryResults"]["results"]
            secondaryResults.forEach {
                val details = it["compactVideoRenderer"]
                val video = parseRelatedVideo(details?.jsonObject, rvsParams)
                video?.let { it1 -> add(it1) }
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
            return@buildJsonArray
        }
    }

    private fun getText(obj: JsonElement?): JsonPrimitive {
        return (obj["runs"][0]["text"] ?: obj["simpleText"]).toPrimitive()
    }

    private fun parseRelatedVideo(details: JsonObject?, rvsParams: JsonArray?): JsonObject? {
        if (details == null) return null

        try {
            val viewCount = getText(details["viewCountText"]).content.removeNonDigit()
            val rvsDetails = rvsParams?.find { it["id"] == details["videoId"] }

            val browseEndpoint = details["shortBylineText"]["runs"][0]["navigationEndpoint"]["browseEndpoint"]

            val channelId = browseEndpoint["browseId"]
            val name = getText(details["shortBylineText"])
            val user = browseEndpoint["canonicalBaseUrl"].text?.substringAfterLast("/")

            return buildJsonObject {
                put("id", details["videoId"] ?: JsonNull)
                put("title", getText(details["title"]))
                put("published", getText(details["publishedTimeText"]))

                put("author", buildJsonObject {
                    put("id", channelId ?: JsonNull)
                    put("name", name)
                    put("user", user.toPrimitive())
                    put("thumbnails", details["channelThumbnail"]["thumbnails"].map { it["url"] })
                    put("verified", isVerified(details["ownerBadges"]))
                })

                put("view_count", viewCount.toPrimitive())
                put("length_text", getText(details["lengthText"]))
                put("thumbnails", details["thumbnail"]["thumbnails"] ?: JsonNull)
                put("rich_thumbnails", details["richThumbnail"]["movingThumbnailRenderer"]["movingThumbnailDetails"]["thumbnails"] ?: JsonNull)
                put("isLive", details["badges"].findElement { it["metadataBadgeRenderer"]["label"].text == "LIVE NOW" }.toPrimitive())
            }
        } catch (err: Exception) {
            println("Error: parseRelatedVideo === $err")
            return null
        }
    }

    private fun isVerified(badges: JsonElement?): JsonPrimitive {
        return (badges.findElement {
            it["metadataBadgeRenderer"]["style"].text == "BADGE_STYLE_TYPE_VERIFIED"
        } != null).toPrimitive()
    }
}

