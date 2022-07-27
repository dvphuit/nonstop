package dvp.lib.jsengine.internal

import dvp.lib.jsengine.JsType


internal interface JsEngine {

    fun evaluate(context: Map<String, JsType>, script: String): JsType

    fun close()

}
