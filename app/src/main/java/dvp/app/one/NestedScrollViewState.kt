package dvp.app.one
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Velocity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.withSign

@Composable
fun rememberNestedScrollViewState(): NestedScrollViewState {
    val scope = rememberCoroutineScope()
    val saver = remember { NestedScrollViewState.Saver(scope = scope) }

    return rememberSaveable(saver = saver) {
        NestedScrollViewState(scope = scope)
    }
}

@Stable
class NestedScrollViewState(
    private val scope: CoroutineScope,
    initialOffset: Float = 0f,
    initialMaxOffset: Float = 0f,
) {
    companion object {
        fun Saver(
            scope: CoroutineScope,
        ): Saver<NestedScrollViewState, *> = listSaver(
            save = { // offset을 저장한다.
                listOf(it.offset, it._maxOffset.value)
            },
            restore = { // 저장된 offset을 불러온다.
                NestedScrollViewState(
                    scope = scope,
                    initialOffset = it[0],
                    initialMaxOffset = it[1],
                )
            }
        )
    }

    private var changes = 0f

    private val _maxOffset = mutableStateOf(initialMaxOffset)
    val maxOffset: Float get() = _maxOffset.value.absoluteValue

    /** Animation이 가능한 Int value를 만든다.
     * Animation 중 값이 변경되면, 중단하고 새로운 값으로 전환되어, 값 변경이 계속 이루어진다.
     * */
    private var _offset = Animatable(initialOffset)
    val offset: Float get() = _offset.value

    internal val nestedScrollConnectionHolder = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            println("TEST: onPreScroll -> $available")
            return takeIf {
                available.y < 0 && source == NestedScrollSource.Drag
            }?.let {
                Offset(0f, drag(available.y))
            } ?: Offset.Zero
        }

        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource
        ): Offset {
            println("TEST: onPostScroll -> available = $available, consumed = $consumed")
            return takeIf {
                available.y > 0 && source == NestedScrollSource.Drag
            }?.let {
                Offset(0f, drag(available.y))
            } ?: Offset.Zero
        }

        override suspend fun onPreFling(available: Velocity): Velocity {
            return Velocity(0f, fling(available.y))
        }

        override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
            return Velocity(0f, fling(available.y))
        }
    }


    private suspend fun snapTo(value: Float) {
        _offset.snapTo(value)
    }

    internal suspend fun fling(velocity: Float): Float {
        if (velocity == 0f || velocity > 0 && offset == 0f) {
            return velocity
        }
        val realVelocity = velocity.withSign(changes)
        changes = 0f
        return if (offset > _maxOffset.value && offset <= 0) {
            _offset.animateDecay(
                realVelocity,
                exponentialDecay()
            ).endState.velocity.let {
                if (offset == 0f) {
                    velocity
                } else {
                    it
                }
            }
        } else {
            0f
        }
    }

    internal fun drag(delta: Float): Float {
        return if (delta < 0 && offset > _maxOffset.value || delta > 0 && offset < 0f) {
            changes = delta
            scope.launch {
                snapTo((offset + delta).coerceIn(_maxOffset.value, 0f))
            }
            delta
        } else {
            0f
        }
    }

    internal fun updateBounds(maxOffset: Float) {
        _maxOffset.value = maxOffset
        _offset.updateBounds(lowerBound = maxOffset, upperBound = 0f)
    }
}