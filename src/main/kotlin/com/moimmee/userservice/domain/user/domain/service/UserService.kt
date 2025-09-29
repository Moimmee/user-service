package com.moimmee.userservice.domain.user.domain.service

import com.moimmee.userservice.domain.user.application.dto.req.GetUserReq
import com.moimmee.userservice.domain.user.application.dto.req.RegisterReq
import com.moimmee.userservice.domain.user.application.dto.res.GetUserRes
import com.moimmee.userservice.domain.user.exception.EmailAlreadyUsedException
import com.moimmee.userservice.domain.user.exception.UserNotFoundException
import com.moimmee.userservice.domain.user.persistence.repo.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepo: UserRepository,
) {

    fun saveUser(req: RegisterReq) {
        userRepo.save(RegisterReq.toEntity(req))
    }

    fun verifyEmailConflict(desiredEmail: String) {
        val isConflicted = userRepo.findAllWithEmailPair()
                .stream()
                .anyMatch { it -> it.getEmail().equals(desiredEmail) }
        if (isConflicted) throw EmailAlreadyUsedException();
    }

    fun findUser(req: GetUserReq): GetUserRes {
        return GetUserRes.of(userRepo.findById(req.id).orElseThrow(::UserNotFoundException));
    }

    fun findAllUsers(): List<GetUserRes> {
        return userRepo.findAll().map { it -> GetUserRes.of(it) }
    }
}