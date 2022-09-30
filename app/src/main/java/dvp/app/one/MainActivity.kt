package dvp.app.one

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import dvp.app.one.ui.theme.NonStopTheme
import dvp.lib.book.BookUI
import dvp.lib.browser.ComposeBrowser
import dvp.lib.browser.Controller
import dvp.lib.youtube.YoutubeUI
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SetJavaScriptEnabled")
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webview = WebView(this).apply {
            this.settings.apply {
                this.javaScriptEnabled = true
                this.allowContentAccess = true
            }
            this.webChromeClient = object : WebChromeClient(){
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    println("TEST: loading = $newProgress")
                }

                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    println("TEST: title = $title")
                }
            }

            this.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    println("TEST: page loaded $url")
                }
            }
        }
        webview.loadUrl("https://tinhte.vn")


        setContent {
            NonStopTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
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
                                0 -> BookUI()
                                1 -> BrowserUI()
                                2 -> YoutubeUI()
                            }
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .background(arrayOf(Color.Cyan, Color.Blue, Color.DarkGray)[page])
//                            ) {
//                                if(page == 1) {
//                                    BrowserUI()
//                                } else{
//                                    Text(text = "Page $page")
//                                }
//                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BrowserUI() {
    ComposeBrowser(
        onBrowserStarted = {
            Controller.getInstance().loadUrl("tinhte.vn");
        },
    )
}
