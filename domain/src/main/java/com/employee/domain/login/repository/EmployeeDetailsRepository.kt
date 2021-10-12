package com.employee.domain.login.repository

import com.employee.domain.login.entity.response.EmployeeData
import com.employee.domain.login.entity.response.UserResponse
import io.reactivex.Single

interface EmployeeDetailsRepository {
    fun getUserDetail(userId: Int): Single<EmployeeData>
    suspend  fun editUser(userId: Int): Single<EmployeeData>
}