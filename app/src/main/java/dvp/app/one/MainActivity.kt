package dvp.app.one

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.core.view.WindowCompat
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.ActivityIntegrationPoint
import com.bumble.appyx.core.integrationpoint.IntegrationPointProvider
import com.bumble.appyx.core.integrationpoint.NodeActivity
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import dvp.app.one.ui.theme.OneTheme
import dvp.lib.book.BookUI
import dvp.lib.browser.BrowserUI
import dvp.ui.youtube.YoutubeRouter
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.ext.android.getKoin
import java.util.*
import kotlin.math.absoluteValue

open class BaseActivity : ComponentActivity(), IntegrationPointProvider {
    override lateinit var appyxIntegrationPoint: ActivityIntegrationPoint
        protected set

    protected open fun createIntegrationPoint(savedInstanceState: Bundle?) =
        ActivityIntegrationPoint(
            activity = this,
            savedInstanceState = savedInstanceState
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appyxIntegrationPoint = createIntegrationPoint(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        appyxIntegrationPoint.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        appyxIntegrationPoint.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        appyxIntegrationPoint.onSaveInstanceState(outState)
    }
}

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            OneTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.Transparent,
                        darkIcons = true
                    )
                }
//                NodeHost(integrationPoint = appyxIntegrationPoint) {
//                    RootNode(it)
//                }
                YoutubeRouter(this)
//                App(context = this)
//                Web()


//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.Red)
//                ) {
//
//                    Box(
//                        modifier = Modifier
//                            .height(300.dp)
//                            .width(300.dp)
//                            .background(Color.Yellow)
//
//                    ){
//                        Box(
//                            modifier = Modifier
//                                .offset(x = 100.dp, y =  250.dp)
//                                .height(100.dp)
//                                .width(100.dp)
//                                .background(Color.Blue)
//                        )
//                    }
//                    Box(
//                        modifier = Modifier
//                            .height(200.dp)
//                            .width(200.dp)
//                            .background(Color.Green)
//                    )
//
//                }
            }
        }

//        val oneTapClient = Identity.getSignInClient(this)
//        oneTapClient.signOut()
//        setContent {
//            NonStopTheme {
//                val state = rememberOneTapSignInState()
//                Log.d("TEST", "STATE = ${state.state}")
//                OneTapSignInWithGoogle(
//                    state = state,
//                    clientId = "644667237511-tfjgf526riona6cnc2u43pboutv972q8.apps.googleusercontent.com",
//                    onTokenIdReceived = {
//                        Log.d("MainActivity", it)
////                        state.getAccessToken()
//                    },
//                    onDialogDismissed = {
//                        Log.d("MainActivity", it)
//                    }
//                )
//
//                GetAccessToken(
//                    state,
//                    "hisoka.dvp@gmail.com",
//                    arrayOf(
//                        "https://www.googleapis.com/auth/youtube",
//                        "https://www.googleapis.com/auth/youtube.readonly"
//                    ),
//                    onSuccess = {
//
//                    },
//                    onError = {
//
//                    }
//                )
//
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Button(onClick = { state.signIn() }, enabled = state.state == State.None) {
//                        Text(text = "Sign in")
//                    }
//                }
//            }
//        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun Web() {
    CookieManager.getInstance().removeAllCookies {

    }
    val state =
        rememberWebViewState("https://accounts.google.com/v3/signin/identifier?dsh=S654550160:1677074439920489&continue=https://www.youtube.com/signin?action_handle_signin=true&app=desktop&hl=en&next=%2F&ec=65620&hl=en&passive=true&service=youtube&uilel=3&flowName=GlifWebSignIn&flowEntry=ServiceLogin&ifkv=AWnogHdMkjMzXPIJk9ZStwK0nO6GxrSmuxwJputmlLcuGpEr0WgjDQn63cAi6I_m9c2k4np5AKbR7w")
    val client = MyWebClient()

    WebView(
        state,
        onCreated = {
            it.settings.javaScriptEnabled = true
        },
        client = client
    )
}

class MyWebClient : AccompanistWebViewClient() {
    private var isYoutubeLoaded = false

    @SuppressLint("NewApi")
    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {

//        val cookie = CookieManager.getInstance().getCookie("https://google.com")
//        println("TEST: cookie -> $cookie")
        val ytCookie = CookieManager.getInstance().getCookie("https://m.youtube.com")
        println("TEST: url -> ${request?.url}")
        println("TEST: header -> ${request?.requestHeaders}")
        println("TEST: header -> ${request?.requestHeaders}")
        println("TEST: ytCookie -> $ytCookie")
        println("TEST: ===============================================")

//        if (isYoutubeLoaded) {
//            return null
//        }
//        if (request?.url.toString().startsWith("https://accounts.google.com.vn/accounts/SetSID")  && !isYoutubeLoaded) {
//            isYoutubeLoaded = true
//            return handleRequest(request)
//        }

        return super.shouldInterceptRequest(view, request)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if (isYoutubeLoaded) {
            return true
        }
        return super.shouldOverrideUrlLoading(view, request)
    }

//    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//        println("shouldOverrideUrlLoading -> ${request?.url.toString()}")
//        val res = runBlocking(Dispatchers.IO) {
//            client.get(request?.url.toString()).bodyAsText()
//        }
//        println("shouldOverrideUrlLoading -> $res")
//
//        view?.loadData(res, "text/html", "utf-8")
//        println("shouldOverrideUrlLoading -> done")
//        return false
//    }

    private val httpClient = OkHttpClient().apply {
    }

    private fun handleRequest(req: WebResourceRequest?): WebResourceResponse? {
        println("TEST: handleRequest")
        return try {
            val headers = Headers.Builder()
            req?.requestHeaders?.forEach { headers.add(it.key, it.value) }
            val call = httpClient.newCall(
                Request.Builder()
                    .method(req!!.method, null)
                    .headers(headers.build())
                    .url(req.url.toString())
                    .build()
            )
            val response = call.execute()

            println("TEST: header -> ${response.headers.toMap()}")
//            val responseHeaders: MutableMap<String, String> = HashMap()
//            if (response.headers.size > 0) {
//                for (i in 0 until response.headers.size) {
//                    val key: String = response.headers.name(i)
//                    val value: String = response.headers.value(i)
//                    responseHeaders[key] = value
//                }
//            }
//            val body = response.body?.string()
            val ytCookie = CookieManager.getInstance().getCookie("https://m.youtube.com")
            println("TEST: ytCookie -> $ytCookie")
//            println("TEST: body -> $body")
            println("TEST: ========================================")
//            println("TEST: body -> ${body}")
//            val a = responseHeaders["content-type"]?.split("; ")
//            val mineType = a?.getOrNull(0) ?: "text/html"
//            val charset = a?.getOrNull(1)?.substringAfter("=") ?: "binary"

//            WebResourceResponse(
//                mineType,
//                charset,
//                response.body?.byteStream()
//            )
            return null
        } catch (e: java.lang.Exception) {
//            println("TEST: $url | error = $e")
            null
        }
    }

}

//class RootNode(
//    private val buildContext: BuildContext
//) : Node(
//    buildContext = buildContext
//) {
//    @Composable
//    override fun View(modifier: Modifier) {
//        App(buildContext)
//    }
//}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun App(context: Context) {
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
                    0 -> YoutubeRouter(context)
                }
            }
        }
    }
}
