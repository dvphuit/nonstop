package dvp.data.youtube.extension

import dvp.data.youtube.models.*
import dvp.lib.ytube.dto.Format
import dvp.lib.ytube.dto.Thumbnail
import dvp.lib.ytube.dto.YoutubeVideoResponse
import dvp.lib.ytube.dto.init.AdaptiveFormatDto
import dvp.lib.ytube.dto.init.CompactVideoDto
import dvp.lib.ytube.dto.init.FormatDto
import dvp.lib.ytube.dto.init.InitResponse
import dvp.lib.ytube.dto.init.ListThumbnail
import dvp.lib.ytube.dto.init.RunTexts
import dvp.lib.ytube.dto.init.StreamingDataDto
import dvp.lib.ytube.dto.init.Text
import dvp.lib.ytube.dto.init.VideoDataDto
import dvp.lib.ytube.dto.init.VideoDto

fun VideoDto.toVideo(): VideoEntity {
    return VideoEntity(
        id = this.videoId.orEmpty(),
        title = this.title?.runs?.firstOrNull()?.text.orEmpty(),
        description = this.descriptionSnippet?.runs?.firstOrNull()?.text.orEmpty(),
        published = this.publishedTimeText?.simpleText.orEmpty(),
        duration = this.lengthText?.simpleText.orEmpty(),
        viewCount = this.viewCountText?.simpleText.orEmpty(),
        ownerBadges = this.ownerBadges?.firstOrNull()?.metadataBadgeRenderer?.icon?.iconType.orEmpty(),
        thumbnails = this.thumbnail?.thumbnails?.map { it.toThumbnail() }
            ?: listOf(),
        channel = ChannelEntity.fromDto(
            this.ownerText,
            this.channelThumbnailSupportedRenderers?.channelThumbnailWithLinkRenderer?.thumbnail?.thumbnails?.firstOrNull()
        )
    )
}

fun CompactVideoDto.toVideo(): VideoEntity {
    this.shortBylineText?.runs?.get(0)?.let {
        ChannelEntity(
            id = it.navigationEndpoint?.browseEndpoint?.browseId.orEmpty(),
            name = it.text.orEmpty(),
            thumbnail = this.channelThumbnail?.toThumbnails()?.firstOrNull()
        )
    }
    return VideoEntity(
        id = this.videoId.orEmpty(),
        title = this.title?.simpleText.orEmpty(),
        description = "",
        thumbnails = this.thumbnail?.toThumbnails() ?: emptyList(),
        published = "",
        duration = this.lengthText.map(),
        viewCount = this.viewCountText.map(),
        ownerBadges = this.ownerBadges?.get(0)?.metadataBadgeRenderer?.style.orEmpty(),
        channel =
        ChannelEntity.fromDto(
            this.shortBylineText,
            this.channelThumbnail?.thumbnails?.last()
        )
    )
}

private fun Text?.map(): String {
    return this?.simpleText.orEmpty()
}

fun VideoDataDto.toVideo(origin: VideoEntity): VideoEntity {
    return origin.copy(
        streamingData = this.streamingData?.toStreamingData(),
        description = this.videoDetails?.shortDescription.orEmpty()
    )
}

private fun StreamingDataDto.toStreamingData(): StreamingData {
    val videos = mutableListOf<VideoFormat>()
    val audios = mutableListOf<AudioFormat>()
    this.adaptiveFormats?.forEach {
        when {
            it.mimeType?.startsWith("video").isTrue() -> videos.add(it.toVideoFormat())
            it.mimeType?.startsWith("audio").isTrue() -> audios.add(it.toAudioFormat())
        }
    }
    return StreamingData(
        expireIn = this.expiresInSeconds.longOrZero(),
        videoFormats = videos,
        audioFormats = audios
    )
}

private fun AdaptiveFormatDto.toVideoFormat(): VideoFormat {
    return VideoFormat(
        itag = this.itag.orZero(),
        url = this.url.orEmpty(),
        mimeType = this.mimeType.orEmpty(),
        bitrate = this.bitrate.orZero(),
        width = this.width.orZero(),
        height = this.height.orZero(),
        qualityLabel = this.qualityLabel.orEmpty(),
        contentLength = this.contentLength.longOrZero(),
        highReplication = this.highReplication.orFalse()
    )
}

private fun AdaptiveFormatDto.toAudioFormat(): AudioFormat {
    return AudioFormat(
        itag = this.itag.orZero(),
        url = this.url.orEmpty(),
        mimeType = this.mimeType.orEmpty(),
        bitrate = this.bitrate.orZero(),
        contentLength = this.contentLength.longOrZero(),
        highReplication = this.highReplication.orFalse(),
        audioQuality = this.audioQuality.orEmpty(),
        audioSampleRate = this.audioSampleRate.orEmpty(),
        audioChannels = this.audioChannels.orZero(),
//        loudnessDb = this.loudnessDb.orZero()
    )
}

private fun ListThumbnail.toThumbnails(): List<ThumbnailEntity> {
    return this.thumbnails?.map { it.toThumbnail() } ?: emptyList()
}

internal fun Thumbnail.toThumbnail(): ThumbnailEntity {
    return ThumbnailEntity(
        url = this.url.orEmpty(),
        width = this.width?.toInt() ?: 0,
        height = this.height?.toInt() ?: 0,
    )
}