package dvp.app.one

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import androidx.core.view.WindowCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.gms.auth.api.identity.Identity
import dvp.app.one.oauth.GetAccessToken
import dvp.app.one.oauth.OneTapSignInWithGoogle
import dvp.app.one.oauth.State
import dvp.app.one.oauth.rememberOneTapSignInState
import dvp.app.one.ui.theme.NonStopTheme
import dvp.lib.book.BookUI
import dvp.lib.browser.BrowserUI
import dvp.lib.youtube.YoutubeUI
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)

//        setContent {
//            NonStopTheme {
//                val systemUiController = rememberSystemUiController()
//                SideEffect {
//                    systemUiController.setStatusBarColor(
//                        color = Color.Transparent,
//                        darkIcons = true
//                    )
//                }
//
//                App()
//            }
//        }

        val oneTapClient = Identity.getSignInClient(this)
        oneTapClient.signOut()
        setContent {
            NonStopTheme {
                val state = rememberOneTapSignInState()
                Log.d("TEST", "STATE = ${state.state}")
                OneTapSignInWithGoogle(
                    state = state,
                    clientId = "644667237511-tfjgf526riona6cnc2u43pboutv972q8.apps.googleusercontent.com",
                    onTokenIdReceived = {
                        Log.d("MainActivity", it)
//                        state.getAccessToken()
                    },
                    onDialogDismissed = {
                        Log.d("MainActivity", it)
                    }
                )

                GetAccessToken(
                    state,
                    "hisoka.dvp@gmail.com",
                    arrayOf(
                        "https://www.googleapis.com/auth/youtube",
                        "https://www.googleapis.com/auth/youtube.readonly"
                    ),
                    onSuccess = {

                    },
                    onError = {

                    }
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = { state.signIn() }, enabled = state.state == State.None) {
                        Text(text = "Sign in")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun App() {
    Surface(
        modifier = Modifier,
        color = Color.Cyan,
    ) {
        HorizontalPager(count = 3) { page ->
            Card(
                Modifier.graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
            ) {

                when (page) {
                    1 -> BookUI()
                    2 -> BrowserUI()
                    0 -> YoutubeUI()
                }
            }
        }
    }
}
