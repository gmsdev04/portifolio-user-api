package br.com.mercadolivre.meliuserapi.boundaries.controllers.dtos.request

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate


@JsonInclude(JsonInclude.Include.NON_NULL)
class PatchUserRequestDto {
    var name : String? = null
    @field:Pattern(regexp = "^[0-9]+$", message = "Only digits are allowed in CPF")
    @Size(min = 11,max = 11, message = "CPF must have 11 digits")
    val cpf : String? = null
    @Email
    val email : String? = null
    val dateOfBirth : LocalDate? = null
}