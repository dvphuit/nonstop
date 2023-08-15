package dvp.lib.ytube

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

abstract class BaseTest {
    val serializer = Json {
        ignoreUnknownKeys = true
    }

    protected fun readFile(file: String): String {
//        val stream = this.javaClass.classLoader?.getResourceAsStream(file)
        val stream = javaClass.classLoader!!.getResourceAsStream(file)
        return stream?.bufferedReader()?.use { it.readText() }!!
    }

    protected fun parseToJson(json: String): JsonElement {
        return serializer.parseToJsonElement(json)
    }

    protected fun fileToJsonElement(file: String): JsonElement {
        val content = readFile(file)
        return parseToJson(content)
    }
}