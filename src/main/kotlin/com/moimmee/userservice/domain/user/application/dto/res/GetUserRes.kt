package com.moimmee.userservice.domain.user.application.dto.res

import com.moimmee.userservice.domain.user.infra.enum.UserGender
import com.moimmee.userservice.domain.user.infra.enum.UserRole
import com.moimmee.userservice.domain.user.persistence.entity.UserEntity

data class GetUserRes(
        var name: String,
        var role: UserRole,
        var gender: UserGender,
        var email: String
) {
    companion object {
        fun of(user: UserEntity): GetUserRes {
            return GetUserRes(
                    user.name,
                    user.role,
                    user.gender,
                    user.email
            )
        }
    }
}
