package com.moimmee.userservice.domain.user.persistence.repo

import com.moimmee.userservice.domain.user.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {

    @Query("SELECT u.id FROM UserEntity u")
    fun findAllWithEmailPair(): List<UserEmailMapping>

}

interface UserEmailMapping {
    fun getId(): Long
    fun getEmail(): String
}