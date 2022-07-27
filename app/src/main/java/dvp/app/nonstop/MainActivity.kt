package dvp.app.nonstop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dvp.app.nonstop.ui.theme.NonStopTheme
import dvp.lib.ytube.YtExtractor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NonStopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")

    LaunchedEffect(Unit){
        val videoId = "xsPcMO9CRBE"
        val result = YtExtractor().getBasicInfo(videoId)
        println(result)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NonStopTheme {
        Greeting("Android")
    }
}