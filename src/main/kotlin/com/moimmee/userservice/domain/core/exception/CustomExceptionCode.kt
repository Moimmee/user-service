package com.moimmee.userservice.domain.core.exception

import org.springframework.http.HttpStatus

interface CustomExceptionCode {
    val status: HttpStatus
    val message: String
}