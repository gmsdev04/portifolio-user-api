package br.com.mercadolivre.meliuserapi.usecases.impl

import br.com.mercadolivre.meliuserapi.repositories.UserRepository
import br.com.mercadolivre.meliuserapi.usecases.ListUsersByNameUseCase
import org.springframework.stereotype.Service


@Service
class ListUsersByNameUseCaseImpl(
    private val userRepository: UserRepository
) : ListUsersByNameUseCase {
    override fun list(name: String) = userRepository.listUsersByName(name)

}