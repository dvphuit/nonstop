//package dvp.ui.youtube.mediaplayer.service
//
//import android.util.Log
//import dvp.data.youtube.models.VideoEntity
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//object PlayingQueue {
//    private val queue = mutableListOf<VideoEntity>()
//    private var currentStream: VideoEntity? = null
//    private val scope = CoroutineScope(Dispatchers.IO)
//
//    /**
//     * Listener that gets called when the user selects an item from the queue
//     */
//    private var onQueueTapListener: (VideoEntity) -> Unit = {}
//
//    var repeatQueue: Boolean = false
//
//    fun clear() = queue.clear()
//
//    fun add(vararg streamItem: VideoEntity) {
//        for (stream in streamItem) {
//            if (currentStream?.streamingData?.getAudio()?.url == stream.streamingData?.getAudio()?.url || stream.title.isBlank()) continue
//            // remove if already present
//            queue.remove(stream)
//            queue.add(stream)
//        }
//    }
//
//    fun addAsNext(streamItem: VideoEntity) {
//        if (currentStream == streamItem) return
//        if (queue.contains(streamItem)) queue.remove(streamItem)
//        queue.add(
//            currentIndex() + 1,
//            streamItem
//        )
//    }
//
//    // return the next item, or if repeating enabled, the first one of the queue
//    fun getNext(): VideoEntity? = queue.getOrNull(currentIndex() + 1)
//        ?: queue.firstOrNull()?.takeIf { repeatQueue }
//
//    // return the previous item, or if repeating enabled, the last one of the queue
//    fun getPrev(): VideoEntity? = queue.getOrNull(currentIndex() - 1)
//        ?: queue.lastOrNull()?.takeIf { repeatQueue }
//
//    fun hasPrev() = getPrev() != null
//
//    fun hasNext() = getNext() != null
//
//    fun updateCurrent(streamItem: VideoEntity) {
//        currentStream = streamItem
//        if (!contains(streamItem)) queue.add(0, streamItem)
//    }
//
//    fun isNotEmpty() = queue.isNotEmpty()
//
//    fun isEmpty() = queue.isEmpty()
//
//    fun size() = queue.size
//
//    fun currentIndex(): Int = queue.indexOfFirst { it.id == currentStream?.id }.takeIf { it >= 0 } ?: 0
//
//    fun getCurrent(): VideoEntity? = currentStream
//
//    fun contains(streamItem: VideoEntity) = queue.any { it.id == streamItem.id }
//
//    // only returns a copy of the queue, no write access
//    fun getStreams() = queue.toList()
//
//    fun setStreams(streams: List<VideoEntity>) {
//        queue.clear()
//        queue.addAll(streams)
//    }
//
//    fun remove(index: Int) = queue.removeAt(index)
//
////    fun move(from: Int, to: Int) = queue.move(from, to)
//
//    private fun fetchMoreFromPlaylist(playlistId: String, nextPage: String?) {
//        var playlistNextPage: String? = nextPage
////        scope.launch {
////            while (playlistNextPage != null) {
////                RetrofitInstance.authApi.getPlaylistNextPage(
////                    playlistId,
////                    playlistNextPage!!
////                ).apply {
////                    add(
////                        *this.relatedStreams.toTypedArray()
////                    )
////                    playlistNextPage = this.nextpage
////                }
////            }
////        }
//    }
//
//    fun insertPlaylist(playlistId: String, newCurrentStream: VideoEntity) {
//        scope.launch {
//            try {
//                val playlist = PlaylistsHelper.getPlaylist(playlistId)
//                add(*playlist.relatedStreams.toTypedArray())
//                updateCurrent(newCurrentStream)
//                if (playlist.nextpage == null) return@launch
//                fetchMoreFromPlaylist(playlistId, playlist.nextpage)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    private fun fetchMoreFromChannel(channelId: String, nextPage: String?) {
//        var channelNextPage: String? = nextPage
//        scope.launch {
//            while (channelNextPage != null) {
//                RetrofitInstance.api.getChannelNextPage(channelId, nextPage!!).apply {
//                    add(*relatedStreams.toTypedArray())
//                    channelNextPage = this.nextpage
//                }
//            }
//        }
//    }
//
//    fun insertChannel(channelId: String, newCurrentStream: VideoEntity) {
//        scope.launch {
//            runCatching {
//                val channel = RetrofitInstance.api.getChannel(channelId)
//                add(*channel.relatedStreams.toTypedArray())
//                updateCurrent(newCurrentStream)
//                if (channel.nextpage == null) return@launch
//                fetchMoreFromChannel(channelId, channel.nextpage)
//            }
//        }
//    }
//
//    fun insertByVideoId(videoId: String) {
//        scope.launch {
//            runCatching {
//                val streams = RetrofitInstance.api.getStreams(videoId)
//                add(streams.toStreamItem(videoId))
//            }
//        }
//    }
//
//    fun onQueueItemSelected(index: Int) {
//        try {
//            val streamItem = queue[index]
//            updateCurrent(streamItem)
//            onQueueTapListener.invoke(streamItem)
//        } catch (e: Exception) {
//            Log.e("Queue on tap", "lifecycle already ended")
//        }
//    }
//
//    fun setOnQueueTapListener(listener: (VideoEntity) -> Unit) {
//        onQueueTapListener = listener
//    }
//
//    fun resetToDefaults() {
//        repeatQueue = false
//        onQueueTapListener = {}
//    }
//}
