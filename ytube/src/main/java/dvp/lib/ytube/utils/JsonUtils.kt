package dvp.lib.ytube.utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

object JsonUtils {
    private val jsonBuilder = Json {
        this.isLenient = true
        this.prettyPrint = false
    }

    fun String.findJson(
        source: String,
        varname: String,
        left: Any,
        right: String,
        prepend: String
    ): JsonElement {
        val jsonStr = this.between(left, right)

        if (jsonStr.isEmpty()) {
            error("Could not find $varname in $source")
        }

        return (prepend + jsonStr).toJson(source, varname)
    }

    private fun String.toJson(source: String, varname: String): JsonElement {
        try {
            return jsonBuilder.parseToJsonElement(
                this.cutAfterJSON().replace(Regex("^[)\\]}'\\s]+"), "")
            )
        } catch (e: Exception) {
            error("Error parsing $varname in ${source}: ${e.message}");
        }
    }

    fun String.cutAfterJSON(): String {
        val (open, close) = getJsonBrackets(this)
        var isString = false
        var isEscaped = false
        var count = 0

        for (i in this.indices) {
            if (this[i] == '"' && !isEscaped) {
                isString = !isString
                continue
            }
            isEscaped = this[i] == '\\' && !isEscaped

            if (isString) continue

            when (this[i]) {
                open -> count++
                close -> count--
            }

            if (count == 0) {
                return this.substring(0, i + 1)
            }
        }
        error("Can't cut unsupported JSON (no matching closing bracket found)")
    }


    private fun getJsonBrackets(str: String): Pair<Char?, Char?> {
        if (str.startsWith("[")) {
            return '[' to ']'
        }
        if (str.startsWith("{")) {
            return '{' to '}'
        }
        error("Can't cut unsupported JSON (need to begin with [ or { ) but got: ${str[0]}")
    }

    fun String.between(left: Any, right: String): String {
        var start = 0
        if (left is Regex) {
            val match = left.find(this) ?: return ""
            start = match.range.last + 1
        }
        if (left is String) {
            start = this.indexOf(left);
            if (start == -1) return ""
            start += left.length
        }
        val end = this.indexOf(right, startIndex = start)
        if (end == -1) return ""

        return this.slice(IntRange(start, end - 1))
    }
}