package com.moimmee.userservice.domain.user.exception

import com.moimmee.userservice.domain.core.exception.CustomException

class UserNotFoundException : CustomException(
        exceptionCode = UserExceptionCode.USER_NOT_FOUND
) {
}