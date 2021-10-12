package com.employee.domain.login.repository

import com.employee.domain.login.entity.request.EmployeeLogin
import com.employee.domain.login.entity.response.LoginResponse
import io.reactivex.Single

interface LoginRepository {
    fun login(employeeLogin: EmployeeLogin): Single<LoginResponse>
    fun logout(id: String) : Single<String>
}