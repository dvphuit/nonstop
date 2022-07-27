package dvp.lib.jsengine

import dvp.lib.jsengine.internal.JsEngine
import dvp.lib.jsengine.internal.JsEngineImpl

object JsEval {
    private val engine : JsEngine = JsEngineImpl()

    fun evaluate(context: Map<String, JsType>, script: String) = engine.evaluate(context, script)

    fun close() = engine.close()
}