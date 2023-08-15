package dvp.lib.ytube

import org.junit.Test


class ParseDataTest : BaseTest() {

    @Test
    fun initParseTest() {
        val data = readFile("init.json")
        val (videos, continuation) = DataParser.parserInitData(data)

        println(videos)
        println(continuation)
    }


    @Test
    fun continueParseTest() {
        val data = readFile("continue.json")
        val (videos, continuation) = DataParser.parseContinueData(data)

        println(videos)
        println(continuation)
    }

    @Test
    fun relativeParseTest() {
        val data = readFile("next.json")
        val (videos, continuation) = DataParser.parseRelativeData(data)

        println(videos)
        println(continuation)
    }

    @Test
    fun videoDetailTest() {
        val data = readFile("video_detail.json")
        val videoData = DataParser.parseVideoDetail(data)
        println(videoData)
    }
}