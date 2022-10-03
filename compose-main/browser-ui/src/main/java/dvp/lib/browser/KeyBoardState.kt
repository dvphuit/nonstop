package dvp.lib.browser

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity

class KeyBoardState {
    var height by mutableStateOf(0)
        private set
    var maxHeight by mutableStateOf(1000)
        private set

    private var lastState = State.Closed
    private var lastHeight = 0

    @SuppressLint("ComposableNaming")
    @JvmName("setHeight1")
    @Composable
    fun listenState(listener: (State) -> Unit) {
        this.height = WindowInsets.ime.getBottom(LocalDensity.current)
        if (height > maxHeight) {
            maxHeight = height
        }

        val mState = when {
            height == 0 -> State.Closed
            height == maxHeight -> State.Opened
            height > lastHeight -> State.Opening
            height < lastHeight -> State.Closing
            else -> State.Closed
        }
        lastHeight = height
        if (lastState != mState) {
            listener.invoke(mState)
            lastState = mState
        }

    }

    enum class State {
        Closed,
        Closing,
        Opened,
        Opening,
    }
}
