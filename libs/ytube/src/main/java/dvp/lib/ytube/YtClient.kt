package dvp.lib.ytube

import com.soywiz.krypto.sha1
import dvp.lib.ytube.dto.YoutubeVideoResponse
import dvp.lib.ytube.dto.init.InitResponse
import dvp.lib.ytube.dto.init.NextResponse
import dvp.lib.ytube.network.HttpClient
import dvp.lib.ytube.sample.res
import dvp.lib.ytube.utils.JsonUtils.findJson
import dvp.lib.ytube.utils.forEach
import dvp.lib.ytube.utils.get
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*

class YtClient {
    private val _json = Json {
        this.isLenient = true
        this.prettyPrint = false
        this.ignoreUnknownKeys = true
        this.allowStructuredMapKeys = true
    }

    var isTest = false

    fun setCookie(cookie: String): Boolean {
        val map = cookie.split(";").associate {
            it.trim().split("=").let { p -> p[0] to p[1] }
        }
        val sid = map["SAPISID"] ?: return false

        val apiId = map["__Secure-3PAPISID"]
        val psid = map["__Secure-3PSID"]
        val shortCookie = "SAPISID=${sid}; __Secure-3PAPISID=${apiId}; __Secure-3PSID=${psid};"
        fun generateSidAuth(sid: String): String {
            val timestamp = System.currentTimeMillis() / 1000
            val origin = "https://m.youtube.com"
            val sidBytes = "$timestamp $sid $origin".encodeToByteArray()
            val sidHash = sidBytes.sha1()
            val sidHashBase64 = sidHash.hexLower
            return "SAPISIDHASH ${timestamp}_${sidHashBase64}"
        }

        val headers = mapOf(
            "cookie" to shortCookie,
            "authorization" to generateSidAuth(sid),
            "origin" to "https://m.youtube.com"
        )
        HttpClient.setHeaders(headers)
        return true
    }


    suspend fun getHomePage(): InitResponse {
        val body = HttpClient.getSource()
        return try {
            val response = body.findJson(
                "watch.html",
                "response",
                Regex("\\bytInitialData(\"])?\\s*=\\s*\\{"),
                "</script>",
                "{"
            )
//            _json.decodeFromString(res)
            _json.decodeFromString(response)
        } catch (e: Exception) {
            println("error = $e")
            throw e
        }
    }

    suspend fun getNext(token: String): NextResponse {
        val data = """
            {
                "continuation": "$token",
                "context": {
                    "client": {
                        "hl": "en",
                        "clientName": "WEB",
                        "clientVersion": "2.20230217.01.00"
                    }
                }
            }
        """.trimIndent()

        val res =
            HttpClient.post("https://www.youtube.com/youtubei/v1/browse?prettyPrint=false", data)
        return _json.decodeFromString(res)
    }

    suspend fun getVideo(videoId: String): YoutubeVideoResponse {
        val data = """
            {
               "context":{
                  "client":{
                     "userAgent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.3 Safari/605.1.15,gzip(gfe)",
                    "clientName": "WEB",
                    "clientVersion": "2.20230331.00.00",
                    "osName": "Macintosh",
                    "osVersion": "10_15_7",
                     "hl":"en",
                     "timeZone":"UTC",
                     "utcOffsetMinutes":0,
                     "platform": "DESKTOP",
                    "clientFormFactor": "UNKNOWN_FORM_FACTOR",
                  }
               },
               "videoId":"$videoId",
               "playbackContext":{
                  "contentPlaybackContext":{
                     "html5Preference":"HTML5_PREF_WANTS"
                  }
               },
               "contentCheckOk":true,
               "racyCheckOk":true
            }
        """.trimIndent()

        val data2 = """
                         {
                            "videoId": "$videoId",
                            "context": {
                                "client": {
                                    "clientName": "ANDROID_TESTSUITE",
                                    "clientVersion": "1.9",
                                    "androidSdkVersion": 30,
                                    "hl": "en",
                                    "gl": "US",
                                    "utcOffsetMinutes": 0
                                }
                            }
                         }
        """.trimIndent()
        val agent = "User-Agent" to "com.google.android.youtube/17.36.4 (Linux; U; Android 12; GB) gzip"

        val res =
            HttpClient.post("https://www.youtube.com/youtubei/v1/player?prettyPrint=false", data2, agent)
        return _json.decodeFromString(res)
    }

//    suspend fun getBasicInfo(videoId: String): JsonObject {
//        return getWatchHTMLPage(videoId)
//    }

//    private suspend fun getWatchHTMLPage(videoId: String): JsonObject {
//        val body = HttpClient.getHtmlWatchPage(videoId)
//
//        return buildJsonObject {
//            put("page", "watch")
//            put(
//                "cver",
//                body.substringAfter("{\"key\":\"cver\",\"value\":\"").substringBefore("\"}")
//            )
//
//            var playerResponse: JsonElement? = null
//            try {
//                playerResponse = body.findJson(
//                    "watch.html",
//                    "player_response",
//                    Regex("\\bytInitialPlayerResponse\\s*=\\s*\\{"),
//                    "</script>",
//                    "{"
//                )
//                put("player_response", playerResponse)
//            } catch (e: Exception) {
////              val args = Regex("\\bytplayer\\.config\\s*=\\s*\\{(.*)</script>").find(body)?.groupValues?.get(1)?.let { return@let "{$it" }
//                println("getWatchHTMLPage -> error: $e")
//            }
//
//            val response = body.findJson(
//                "watch.html",
//                "response",
//                Regex("\\bytInitialData(\"])?\\s*=\\s*\\{"),
//                "</script>",
//                "{"
//            ).jsonObject
//            put("response", element = response)
//
//            val formats = parseFormats(playerResponse as JsonObject)
//            put(
//                key = "formats",
//                element = if (!isTest) {
//                    val playerUrl = getHTML5PlayerUrl(body)
//                    put("html5player", playerUrl)
//
//                    val html5Player = HttpClient.getSource(playerUrl)
//                    Sig.decipherFormats(formats, html5Player)
//                } else {
//                    formats
//                }
//            )
//
//            put("relative_video", getRelatedVideo(response))
//        }
//    }

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