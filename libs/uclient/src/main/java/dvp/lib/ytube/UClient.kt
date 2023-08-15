package dvp.lib.ytube

import com.soywiz.krypto.sha1
import dvp.lib.ytube.apis.IUTubeApi
import dvp.lib.ytube.dto.YoutubeVideoResponse
import dvp.lib.ytube.dto.init.CompactVideoDto
import dvp.lib.ytube.dto.init.ContinuationDto
import dvp.lib.ytube.dto.init.InitResponse
import dvp.lib.ytube.dto.init.NextResponse
import dvp.lib.ytube.dto.init.VideoDataDto
import dvp.lib.ytube.dto.init.VideoDto
import dvp.lib.ytube.network.HttpClient
import dvp.lib.ytube.utils.JsonUtils.findJson
import dvp.lib.ytube.utils.forEach
import dvp.lib.ytube.utils.get
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*

class UClient : IUTubeApi {
    private val _json = Json {
        this.isLenient = true
        this.prettyPrint = false
        this.ignoreUnknownKeys = true
        this.allowStructuredMapKeys = true
    }

    var isTest = false

    override fun setCookie(cookie: String): Boolean {
        val map = cookie.split(";").associate {
            it.trim().split("=").let { p -> p[0] to p[1] }
        }
        val sid = map["SAPISID"] ?: return false

        val apiId = map["__Secure-3PAPISID"]
        val psid = map["__Secure-3PSID"]
        val shortCookie = "SAPISID=${sid}; __Secure-3PAPISID=${apiId}; __Secure-3PSID=${psid};"
        fun generateSidAuth(sid: String): String {
            val timestamp = System.currentTimeMillis() / 1000
            val origin = "https://youtube.com"
            val sidBytes = "$timestamp $sid $origin".encodeToByteArray()
            val sidHash = sidBytes.sha1()
            val sidHashBase64 = sidHash.hexLower
            return "SAPISIDHASH ${timestamp}_${sidHashBase64}"
        }

        val headers = mapOf(
            "cookie" to shortCookie,
            "authorization" to generateSidAuth(sid),
            "origin" to "https://youtube.com"
        )

        HttpClient.setHeaders(headers)
        return true
    }

    override suspend fun getInitVideos(): Pair<List<VideoDto>, ContinuationDto?> {
        val body = HttpClient.getSource()
        val response = body.findJson(
            "watch.html",
            "response",
            Regex("\\bytInitialData(\"])?\\s*=\\s*\\{"),
            "</script>",
            "{"
        )
        return DataParser.parserInitData(response)
    }

    override suspend fun getNextVideos(token: String): Pair<List<VideoDto>, ContinuationDto?> {
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

        val res = HttpClient.post("https://www.youtube.com/youtubei/v1/browse?prettyPrint=false", data)
        return DataParser.parseContinueData(res)
    }

    override suspend fun getVideoDetail(videoId: String): VideoDataDto {
        val payload = """
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
            HttpClient.post("https://www.youtube.com/youtubei/v1/player?prettyPrint=false", payload, agent)
        return DataParser.parseVideoDetail(res)
    }

    override suspend fun getRelativeVideos(videoId: String): Pair<List<CompactVideoDto>, ContinuationDto?> {
        val data = """
            {
                "videoId": "$videoId",
                "context": {
                    "client": {
                        "hl": "en",
                        "clientName": "WEB",
                        "clientVersion": "2.20230718.08.01"
                    }
                }
            }
        """.trimIndent()

        val res = HttpClient.post("https://www.youtube.com/youtubei/v1/next?prettyPrint=false", data)
        return DataParser.parseRelativeData(res)
    }

    override suspend fun getContinueRelativeVideos(token: String): Pair<List<CompactVideoDto>, ContinuationDto?> {
        val data = """
            {
                "continuation": "$token",
                "context": {
                    "client": {
                        "hl": "en",
                        "clientName": "WEB",
                        "clientVersion": "2.20230718.08.01"
                    }
                }
            }
        """.trimIndent()

        val res = HttpClient.post("https://www.youtube.com/youtubei/v1/next?prettyPrint=false", data)
        return DataParser.parseContinueRelativeData(res)
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

        val res = HttpClient.post("https://www.youtube.com/youtubei/v1/browse?prettyPrint=false", data)
        return _json.decodeFromString(res)
    }

    suspend fun getVideo(videoId: String): YoutubeVideoResponse {
        val payload = """
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
            HttpClient.post("https://www.youtube.com/youtubei/v1/player?prettyPrint=false", payload, agent)
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