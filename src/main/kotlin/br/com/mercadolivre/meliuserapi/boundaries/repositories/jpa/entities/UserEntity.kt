package br.com.mercadolivre.meliuserapi.boundaries.repositories.jpa.entities

import br.com.mercadolivre.meliuserapi.domains.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.NoArgsConstructor
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@NoArgsConstructor
@Entity(name="users")
data class UserEntity(
    @Id
    @Column(name="id")
    var id : UUID,
    @Column(name="name")
    var name : String,
    @Column(name="cpf")
    var cpf : String,
    @Column(name="email")
    var email : String,
    @Column(name="date_of_birth")
    var dateOfBirth : LocalDate,
    @Column(name="created_at")
    var createdAt : LocalDateTime,
    @Column(name="updated_at")
    var updatedAt : LocalDateTime?
) {
    constructor(user : User) : this(user.id, user.name, user.cpf, user.email, user.dateOfBirth, user.createdAt, user.updatedAt)

    fun toDomain() = User(id, name, cpf, email, dateOfBirth, createdAt, updatedAt)
}
