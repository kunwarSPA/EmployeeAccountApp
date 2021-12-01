package com.employee.domain.login.usecase

import com.employee.domain.login.entity.request.EmployeeLogin
import com.employee.domain.login.entity.response.LoginResponse
import com.employee.domain.login.result.APIResult
import io.reactivex.Observable

interface LoginUseCase{
    fun login(employeeLogin: EmployeeLogin, hasNetwork: Boolean): Observable<APIResult<LoginResponse>>
}