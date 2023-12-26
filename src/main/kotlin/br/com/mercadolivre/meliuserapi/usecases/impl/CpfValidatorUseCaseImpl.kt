package br.com.mercadolivre.meliuserapi.usecases.impl

import br.com.caelum.stella.validation.CPFValidator
import br.com.caelum.stella.validation.InvalidStateException
import br.com.mercadolivre.meliuserapi.usecases.CpfValidatorUseCase
import org.springframework.stereotype.Service

@Service
class CpfValidatorUseCaseImpl : CpfValidatorUseCase {
    override fun isValid(cpf: String): Boolean {
        return try {
            CPFValidator().assertValid(cpf)
            true
        }catch (e : InvalidStateException){
            false
        }
    }
}