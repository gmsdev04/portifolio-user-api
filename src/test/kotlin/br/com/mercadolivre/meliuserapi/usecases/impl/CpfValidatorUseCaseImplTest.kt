package br.com.mercadolivre.meliuserapi.usecases.impl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [CpfValidatorUseCaseImpl::class])
class CpfValidatorUseCaseImplTest {

    @Autowired
    lateinit var validator : CpfValidatorUseCaseImpl

    @Test
    fun test_invalid_email(){
        Assertions.assertFalse(validator.isValid("12345678911"))
    }

    @Test
    fun test_valid_email(){
        Assertions.assertTrue(validator.isValid("91932203087"))
    }

}