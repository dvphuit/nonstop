package dvp.data.youtube.extension

import dvp.data.youtube.models.*
import dvp.lib.ytube.dto.Format
import dvp.lib.ytube.dto.YoutubeVideoResponse
import dvp.lib.ytube.dto.init.InitResponse

fun InitResponse.VideoRenderer.toVideo(): VideoEntity {
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

fun InitResponse.Thumbnail.toThumbnail(): ThumbnailEntity {
    return ThumbnailEntity(
        url = this.url.orEmpty(),
        width = this.width?.toInt() ?: 0,
        height = this.height?.toInt() ?: 0,
    )
}

fun VideoEntity.fromVideoDetailDto(dto: YoutubeVideoResponse): VideoEntity {
    val videos = mutableListOf<VideoFormat>()
    val audios = mutableListOf<AudioFormat>()
    dto.streamingData?.adaptiveFormats?.forEach {
        if (it.mimeType?.startsWith("video") == true) {
            videos.add(it.toVideoFormat())
        }
        if (it.mimeType?.startsWith("audio") == true) {
            audios.add(it.toAudioFormat())
        }
    }

    return this.copy(
        streamingData = StreamingData(
            expireIn = dto.streamingData?.expiresInSeconds ?: 0L,
            videoFormats = videos,
            audioFormats = audios
        )
    )
}

internal fun Format.toVideoFormat(): VideoFormat {
    return VideoFormat(
        itag = this.itag.orZero(),
        url = this.url.orEmpty(),
        mimeType = this.mimeType.orEmpty(),
        bitrate = this.bitrate.orZero(),
        width = this.width.orZero(),
        height = this.height.orZero(),
        qualityLabel = this.qualityLabel.orEmpty(),
        contentLength = this.contentLength.orZero(),
        highReplication = this.highReplication.orFalse()
    )
}

internal fun Format.toAudioFormat(): AudioFormat {
    return AudioFormat(
        itag = this.itag.orZero(),
        url = this.url.orEmpty(),
        mimeType = this.mimeType.orEmpty(),
        bitrate = this.bitrate.orZero(),
        contentLength = this.contentLength.orZero(),
        highReplication = this.highReplication.orFalse(),
        audioQuality = this.audioQuality.orEmpty(),
        audioSampleRate = this.audioSampleRate.orEmpty(),
        audioChannels = this.audioChannels.orZero(),
        loudnessDb = this.loudnessDb.orZero()
    )
}