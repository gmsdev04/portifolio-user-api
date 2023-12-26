package br.com.mercadolivre.meliuserapi.usecases

import br.com.mercadolivre.meliuserapi.domains.User

interface ListUsersByNameUseCase {
    fun list(name : String) : List<User>
}