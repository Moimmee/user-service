package com.moimmee.userservice.domain.user.exception

import com.moimmee.userservice.domain.core.exception.CustomExceptionCode
import io.grpc.Status

enum class UserExceptionCode(
        override val status: Status,
        override val message: String
) : CustomExceptionCode {

    USER_NOT_FOUND(Status.NOT_FOUND, "없는 유저"),
    EMAIL_ALREADY_USED(Status.ALREADY_EXISTS, "이미 사용된 이메일")

}