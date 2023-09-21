package dvp.ui.youtube.helper

import kotlin.math.min

internal fun calculateKeyFrame(progress: Float, keyframes: List<Pair<Float, Float>>): Float {
    val value = calculateKeyFrame(progress, keyframes) { fraction ->
        fraction * fraction * (3 - 2 * fraction)
    }
    return if (value.isNaN()) 1f else value
}

internal fun calculateKeyFrame(
    position: Float,
    keyframes: List<Pair<Float, Float>>,
    interpolator: (Float) -> Float
): Float {
    var i = 0
    while (i < keyframes.size - 1 && position >= keyframes[i + 1].first) {
        i++
    }
    val start = keyframes[i]
    val end = keyframes[min(i + 1, keyframes.lastIndex)]
    val fraction = (position - start.first) / (end.first - start.first)
    return interpolator(start.second + fraction * (end.second - start.second))
}