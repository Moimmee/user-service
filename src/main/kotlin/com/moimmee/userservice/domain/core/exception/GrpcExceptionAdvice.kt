package com.moimmee.userservice.domain.core.exception

import io.grpc.Status
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler

@GrpcAdvice
class GrpcExceptionAdvice {

    @GrpcExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): Status {
        return e.exceptionCode.status
    }

}