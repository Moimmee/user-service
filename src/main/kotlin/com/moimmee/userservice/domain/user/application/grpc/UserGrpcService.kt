package com.moimmee.userservice.domain.user.application.grpc

import com.google.protobuf.Empty
import com.moimmee.proto.user.UserServiceGrpc.UserServiceImplBase
import com.moimmee.proto.user.UserServiceProto
import com.moimmee.proto.user.UserServiceProto.EditUserRequest
import com.moimmee.proto.user.UserServiceProto.GetUserResponse
import com.moimmee.userservice.domain.user.application.dto.req.DeleteUserReq
import com.moimmee.userservice.domain.user.application.dto.req.EditUserReq
import com.moimmee.userservice.domain.user.application.dto.req.GetUserReq
import com.moimmee.userservice.domain.user.application.dto.req.RegisterReq
import com.moimmee.userservice.domain.user.application.dto.res.GetUserRes
import com.moimmee.userservice.domain.user.domain.usecase.UserUseCase
import com.moimmee.userservice.domain.user.infra.enum.UserGender
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class UserGrpcService(
        val userUseCase: UserUseCase
) : UserServiceImplBase() {

    override fun register(request: UserServiceProto.RegisterUserRequest, responseObserver: StreamObserver<Empty>) {
        val mappedDto = RegisterReq(
                name = request.name,
                gender = UserGender.valueOf(request.gender),
                email = request.email
        )
        userUseCase.register(mappedDto)

        responseObserver.onNext(Empty.getDefaultInstance())
        responseObserver.onCompleted()
    }

    private fun mapGetUserResDtoToGrpcResponse(res: GetUserRes): GetUserResponse {
        return GetUserResponse.newBuilder()
                .setName(res.name)
                .setGender(res.gender.name)
                .setEmail(res.email)
                .build()
    }

    override fun getUser(request: UserServiceProto.GetUserRequest, responseObserver: StreamObserver<GetUserResponse>) {
        val mappedReq = GetUserReq(id = request.id)
        val result = userUseCase.getUser(mappedReq)
        val response = mapGetUserResDtoToGrpcResponse(result)

        responseObserver.onNext(response);
        responseObserver.onCompleted()
    }

    override fun getAllUsers(request: Empty, responseObserver: StreamObserver<UserServiceProto.GetAllUsersResponse>) {
        val result = userUseCase.getAllUsers();
        val response = UserServiceProto.GetAllUsersResponse.newBuilder()
                .addAllUser(result.map { mapGetUserResDtoToGrpcResponse(it) })
                .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun editUser(request: EditUserRequest, responseObserver: StreamObserver<Empty>) {
        val mappedReq = EditUserReq(
            id = request.id,
            name = request.name,
            email = request.email
        )
        userUseCase.editUser(mappedReq);

        responseObserver.onNext(Empty.getDefaultInstance())
        responseObserver.onCompleted()
    }

    override fun deleteUser(request: UserServiceProto.DeleteUserRequest, responseObserver: StreamObserver<Empty>) {
        val mappedReq = DeleteUserReq(
            id = request.id
        )
        userUseCase.deleteUser(mappedReq);

        responseObserver.onNext(Empty.getDefaultInstance())
        responseObserver.onCompleted()
    }
}