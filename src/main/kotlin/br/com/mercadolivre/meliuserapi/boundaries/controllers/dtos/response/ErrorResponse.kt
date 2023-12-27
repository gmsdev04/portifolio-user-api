package br.com.mercadolivre.meliuserapi.boundaries.controllers.dtos.response

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorResponse(
    val message : String? = "Houve um erro interno, estamos trabalhando para resolver o quanto antes, obrigado!"
)