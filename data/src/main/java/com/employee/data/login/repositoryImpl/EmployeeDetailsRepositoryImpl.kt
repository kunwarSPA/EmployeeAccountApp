package com.employee.data.login.repositoryImpl

import com.employee.data.login.api.Api
import com.employee.domain.login.entity.request.EmployeeUpdate
import com.employee.domain.login.entity.response.EmployeeData
import com.employee.domain.login.repository.EmployeeDetailsRepository
import io.reactivex.Single
import javax.inject.Inject

class EmployeeDetailsRepositoryImpl  @Inject constructor(private val api: Api) : EmployeeDetailsRepository {
    override fun getUserDetail(userId: Int): Single<EmployeeData>  {
        return api.getUserDetail(userId)
    }

    override suspend fun editUser(userId : Int,employeeUpdate: EmployeeUpdate): EmployeeUpdate {
        return api.editUserDetail(userId,employeeUpdate)
    }

}