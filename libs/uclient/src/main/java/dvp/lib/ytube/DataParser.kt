package dvp.lib.ytube

import dvp.lib.ytube.dto.init.CompactVideoDto
import dvp.lib.ytube.dto.init.ContinuationDto
import dvp.lib.ytube.dto.init.VideoDataDto
import dvp.lib.ytube.dto.init.VideoDto
import dvp.lib.ytube.utils.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.decodeFromJsonElement


object DataParser {
    private val serializer = Json {
        ignoreUnknownKeys = true
    }

    fun parserInitData(data: String): Pair<List<VideoDto>, ContinuationDto?> {
        val array =
            serializer.parseToJsonElement(data)["contents"]["twoColumnBrowseResultsRenderer"]["tabs"][0]["tabRenderer"]["content"]["richGridRenderer"]["contents"]?.jsonArray

        val videos = mutableListOf<VideoDto>()
        var continuation: ContinuationDto? = null

        array?.forEach {
            parserVideoItem(it)?.let(videos::add)
                ?: parseContinueToken(it).also { ctoken ->
                    continuation = ctoken
                }
        }

        return videos to continuation
    }

    fun parseContinueData(data: String): Pair<List<VideoDto>, ContinuationDto?> {
        val array = serializer.parseToJsonElement(data)["onResponseReceivedActions"][0]["appendContinuationItemsAction"]["continuationItems"]?.jsonArray

        val videos = mutableListOf<VideoDto>()
        var continuation: ContinuationDto? = null

        array?.forEach {
            parserVideoItem(it)?.let(videos::add)
                ?: parseContinueToken(it).also { ctoken ->
                    continuation = ctoken
                }
        }

        return videos to continuation
    }

    fun parseRelativeData(data: String): Pair<List<CompactVideoDto>, ContinuationDto?> {
        val json =
            serializer.parseToJsonElement(data)["contents"]["twoColumnWatchNextResults"]["secondaryResults"]["secondaryResults"]["results"][1]["itemSectionRenderer"]["contents"]

        val videos = mutableListOf<CompactVideoDto>()
        var continuation: ContinuationDto? = null
        json?.jsonArray?.forEach {
            it["compactVideoRenderer"]?.let { e ->
                serializer.decodeFromJsonElement(CompactVideoDto.serializer(), e).let(videos::add)
            } ?: parseContinueToken(it).also { ctoken ->
                continuation = ctoken
            }
        }

        return videos to continuation
    }

    fun parseContinueRelativeData(data: String): Pair<List<CompactVideoDto>, ContinuationDto?> {
        val array = serializer.parseToJsonElement(data)["onResponseReceivedEndpoints"][0]["appendContinuationItemsAction"]["continuationItems"]?.jsonArray
        val videos = mutableListOf<CompactVideoDto>()
        var continuation: ContinuationDto? = null
        array?.jsonArray?.forEach {
            it["compactVideoRenderer"]?.let { e ->
                serializer.decodeFromJsonElement(CompactVideoDto.serializer(), e).let(videos::add)
            } ?: parseContinueToken(it).also { ctoken ->
                continuation = ctoken
            }
        }

        return videos to continuation
    }

    fun parseVideoDetail(data: String) : VideoDataDto {
        return serializer.decodeFromString(VideoDataDto.serializer(), data)
    }

    private fun parseContinueToken(data: JsonElement): ContinuationDto? {
        return data["continuationItemRenderer"]?.let { ctoken ->
            serializer.decodeFromJsonElement<ContinuationDto>(ctoken)
        }
    }

    private fun parserVideoItem(data: JsonElement): VideoDto? {
        return data["richItemRenderer"]["content"]["videoRenderer"]?.let { richItem ->
            serializer.decodeFromJsonElement<VideoDto>(richItem)
        }
    }
}