package br.com.mercadolivre.meliuserapi.domains

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class User(
    val id : UUID = UUID.randomUUID(),
    var name : String,
    var cpf : String,
    var email : String,
    var dateOfBirth : LocalDate,
    val createdAt : LocalDateTime = LocalDateTime.now(),
    val updatedAt : LocalDateTime? = null
)
