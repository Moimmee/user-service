package com.moimmee.userservice.domain.user.domain.usecase

import com.moimmee.userservice.domain.user.application.dto.req.GetUserReq
import com.moimmee.userservice.domain.user.application.dto.req.RegisterReq
import com.moimmee.userservice.domain.user.application.dto.res.GetUserRes
import com.moimmee.userservice.domain.user.domain.service.UserService
import com.moimmee.userservice.domain.user.infra.enum.UserGender
import com.moimmee.userservice.domain.user.infra.enum.UserRole
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class UserUseCaseTest {

    private val userService: UserService = mock(UserService::class.java)
    private val userUseCase = UserUseCase(userService)

    @Test
    fun `register should call verifyEmailConflict and saveUser`() {
        val req = RegisterReq(name = "진예준", gender = UserGender.MALE, email = "jinyj0217@gmail.com")

        userUseCase.register(req)

        verify(userService).verifyEmailConflict(req.email)
        verify(userService).saveUser(req)
    }

    @Test
    fun `getUser should return user from service`() {
        val req = GetUserReq(id = 1L)
        val expected = GetUserRes(name = "진예준", role = UserRole.ROLE_USER, gender = UserGender.MALE, email = "jinyj0217@gmail.com")

        `when`(userService.findUser(req)).thenReturn(expected)
        val result = userUseCase.getUser(req)

        assertEquals(expected, result)
        verify(userService).findUser(req)
    }

    @Test
    fun `getAllUsers should return list from service`() {
        val expectedList = listOf(
                GetUserRes(name = "진예준", role = UserRole.ROLE_USER, gender = UserGender.MALE, email = "jinyj0217@gmail.com")
        )

        `when`(userService.findAllUsers()).thenReturn(expectedList)
        val result = userUseCase.getAllUsers()

        assertEquals(expectedList, result)
        verify(userService).findAllUsers()
    }
}