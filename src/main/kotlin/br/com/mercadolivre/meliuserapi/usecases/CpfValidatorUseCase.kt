package br.com.mercadolivre.meliuserapi.usecases

interface CpfValidatorUseCase {
    fun isValid(cpf : String) : Boolean
}