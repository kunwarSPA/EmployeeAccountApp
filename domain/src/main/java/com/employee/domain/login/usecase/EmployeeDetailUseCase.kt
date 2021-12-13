package com.employee.domain.login.usecase

import com.employee.domain.common.usecase.Callback
import com.employee.domain.login.entity.request.EmployeeUpdate
import com.employee.domain.login.result.APIResult
import com.employee.domain.model.EmployeeData
import io.reactivex.Observable

interface EmployeeDetailUseCase {

    fun getEmployeeDetail(employeeId: Int,  callback: Callback<EmployeeData>): Observable<APIResult<EmployeeData>>
    fun getEmployeeDetailFromDb(employeeId: Int,callback: Callback<EmployeeData>): EmployeeData
    suspend fun updateEmployeeDetail(employeeUpdate: EmployeeUpdate,userId: Int,hasNetwork: Boolean,callback: Callback<EmployeeUpdate>)
    fun updateEmployeeDetailInDb(employeeData: EmployeeData,callback: Callback<EmployeeData>)
    fun insertEmployeeDetailInDb(employeeData: EmployeeData)
}