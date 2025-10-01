package com.moimmee.userservice.domain.user.persistence.entity

import com.moimmee.userservice.domain.core.entity.BaseEntity
import com.moimmee.userservice.domain.user.infra.enum.UserGender
import com.moimmee.userservice.domain.user.infra.enum.UserRole
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "users")
class UserEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @NotBlank
        var name: String,

        @NotBlank
        @Column(updatable = false)
        val role: UserRole,

        @NotNull
        @Column(updatable = false)
        val gender: UserGender,

        @Email
        var email: String

) : BaseEntity() {
        fun update(@NotBlank name: String, email: String) {
                this.name = name
                this.email = email
        }
}