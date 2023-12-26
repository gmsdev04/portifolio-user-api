package br.com.mercadolivre.meliuserapi.usecases.impl

import br.com.mercadolivre.meliuserapi.domains.User
import br.com.mercadolivre.meliuserapi.exceptions.UserException
import br.com.mercadolivre.meliuserapi.repositories.UserRepository
import br.com.mercadolivre.meliuserapi.usecases.CpfValidatorUseCase
import br.com.mercadolivre.meliuserapi.usecases.CreateUserUseCase
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.temporal.ChronoUnit.YEARS
@Service
class CreateUserUseCaseImpl(
    private val userRepo : UserRepository,
    private val cpfValidator : CpfValidatorUseCase
) : CreateUserUseCase {

    override fun create(newUser: User)= YEARS.between(newUser.dateOfBirth, LocalDate.now())
        .takeIf { it > 18 }
        ?.let {
            newUser.cpf.takeUnless { userRepo.existsByCpf(it) }
                ?.let { userRepo.create(newUser) }
                ?: throw UserException("User with the provided CPF already exists")
        } ?: throw UserException("The user age must be above 18 years")

}