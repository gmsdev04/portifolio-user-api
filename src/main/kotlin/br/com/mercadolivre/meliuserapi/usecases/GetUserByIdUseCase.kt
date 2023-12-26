package br.com.mercadolivre.meliuserapi.usecases

import br.com.mercadolivre.meliuserapi.domains.User
import java.util.*

interface GetUserByIdUseCase {
    fun get(id : UUID) : User?
}