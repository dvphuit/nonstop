package dvp.data.youtube

import androidx.lifecycle.ViewModel
import dvp.data.youtube.internal.LocalRepo
import dvp.data.youtube.internal.RemoteRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YtViewModel(private val local: LocalRepo, private val remote: RemoteRepo) : ViewModel() {
    fun getVideoInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            val info = remote.getVideoInfo("aqz-KE-bpKQ")
            println(info)
        }
    }
}