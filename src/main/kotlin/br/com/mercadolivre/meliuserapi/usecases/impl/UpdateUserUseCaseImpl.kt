package br.com.mercadolivre.meliuserapi.usecases.impl

import br.com.mercadolivre.meliuserapi.domains.User
import br.com.mercadolivre.meliuserapi.exceptions.UserException
import br.com.mercadolivre.meliuserapi.repositories.UserRepository
import br.com.mercadolivre.meliuserapi.usecases.UpdateUserUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.util.*

@Service
class UpdateUserUseCaseImpl(
    private val userRepository: UserRepository,
    private val mapper : ObjectMapper
) : UpdateUserUseCase{
    override fun update(userUpdatedJson: String, id : UUID) : User?{

        return userRepository.getUserById(id)?.let {
            val updater = mapper.readerForUpdating(it)

            updater.readValue(userUpdatedJson, User::class.java)

            userRepository.update(it)

        }
    }
}