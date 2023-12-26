package br.com.mercadolivre.meliuserapi.usecases.impl

import br.com.mercadolivre.meliuserapi.repositories.UserRepository
import br.com.mercadolivre.meliuserapi.usecases.GetUserByIdUseCase
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetUserByIdUseCaseImpl(
    private val userRepository: UserRepository
) : GetUserByIdUseCase {
    override fun get(id: UUID) = userRepository.getUserById(id)
}