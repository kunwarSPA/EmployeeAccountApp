package com.employee.domain.login.usecase

import com.employee.domain.common.usecase.BaseUseCase
import com.employee.domain.login.entity.response.UsersResponse
import com.employee.domain.login.repository.UserRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val userRepository: UserRepository) :
    BaseUseCase<Int, UsersResponse> {
    override suspend fun execute(params: Int, callback: BaseUseCase.Callback<UsersResponse>) {
        try {
            val result = userRepository.getUsers(params)
            callback.onSuccess(result)
        } catch (e: Exception) {
            callback.onError(e)
        }
    }
}