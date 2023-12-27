package br.com.mercadolivre.meliuserapi.usecases.impl

import br.com.mercadolivre.meliuserapi.domains.User
import br.com.mercadolivre.meliuserapi.repositories.UserRepository
import br.com.mercadolivre.meliuserapi.usecases.CpfValidatorUseCase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDate
import java.util.*


@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [CreateUserUseCaseImpl::class])
class CreateUserUseCaseImplTest {

    @Autowired
    lateinit var usecase : CreateUserUseCaseImpl

    @MockBean
    lateinit var userRepo : UserRepository

    @MockBean
    lateinit var cpfValidator : CpfValidatorUseCase

    @Test
    fun must_fail_less_than_19_years_old(){
        val user = User(
            email = "lorem@ipsum.com",
            dateOfBirth = LocalDate.now().minusYears(2),
            id = UUID.randomUUID(),
            name = "Lorem name",
            cpf = "91932203087"
        )

        try{
            usecase.create(user)
        }catch (e : IllegalArgumentException){
            e.message.equals("The user age must be above 18 years")
        }
    }


    @Test
    fun must_fail_invalid_cpf(){

        val user = User(
            email = "lorem@ipsum.com",
            dateOfBirth = LocalDate.now().minusYears(20),
            id = UUID.randomUUID(),
            name = "Lorem name",
            cpf = "91932203087"
        )

        Mockito.`when`(cpfValidator.isValid(user.cpf)).thenReturn(false)

        try{
            usecase.create(user)
        }catch (e : IllegalArgumentException){
            e.message.equals("CPF is not valid")
        }
    }

    @Test
    fun must_fail_user_with_cpf_already_exists(){

        val user = User(
            email = "lorem@ipsum.com",
            dateOfBirth = LocalDate.now().minusYears(20),
            id = UUID.randomUUID(),
            name = "Lorem name",
            cpf = "91932203087"
        )

        Mockito.`when`(cpfValidator.isValid(user.cpf)).thenReturn(true)
        Mockito.`when`(userRepo.existsByCpf(user.cpf)).thenReturn(true)

        try{
            usecase.create(user)
        }catch (e : IllegalArgumentException){
            e.message.equals("User with the given CPF already exists")
        }
    }

    @Test
    fun must_fail_user_with_email_already_exists(){

        val user = User(
            email = "lorem@ipsum.com",
            dateOfBirth = LocalDate.now().minusYears(20),
            id = UUID.randomUUID(),
            name = "Lorem name",
            cpf = "91932203087"
        )

        Mockito.`when`(cpfValidator.isValid(user.cpf)).thenReturn(true)
        Mockito.`when`(userRepo.existsByCpf(user.cpf)).thenReturn(false)
        Mockito.`when`(userRepo.existsByEmail(user.email)).thenReturn(true)

        try{
            usecase.create(user)
        }catch (e : IllegalArgumentException){
            e.message.equals("User with the given EMAIL already exists")
        }
    }

    @Test
    fun must_create_user_succesfully(){

        val user = User(
            email = "lorem@ipsum.com",
            dateOfBirth = LocalDate.now().minusYears(20),
            id = UUID.randomUUID(),
            name = "Lorem name",
            cpf = "91932203087"
        )

        Mockito.`when`(cpfValidator.isValid(user.cpf)).thenReturn(true)
        Mockito.`when`(userRepo.existsByCpf(user.cpf)).thenReturn(false)
        Mockito.`when`(userRepo.existsByEmail(user.email)).thenReturn(false)

        usecase.create(user)
    }


}