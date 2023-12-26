package br.com.mercadolivre.meliuserapi.boundaries.controllers.dtos.request

import br.com.mercadolivre.meliuserapi.domains.User
import jakarta.validation.constraints.NotNull
import lombok.Builder
import java.time.LocalDate

data class CreateNewUserRequestDto(
    var name : String,
    var cpf : String,
    var dateOfBirth : LocalDate
){
    fun toDomain() = User(cpf=this.cpf,name=this.name, dateOfBirth = this.dateOfBirth)
}