package com.moimmee.userservice.domain.core.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
open class BaseEntity(

        @CreatedDate
        @Column(nullable = false)
        var createdDate: LocalDateTime = LocalDateTime.now(),

        @LastModifiedDate
        @Column(nullable = false)
        var updatedDate: LocalDateTime = LocalDateTime.now(),
) {
}