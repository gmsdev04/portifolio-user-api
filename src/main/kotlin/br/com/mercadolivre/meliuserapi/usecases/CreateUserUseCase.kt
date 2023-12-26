package br.com.mercadolivre.meliuserapi.usecases

import br.com.mercadolivre.meliuserapi.domains.User

interface CreateUserUseCase {
    fun create(newUser : User) : User
}