package com.moimmee.userservice.global.jwt.util

import com.moimmee.userservice.global.jwt.config.JWTProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JWTUtil(
    private val jwtProperties: JWTProperties
) {

    companion object {
        private const val TOKEN_TYPE_ACCESS: String = "access"
        private const val TOKEN_TYPE_REFRESH: String = "refresh"
    }

    private val secretKey: SecretKey = Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray())

    fun createAccessToken(username: String, role: String): String {
        return createToken(username, role,
            TOKEN_TYPE_ACCESS,
            jwtProperties.expAccess
        )
    }

    fun createRefreshToken(username: String, role: String): String {
        return createToken(username, role,
            TOKEN_TYPE_REFRESH,
            jwtProperties.expRefresh
        )
    }

    fun createToken(username: String, role: String, type: String, expirationMillis: Long): String {
        return Jwts.builder()
            .id(UUID.randomUUID().toString())
            .claim("type", type)
            .claim("username", username)
            .claim("role", role)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expirationMillis))
            .signWith(secretKey)
            .compact()
    }

}