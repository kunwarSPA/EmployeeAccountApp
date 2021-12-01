package com.employee.domain.login.usecase

import com.employee.domain.common.usecase.BaseUseCase
import com.employee.domain.login.entity.request.EmployeeUpdate
import com.employee.domain.login.entity.response.EmployeeData
import com.employee.domain.login.result.APIResult
import io.reactivex.Observable

interface EmployeeDetailUseCase {

    fun getEmployeeDetail(employeeId: Int,  callback: BaseUseCase.Callback<EmployeeData>): Observable<APIResult<EmployeeData>>
    fun getEmployeeDetailFromDb(employeeId: Int,callback: BaseUseCase.Callback<EmployeeData>): EmployeeData
    suspend fun updateEmployeeDetail(employeeUpdate: EmployeeUpdate,userId: Int,hasNetwork: Boolean,callback: BaseUseCase.Callback<EmployeeUpdate>)
    fun updateEmployeeDetailInDb(employeeData: EmployeeData,callback: BaseUseCase.Callback<EmployeeData>)

}