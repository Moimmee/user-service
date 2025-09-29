package com.moimmee.userservice.domain.user.domain.usecase

import com.moimmee.userservice.domain.user.application.dto.req.GetUserReq
import com.moimmee.userservice.domain.user.application.dto.req.RegisterReq
import com.moimmee.userservice.domain.user.application.dto.res.GetUserRes
import com.moimmee.userservice.domain.user.domain.service.UserService
import org.springframework.stereotype.Component

@Component
class UserUseCase(
        val userService: UserService
) {

    fun register(req: RegisterReq) {
        userService.verifyEmailConflict(req.email);
        userService.saveUser(req)
    }

    fun getUser(req: GetUserReq): GetUserRes {
        return userService.findUser(req)
    }

    fun getAllUsers(): List<GetUserRes> {
        return userService.findAllUsers();
    }
}