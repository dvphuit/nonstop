package dvp.lib.ytube

import dvp.lib.ytube.network.VideoApi
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun urlTest(){
        runBlocking {
            val start = System.currentTimeMillis()
            val info = YtExtractor().run {
                isTest = true
                getBasicInfo("aqz-KE-bpKQ")
            }
            println("info: $info")
            println("extract end at ${System.currentTimeMillis() - start}ms")
        }
    }


    @Test
    fun apiTest(){
        val api = VideoApi()
        runBlocking {
            val res = api.list()
            println(res.bodyAsText())
        }
    }
}