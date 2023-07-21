package dvp.data.youtube.domain

import dvp.data.youtube.public.YoutubeRepo
import dvp.lib.core.domain.UseCase

class AuthenticatedUseCase(private val repo: YoutubeRepo) :
    UseCase<AuthenticatedUseCase.Params, Unit>() {

    override fun execute(parameters: Params?) {
        repo.setCookie(parameters!!.cookie!!)
    }

    class Params private constructor(val cookie: String?) {

        companion object {
            @JvmStatic
            fun create(
                cookie: String? = "",
            ): Params {
                return Params(cookie)
            }
        }
    }


}