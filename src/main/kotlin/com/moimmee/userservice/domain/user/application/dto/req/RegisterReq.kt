package com.moimmee.userservice.domain.user.application.dto.req

import com.moimmee.userservice.domain.user.infra.enum.UserGender
import com.moimmee.userservice.domain.user.infra.enum.UserRole
import com.moimmee.userservice.domain.user.persistence.entity.UserEntity

data class RegisterReq(
        var name: String,
        var gender: UserGender,
        var email: String
) {
    companion object {
        fun toEntity(req: RegisterReq): UserEntity {
            return UserEntity(
                    name = req.name,
                    role = UserRole.ROLE_USER,
                    gender = req.gender,
                    email = req.email
            )
        }
    }
}
