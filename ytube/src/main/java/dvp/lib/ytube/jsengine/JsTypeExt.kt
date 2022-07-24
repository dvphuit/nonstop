package dvp.lib.ytube.jsengine


fun Any?.toJsType(): JsType {
    return when (this) {
        null -> JsType.Null
        is Boolean -> JsType.Bool(this)
        is Int -> JsType.IntNum(this)
        is Double -> JsType.FloatNum(this)
        is Float -> JsType.FloatNum(this.toDouble())
        is String -> JsType.Str(this)
        else -> error(
            message = "Error: JsEngine convert [$this] to JsType"
        )
    }
}