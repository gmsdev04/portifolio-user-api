package br.com.mercadolivre.meliuserapi.boundaries.controllers.dtos.response

import br.com.mercadolivre.meliuserapi.domains.User
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class UserResponseDto(val id : UUID,
                           val name : String,
                           val cpf : String,
                           val dateOfBirth : LocalDate,
                           val createdAt : LocalDateTime,
                           val updatedAt : LocalDateTime?){
    constructor(model : User) : this(model.id, model.name, model.cpf, model.dateOfBirth, model.createdAt, model.updatedAt)
}