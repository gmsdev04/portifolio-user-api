package br.com.mercadolivre.meliuserapi.boundaries.controllers.dtos.response

data class ErrorResponse(
    val message : String? = "Houve um erro interno, estamos trabalhando para resolver o quanto antes, obrigado!"
)