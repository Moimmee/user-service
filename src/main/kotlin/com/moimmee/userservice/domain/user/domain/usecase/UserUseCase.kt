package com.moimmee.userservice.domain.user.domain.usecase

import com.moimmee.userservice.domain.user.application.dto.req.DeleteUserReq
import com.moimmee.userservice.domain.user.application.dto.req.EditUserReq
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
        println(req.gender)
        println(req.email)
        userService.verifyEmailConflict(req.email);
        userService.saveUser(req)
    }

    fun getUser(req: GetUserReq): GetUserRes {
        return userService.findUser(req)
    }

    fun getAllUsers(): List<GetUserRes> {
        return userService.findAllUsers();
    }

    fun editUser(req: EditUserReq) {
        userService.editUser(req)
    }

    fun deleteUser(req: DeleteUserReq) {
        userService.deleteUser(req)
    }
}