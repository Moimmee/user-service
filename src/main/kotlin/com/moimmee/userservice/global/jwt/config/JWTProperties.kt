package com.moimmee.userservice.global.jwt.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JWTProperties(
    val secret: String,
    val expAccess: Long,
    val expRefresh: Long
)