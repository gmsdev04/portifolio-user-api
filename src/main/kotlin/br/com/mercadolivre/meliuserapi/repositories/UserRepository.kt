package br.com.mercadolivre.meliuserapi.repositories

import br.com.mercadolivre.meliuserapi.domains.User
import java.util.*

interface UserRepository {
    fun existsByCpf(cpf: String) : Boolean

    fun existsByEmail(email : String) : Boolean

    fun create(newUser : User) : User

    fun update(updatedUser : User) : User?

    fun listUsersByName(name : String) : List<User>

    fun getUserById(id : UUID) : User?
}