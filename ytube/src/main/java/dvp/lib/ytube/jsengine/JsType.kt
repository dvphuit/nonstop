package dvp.lib.ytube.jsengine

sealed class JsType {
    data class Bool(override val value: Boolean) : JsType()
    data class Str(override val value: String) : JsType()
    data class IntNum(override val value: Int) : JsType()
    data class FloatNum(override val value: Double) : JsType()

    object Null : JsType() {
        override val value: Any? get() = null
    }

    abstract val value: Any?

    fun asString(): String? {
        return  when (this) {
            is Bool, is FloatNum, is IntNum -> this.value.toString()
            is Str -> this.value.replace("\"", "\\\"").let {
                "\"$it\""
            }
            Null -> null
        }
    }


    override fun toString(): String {
        return value.toString()
    }
}