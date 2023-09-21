package dvp.ui.youtube.view.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner


@Composable
fun AppStateHandler(onBackground: () -> Unit, onForeground: () -> Unit) = OnLifecycleEvent(onEvent = { _, event ->
    when (event) {
        Lifecycle.Event.ON_RESUME -> {
            onForeground.invoke()
        }

        Lifecycle.Event.ON_STOP -> {
            onBackground.invoke()
        }

        else -> {}
    }
})

@Composable
internal fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}