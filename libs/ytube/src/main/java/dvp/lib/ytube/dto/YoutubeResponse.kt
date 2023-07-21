// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json                 = Json { allowStructuredMapKeys = true }
// val videoListResponseDto = json.parse(VideoListResponseDto.serializer(), jsonString)

package dvp.lib.ytube.dto

import kotlinx.serialization.*

@Serializable
data class YoutubeResponse(
    val kind: String? = null,
    val etag: String? = null,
    val items: List<Item>? = null,
    val nextPageToken: String? = null,
    val pageInfo: PageInfo? = null
)
