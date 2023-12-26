package br.com.mercadolivre.meliuserapi.usecases

import br.com.mercadolivre.meliuserapi.domains.User
import java.util.*

interface UpdateUserUseCase {
    fun update(userUpdatedJson : String, id : UUID) : User?
}