package com.employee.data.login.repositoryImpl

import com.employee.data.login.api.Api
import com.employee.domain.login.entity.response.UserResponse
import com.employee.domain.login.entity.response.UsersResponse
import com.employee.domain.login.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: Api): UserRepository {
    override suspend fun getUsers(page: Int): UsersResponse {
        return api.getUsers(page)
    }

    override suspend fun getUser(userId: Int): UserResponse {
        return api.getUser(userId)
    }
}