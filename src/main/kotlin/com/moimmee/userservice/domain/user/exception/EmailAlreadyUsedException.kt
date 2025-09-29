package com.moimmee.userservice.domain.user.exception

import com.moimmee.userservice.domain.core.exception.CustomException

class EmailAlreadyUsedException : CustomException(
        exceptionCode = UserExceptionCode.EMAIL_ALREADY_USED
) {
}