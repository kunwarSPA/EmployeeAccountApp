package com.employee.data.login.useCaseImpl

import com.employee.domain.login.entity.request.EmployeeLogin
import com.employee.domain.login.repository.LoginRepository
import com.employee.domain.login.result.APIResult
import com.employee.domain.login.usecase.LoginUseCase
import com.employee.domain.model.LoginData
import io.reactivex.Observable
import javax.inject.Inject

class LoginUseCaseImpl  @Inject constructor(private val repository: LoginRepository)  :
    LoginUseCase {

    override fun login(employeeLogin: EmployeeLogin, hasNetwork: Boolean): Observable<APIResult<LoginData>> {
        return repository.login(employeeLogin).toObservable().map {
            val data = it

            APIResult.Success(data) as  APIResult<LoginData>
        }
            .onErrorReturn {  APIResult.Failure(it.localizedMessage) }

    }


}
