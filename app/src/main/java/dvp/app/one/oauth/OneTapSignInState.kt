package dvp.app.one.oauth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class State {
    None, SignIn, SignUp, GetAccessToken
}

class OAuthState {
    var state by mutableStateOf(State.None)
        private set

    fun signIn() {
        state = State.SignIn
    }

    fun getAccessToken(){
        state = State.GetAccessToken
    }

    internal fun close() {
        state = State.None
    }
}