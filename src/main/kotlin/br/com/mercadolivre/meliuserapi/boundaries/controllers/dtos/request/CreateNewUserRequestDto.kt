package br.com.mercadolivre.meliuserapi.boundaries.controllers.dtos.request

import br.com.mercadolivre.meliuserapi.domains.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate

class CreateNewUserRequestDto {
    @NotNull
    lateinit var name : String
    @NotNull
    @field:Pattern(regexp = "^[0-9]+$", message = "Only digits are allowed in CPF")
    @Size(min = 11,max = 11, message = "CPF must have 11 digits")
    lateinit var cpf : String
    @Email
    @NotNull
    lateinit var email : String
    @NotNull
    lateinit var dateOfBirth : LocalDate

    fun toDomain() = User(cpf=this.cpf,name=this.name, dateOfBirth = this.dateOfBirth, email = this.email)
}