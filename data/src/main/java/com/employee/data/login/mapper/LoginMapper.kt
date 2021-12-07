package com.employee.data.login.mapper

import com.employee.data.login.response.LoginResponse
import com.employee.domain.model.LoginData
import javax.inject.Inject

class LoginMapper @Inject constructor() {


    fun map(response: LoginResponse): LoginData {
        return LoginData(
            token = response.token,
            error = response.error
            )
    }
}
