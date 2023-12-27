package br.com.mercadolivre.meliuserapi.boundaries.repositories

import br.com.mercadolivre.meliuserapi.boundaries.repositories.jpa.UserRepositoryJpa
import br.com.mercadolivre.meliuserapi.boundaries.repositories.jpa.entities.UserEntity
import br.com.mercadolivre.meliuserapi.domains.User
import br.com.mercadolivre.meliuserapi.repositories.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UserRepositoryImpl(
    val jpa : UserRepositoryJpa
) : UserRepository {
    override fun existsByCpf(cpf: String) = jpa.existsByCpf(cpf)
    override fun existsByEmail(email: String) = jpa.existsByEmail(email)

    override fun create(newUser: User) = jpa.save(UserEntity(newUser)).toDomain()

    override fun update(updatedUser: User) : User? {

        val userToUpdateOpt = jpa.findById(updatedUser.id)

        return userToUpdateOpt.map {
            it.apply {
                name = updatedUser.name
                dateOfBirth = updatedUser.dateOfBirth
                cpf = updatedUser.cpf
                email = updatedUser.email
                updatedAt = LocalDateTime.now()
                jpa.save(this)
            }.toDomain()
        }.orElse(null)
    }

    override fun listUsersByName(name: String) = jpa.listByName(name).map { it.toDomain() }

    override fun getUserById(id: UUID): User? {
        val userOpt = jpa.findById(id)

        return if(userOpt.isPresent) userOpt.get().toDomain() else null
    }
}