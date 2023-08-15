package dvp.data.youtube.usecase

import dvp.data.youtube.public.UTubeRepo
import dvp.lib.core.domain.UseCase

class AuthenticatedUseCase(private val repo: UTubeRepo) :
    UseCase<AuthenticatedUseCase.Params, Unit>() {

    override fun execute(parameters: Params?) {
        repo.setCookie(parameters!!.cookie!!)
    }

    class Params private constructor(val cookie: String?) {

        companion object {
            fun create(
                cookie: String? = "",
            ): Params {
                return Params(cookie)
            }
        }
    }


}