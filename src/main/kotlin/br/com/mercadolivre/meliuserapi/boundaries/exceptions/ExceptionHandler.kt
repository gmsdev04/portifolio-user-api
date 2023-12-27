package br.com.mercadolivre.meliuserapi.boundaries.exceptions

import br.com.mercadolivre.meliuserapi.boundaries.controllers.dtos.response.ValidationErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody


@ControllerAdvice
class ExceptionHandler {


    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun processUnmergeException(ex: MethodArgumentNotValidException): ResponseEntity<List<ValidationErrorResponseDto>> {
        val errors = ex.bindingResult.allErrors.map { error ->
            if (error is FieldError) {
                ValidationErrorResponseDto(error.field, error.defaultMessage)
            } else {
                ValidationErrorResponseDto("", error.defaultMessage)
            }
        }

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}