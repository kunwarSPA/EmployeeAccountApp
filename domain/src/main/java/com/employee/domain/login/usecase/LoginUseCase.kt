package com.employee.domain.login.usecase

import com.employee.domain.login.entity.request.EmployeeLogin
import com.employee.domain.login.entity.response.LoginResponse
import com.employee.domain.login.repository.LoginRepository
import com.employee.domain.login.result.APIResult
import io.reactivex.Observable
import javax.inject.Inject

class LoginUseCase  @Inject constructor(private val repository: LoginRepository) {

    fun login(employeeLogin: EmployeeLogin, hasNetwork: Boolean): Observable<APIResult<LoginResponse>> {
        return repository.login(employeeLogin).toObservable().map {
            val data = it

            APIResult.Success(data) as  APIResult<LoginResponse>
        }
            .onErrorReturn {  APIResult.Failure(it.localizedMessage) }
    }
}
