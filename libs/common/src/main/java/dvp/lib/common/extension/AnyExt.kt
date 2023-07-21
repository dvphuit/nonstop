package dvp.lib.common.extension

val Any.classTag: String get() = this.javaClass.canonicalName.orEmpty()

val Any.methodTag get() = classTag + object : Any() {}.javaClass.enclosingMethod?.name

fun Any.hashCodeAsString(): String {
    return hashCode().toString()
}

@Suppress("UNCHECKED_CAST")
fun <T> Any?.cast(): T {
    return this as T
}