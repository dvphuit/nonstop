package dvp.lib.core.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R> {

    operator fun invoke(parameters: P? = null): R = execute(parameters)

    @Throws(RuntimeException::class)
    protected abstract fun execute(parameters: P?): R
}


//
//abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
//
//    operator fun invoke(parameters: P): Flow<Result<R>> = execute(parameters)
//        .catch { e -> emit(Result.Error(Exception(e))) }
//        .flowOn(coroutineDispatcher)
//
//    protected abstract fun execute(parameters: P): Flow<Result<R>>
//}
//
abstract class SuspendUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameters: P): R {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}