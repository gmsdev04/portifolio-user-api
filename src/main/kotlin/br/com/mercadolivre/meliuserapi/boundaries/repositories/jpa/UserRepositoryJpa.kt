package br.com.mercadolivre.meliuserapi.boundaries.repositories.jpa

import br.com.mercadolivre.meliuserapi.boundaries.repositories.jpa.entities.UserEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepositoryJpa : CrudRepository<UserEntity, UUID>{
    @Query("SELECT * FROM users WHERE name like CONCAT('%',:name,'%')", nativeQuery = true)
    fun listByName(name : String) : List<UserEntity>

    fun existsByCpf(cpf : String) : Boolean
}