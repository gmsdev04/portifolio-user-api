package br.com.mercadolivre.meliuserapi.boundaries.controllers

import br.com.mercadolivre.meliuserapi.boundaries.controllers.dtos.request.CreateNewUserRequestDto
import br.com.mercadolivre.meliuserapi.boundaries.controllers.dtos.request.PatchUserRequestDto
import br.com.mercadolivre.meliuserapi.boundaries.controllers.dtos.response.ErrorResponse
import br.com.mercadolivre.meliuserapi.boundaries.controllers.dtos.response.UserResponseDto
import br.com.mercadolivre.meliuserapi.usecases.CreateUserUseCase
import br.com.mercadolivre.meliuserapi.usecases.GetUserByIdUseCase
import br.com.mercadolivre.meliuserapi.usecases.ListUsersByNameUseCase
import br.com.mercadolivre.meliuserapi.usecases.UpdateUserUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*


@Validated
@RestController
@RequestMapping("/v1/users")
class UserController(
    val createUseCase : CreateUserUseCase,
    val getByIdUseCase : GetUserByIdUseCase,
    val updateUserUseCase : UpdateUserUseCase,
    val listUsersByNameUseCase : ListUsersByNameUseCase,
    val mapper : ObjectMapper
) {

    @PostMapping
    fun create(@Valid @RequestBody dto : CreateNewUserRequestDto) : ResponseEntity<Any> {

        return try {
            val userCreated = this.createUseCase.create(dto.toDomain())

            ResponseEntity
                .created(URI.create("/v1/users/users/${userCreated.id}"))
                .body(UserResponseDto(userCreated))

        }catch (e : IllegalArgumentException){
            ResponseEntity.unprocessableEntity().body(ErrorResponse(e.message))
        }catch (e : Exception) {
            ResponseEntity.internalServerError().body(ErrorResponse())
        }
    }

    @GetMapping("/{id}")
    fun getById(id : UUID) : ResponseEntity<Any> {
        return try {
            getByIdUseCase.get(id)?.let {
                ResponseEntity.ok(UserResponseDto(it))
            } ?: ResponseEntity.notFound().build()

        }catch (e : Exception) {
            ResponseEntity.internalServerError().body(ErrorResponse())
        }
    }

    @GetMapping
    fun listByName(@RequestParam name : String) : ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(listUsersByNameUseCase.list(name))
        }catch (e : Exception) {
            ResponseEntity.internalServerError().body(ErrorResponse())
        }
    }

    @PatchMapping("/{id}")
    fun patchById(@Valid @RequestBody dto : PatchUserRequestDto,
                  @PathVariable id : UUID,) : ResponseEntity<Any> {

        return try {

            //Não setei direto o Json como parametro pois poderiam passar qualquer campo =)
            val dtoJson = mapper.writeValueAsString(dto)

            this.updateUserUseCase.update(dtoJson, id)?.let {
                ResponseEntity.ok(UserResponseDto(it))
            } ?: ResponseEntity.notFound().build()
        }catch (e : IllegalArgumentException){
            ResponseEntity.unprocessableEntity().body(ErrorResponse(e.message))
        }catch (e : Exception) {
            ResponseEntity.internalServerError().body(ErrorResponse())
        }
    }
}