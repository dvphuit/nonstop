package dvp.lib.ytube.utils

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

internal object JsonUtils {

    fun String.findJson(
        source: String,
        varname: String,
        left: Any,
        right: String,
        prepend: String
    ): String {
        val jsonStr = this.between(left, right)

        if (jsonStr.isEmpty()) {
            error("Could not find $varname in $source")
        }

        return (prepend + jsonStr).toJson(source, varname)
    }

    private fun String.toJson(source: String, varname: String): String {
        try {
            return this.cutAfterJSON().replace(Regex("^[)\\]}'\\s]+"), "")
        } catch (e: Exception) {
            error("Error parsing $varname in ${source}: ${e.message}")
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
            start = this.indexOf(left)
            if (start == -1) return ""
            start += left.length
        }
        val end = this.indexOf(right, startIndex = start)
        if (end == -1) return ""

        return this.slice(IntRange(start, end - 1))
    }

    fun JsonObject.getNestedObj(vararg keys: String): JsonElement? {
        if (keys.isEmpty()) return this

        val ret = this[keys[0]]
        if (ret is JsonObject) {
            return ret.getNestedObj(*keys.drop(1).toTypedArray())
        }
        return ret
    }
}