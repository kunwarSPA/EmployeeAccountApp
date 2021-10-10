package com.employee.domain.login.usecase

import com.employee.domain.common.usecase.BaseUseCase
import com.employee.domain.login.entity.response.UserResponse
import com.employee.domain.login.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) :
    BaseUseCase<Int, UserResponse> {
    override suspend fun execute(params: Int, callback: BaseUseCase.Callback<UserResponse>) {
        try {
            val result = userRepository.getUser(params)
            callback.onSuccess(result)
        } catch (e: Exception) {
            callback.onError(e)
        }
    }
}