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

        return userRepository.getUserById(id)?.let {

            val currentCpf = it.cpf

            val updater = mapper.readerForUpdating(it)

            updater.readValue(userUpdatedJson, User::class.java)

            if(currentCpf != it.cpf){
                require(cpfValidator.isValid(it.cpf)) { "CPF is not valid" }

                require(!userRepository.existsByCpf(it.cpf)) {"User with the given CPF already exists"}
            }

            userRepository.update(it)

        }
    }
}