package dvp.lib.ytube

import dvp.lib.ytube.jsengine.JsEngine
import dvp.lib.ytube.jsengine.toJsType
import dvp.lib.ytube.utils.JsonUtils.between
import dvp.lib.ytube.utils.JsonUtils.cutAfterJSON
import io.ktor.http.*
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive

object Sig {

    fun decipherFormats(formats: List<JsonObject>, htmlPlayer: String): List<JsonObject> {
        val (decipherScript, nTransformScript) = extractFunctions(htmlPlayer)
        return formats.map {
            setDownloadURL(it, decipherScript, nTransformScript)
        }
    }

    private fun setDownloadURL(
        format: JsonObject,
        decipherScript: String,
        nTransformScript: String
    ): JsonObject {

        fun decipher(url: String): String {
            println("decipher: script=$decipherScript")
            error("decipher: Not implemented yet for url=$url")
        }

        fun ncode(url: String): String {
            val components = URLBuilder(url)
            val ncode = components.parameters["n"]
            if (ncode.isNullOrEmpty() || nTransformScript.isEmpty()) return url

            components.parameters["n"] =
                JsEngine.evaluate(mapOf("ncode" to ncode.toJsType()), nTransformScript).value as String
            return components.buildString()
        }

        val cipher = format["url"] == null
        val url = (format["url"] ?: format["signatureCipher"] ?: format["cipher"])
                ?.jsonPrimitive?.content
                ?: error("Cannot get url from format=$format")

        val newUrl = if (cipher) ncode(decipher(url)) else ncode(url)
        return JsonObject(format.toMutableMap().apply {
            this["url"] = JsonPrimitive(newUrl)
        })
    }


    private fun extractFunctions(htmlPlayer: String): List<String> {
        return extractDecipher(htmlPlayer) + extractNCode(htmlPlayer)
    }

    private fun extractDecipher(htmlPlayer: String): ArrayList<String> {
        val funs = arrayListOf<String>()
        val functionName = htmlPlayer.between("a.set(\"alr\",\"yes\");c&&(c=", "(decodeURIC")
        if (functionName.isNotEmpty()) {
            val functionStart = "${functionName}=function(a)"
            val index = htmlPlayer.indexOf(functionStart)
            if (index > -1) {
                val subBody = htmlPlayer.substring(index + functionStart.length)
                var functionBody = "var ${functionStart}${subBody.cutAfterJSON()}"
                functionBody = "${
                    extractManipulations(
                        htmlPlayer,
                        functionBody
                    )
                };${functionBody};${functionName}(sig);"
                funs.add(functionBody)
            }
        }
        return funs
    }

    private fun extractNCode(htmlPlayer: String): ArrayList<String> {
        val funs = arrayListOf<String>()
        var functionName = htmlPlayer.between("&&(b=a.get(\"n\"))&&(b=", "(b)")
        if (functionName.contains('[')) {
            functionName = htmlPlayer.between("${functionName.split('[').first()}=[", "]")
        }
        if (functionName.isNotEmpty()) {
            val functionStart = "${functionName}=function(a)"
            val index = htmlPlayer.indexOf(functionStart)
            if (index >= 0) {
                val subBody = htmlPlayer.substring(index + functionStart.length);
                val functionBody =
                    "var ${functionStart}${subBody.cutAfterJSON()};${functionName}(ncode);"
                funs.add(functionBody)
            }
        }
        return funs
    }

    private fun extractManipulations(htmlPlayer: String, caller: String): String {
        val functionName = caller.between("a=a.split(\"\");", ".")
        if (functionName.isEmpty()) return ""
        val functionStart = "var ${functionName}={"
        val ndx = htmlPlayer.indexOf(functionStart);
        if (ndx < 0) return ""
        val subBody = htmlPlayer.substring(ndx + functionStart.length - 1);
        return "var ${functionName}=${subBody.cutAfterJSON()}"
    }
}