package com.moimmee.userservice.domain.user.domain.service

import com.moimmee.userservice.domain.user.application.dto.req.DeleteUserReq
import com.moimmee.userservice.domain.user.application.dto.req.EditUserReq
import com.moimmee.userservice.domain.user.application.dto.req.GetUserReq
import com.moimmee.userservice.domain.user.application.dto.req.RegisterReq
import com.moimmee.userservice.domain.user.application.dto.res.GetUserRes
import com.moimmee.userservice.domain.user.exception.EmailAlreadyUsedException
import com.moimmee.userservice.domain.user.exception.UserNotFoundException
import com.moimmee.userservice.domain.user.persistence.repo.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepo: UserRepository,
) {

    fun saveUser(req: RegisterReq) {
        println(req.gender)
        println(req.email)
        userRepo.save(RegisterReq.toEntity(req))
    }

    fun verifyEmailConflict(desiredEmail: String) {
        if (userRepo.existsByEmail(desiredEmail)) {
            throw EmailAlreadyUsedException();
        }
    }

    fun findUser(req: GetUserReq): GetUserRes {
        return GetUserRes.of(userRepo.findById(req.id).orElseThrow(::UserNotFoundException));
    }

    fun findAllUsers(): List<GetUserRes> {
        return userRepo.findAll().map { it -> GetUserRes.of(it) }
    }

    fun editUser(req: EditUserReq) {
        val user = userRepo.findById(req.id).orElseThrow(::UserNotFoundException)
        user.update(req.name, req.email);
        userRepo.save(user)
    }

    fun deleteUser(req: DeleteUserReq) {
        try {
            userRepo.deleteById(req.id)
        } catch (e: EmptyResultDataAccessException) {
            throw UserNotFoundException();
        }
    }
}