package com.moimmee.userservice.domain.user.application.dto.req

data class EditUserReq(
        val id: Long,
        val name: String,
        val email: String
)
