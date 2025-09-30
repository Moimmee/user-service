package com.moimmee.userservice.domain.core.exception

import io.grpc.Status

interface CustomExceptionCode {
    val status: Status
    val message: String
}