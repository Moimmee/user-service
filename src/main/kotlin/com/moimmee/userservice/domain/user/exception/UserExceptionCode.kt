package com.moimmee.userservice.domain.user.exception

import com.moimmee.userservice.domain.core.exception.CustomExceptionCode
import org.springframework.http.HttpStatus

enum class UserExceptionCode(
        override val status: HttpStatus,
        override val message: String
) : CustomExceptionCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "없는 유저"),
    EMAIL_ALREADY_USED(HttpStatus.CONFLICT, "이미 사용된 이메일")

}