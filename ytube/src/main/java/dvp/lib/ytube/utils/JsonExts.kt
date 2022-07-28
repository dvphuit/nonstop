package dvp.lib.ytube.utils

import kotlinx.serialization.json.*

val JsonElement?.text: String?
    get() {
        if (this == null) return null

        if (this.jsonPrimitive.isString) {
            return this.jsonPrimitive.content
        }
        error(message = "$this is not a String")
    }


fun JsonElement?.forEach(function: (JsonElement) -> Boolean?) {
    if(this !is JsonArray){
        println(message = "$this is not JsonArray")
        return
    }

    this.forEach { function.invoke(it) }
}

fun JsonElement?.findElement(predicate: (JsonElement) -> Boolean): JsonElement? {
    if (this is JsonArray) {
        return this.firstOrNull { predicate.invoke(it) } ?: null
    }
    return null
}

fun JsonElement?.map(transform: (JsonElement) -> JsonElement?): JsonArray {
    if (this is JsonArray) {
        return buildJsonArray builder@{
            this@map.forEach {
                this.add(transform(it) ?: JsonNull)
            }
        }
    }
    return JsonArray(emptyList())
}

fun Any?.toPrimitive(): JsonPrimitive {
    return when (this) {
        is String      -> JsonPrimitive(this)
        is Number      -> JsonPrimitive(this)
        is Boolean     -> JsonPrimitive(this)
        is JsonElement -> this.jsonPrimitive
        else           -> JsonNull
    }
}

operator fun JsonElement?.get(s: Any): JsonElement? {
    if (this is JsonObject && s is String) {
        return this[s]
    }
    if (this is JsonArray && s is Int) {
        return this.getOrNull(s)
    }
    println(message = "Json -> Get $this error by query = $s")
    return null
}