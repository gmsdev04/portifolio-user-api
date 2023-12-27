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

    override fun create(newUser: User) : User{
        val age = YEARS.between(newUser.dateOfBirth, LocalDate.now())

        require(age > 18) { "The user age must be above 18 years" }

        require(cpfValidator.isValid(newUser.cpf)) { "CPF is not valid" }

        require(!userRepo.existsByCpf(newUser.cpf)) { "User with the given CPF already exists" }

        require(!userRepo.existsByEmail(newUser.email)) { "User with the given EMAIL already exists" }


        return userRepo.create(newUser)
    }

}