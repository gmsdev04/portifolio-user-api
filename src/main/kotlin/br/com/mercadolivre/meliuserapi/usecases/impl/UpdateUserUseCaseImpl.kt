package br.com.mercadolivre.meliuserapi.usecases.impl

import br.com.mercadolivre.meliuserapi.domains.User
import br.com.mercadolivre.meliuserapi.repositories.UserRepository
import br.com.mercadolivre.meliuserapi.usecases.CpfValidatorUseCase
import br.com.mercadolivre.meliuserapi.usecases.UpdateUserUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.util.*

@Service
class UpdateUserUseCaseImpl(
    private val userRepository: UserRepository,
    private val mapper : ObjectMapper,
    private val cpfValidator: CpfValidatorUseCase
) : UpdateUserUseCase{
    override fun update(userUpdatedJson: String, id : UUID) : User?{

        return userRepository.getUserById(id)?.let { user ->
            val currentCpf = user.cpf
            val currentEmail = user.email

            mapper.readerForUpdating(user).readValue(userUpdatedJson, User::class.java)

            if (currentCpf != user.cpf) {
                require(cpfValidator.isValid(user.cpf)) { "CPF is not valid" }
                require(!userRepository.existsByCpf(user.cpf)) { "Another user with the given CPF already exists" }
            }

            if (currentEmail != user.email) {
                require(!userRepository.existsByEmail(user.email)) { "Another user with the given EMAIL already exists" }
            }

            userRepository.update(user)
        }
    }
}