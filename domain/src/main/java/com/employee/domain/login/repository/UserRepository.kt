package com.employee.domain.login.repository

import com.employee.domain.login.entity.response.UserResponse
import com.employee.domain.login.entity.response.UsersResponse

interface UserRepository {
    suspend fun getUsers(page: Int): UsersResponse

    suspend fun getUser(userId: Int): UserResponse
}