package br.com.mercadolivre.meliuserapi.boundaries.controllers.dtos.request

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate


@JsonInclude(JsonInclude.Include.NON_NULL)
data class PatchUserRequestDto(
    val name : String?,
    val cpf : String?,
    val dateOfBirth : LocalDate?
){
}