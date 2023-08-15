package dvp.lib.core.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R> {

    operator fun invoke(parameters: P? = null): R = execute(parameters)

    @Throws(RuntimeException::class)
    protected abstract fun execute(parameters: P?): R
}


abstract class FlowUseCase<in P, R>(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    operator fun invoke(parameters: P): Flow<R> = execute(parameters)
        .catch { e -> throw e }
        .flowOn(dispatcher)

    protected abstract fun execute(parameters: P): Flow<R>
}


abstract class SuspendUseCase<in P, R>(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend operator fun invoke(parameters: P): R {
        return try {
            withContext(dispatcher) {
                execute(parameters)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}