package com.employee.data.login.repositoryImpl

import com.employee.data.login.api.Api
import com.employee.domain.login.entity.request.EmployeeLogin
import com.employee.domain.login.entity.response.EmployeeData
import com.employee.domain.login.entity.response.LoginResponse
import com.employee.domain.login.entity.response.UserResponse
import com.employee.domain.login.repository.EmployeeDetailsRepository
import com.employee.domain.login.repository.LoginRepository
import io.reactivex.Single
import javax.inject.Inject

class EmployeeDetailsRepositoryImpl  @Inject constructor(private val api: Api) : EmployeeDetailsRepository {
    override fun getUserDetail(userId: Int): Single<EmployeeData>  {
        return api.getUserDetail(userId)
    }

    override suspend fun editUser(userId: Int): Single<EmployeeData>  {
        return api.editUserDetail(userId)
    }

}