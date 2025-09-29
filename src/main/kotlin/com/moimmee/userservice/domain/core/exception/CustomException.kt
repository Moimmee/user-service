package com.moimmee.userservice.domain.core.exception

open class CustomException(
        val exceptionCode: CustomExceptionCode
) : RuntimeException() {
}