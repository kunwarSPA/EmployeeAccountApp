package com.employee.domain.login.usecase

import com.employee.domain.common.usecase.BaseUseCase
import com.employee.domain.login.entity.response.EmployeeData
import com.employee.domain.login.repository.EmployeeDetailsRepository
import com.employee.domain.login.result.APIResult
import io.reactivex.Observable
import javax.inject.Inject

class EmployeeDetailUseCase   @Inject constructor(private val repository: EmployeeDetailsRepository) {

    fun getEmployeeDetail(employeeId: Int, hasNetwork: Boolean, callback: BaseUseCase.Callback<EmployeeData>): Observable<APIResult<EmployeeData>> {
        return repository.getUserDetail(employeeId).toObservable().map {
            val data = it
           // callback.onSuccess(data)
            APIResult.Success(data) as  APIResult<EmployeeData>

        }
            .onErrorReturn {
            //    callback.onError(it)
                APIResult.Failure(it.localizedMessage)
            }

    }

}