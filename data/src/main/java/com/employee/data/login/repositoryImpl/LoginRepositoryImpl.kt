package com.employee.data.login.repositoryImpl

import com.employee.data.login.api.Api
import com.employee.data.login.mapper.LoginMapper
import com.employee.domain.login.entity.request.EmployeeLogin
import com.employee.domain.login.repository.LoginRepository
import com.employee.domain.model.LoginData
import io.reactivex.Single

class LoginRepositoryImpl constructor(private val api: Api,private  val mapper: LoginMapper) : LoginRepository {
    override  fun login(employeeLogin: EmployeeLogin): Single<LoginData> {
        return api.login(employeeLogin).map { mapper.map(it) }
    }

    override  fun logout(id: String): Single<String> {
        return api.logout(id)
    }

}