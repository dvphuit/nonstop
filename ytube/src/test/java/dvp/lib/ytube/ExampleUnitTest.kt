package dvp.lib.ytube

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
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun urlTest(){
        runBlocking {
            val start = System.currentTimeMillis()
            val info = YtExtractor().run {
                isTest = true
                getBasicInfo("aqz-KE-bpKQ")
            }
            println("extract end at ${System.currentTimeMillis() - start}ms")
        }
    }
}