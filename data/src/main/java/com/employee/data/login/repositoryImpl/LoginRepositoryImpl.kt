package com.employee.data.login.repositoryImpl

import com.employee.data.login.api.Api
import com.employee.domain.login.entity.request.EmployeeLogin
import com.employee.domain.login.entity.response.LoginResponse
import com.employee.domain.login.repository.LoginRepository
import io.reactivex.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val api: Api) : LoginRepository {
    override  fun login(employeeLogin: EmployeeLogin): Single<LoginResponse> {
        return api.login(employeeLogin)
    }

    override  fun logout(id: String): Single<String> {
        return api.logout(id)
    }

}