package com.employee.data.login.useCaseImpl

import com.employee.domain.common.usecase.Callback
import com.employee.domain.login.entity.request.EmployeeUpdate
import com.employee.domain.login.repository.EmployeeDetailsRepository
import com.employee.domain.login.result.APIResult
import com.employee.domain.login.usecase.EmployeeDetailUseCase
import com.employee.domain.model.EmployeeData
import io.reactivex.Observable
import javax.inject.Inject

class EmployeeDetailUseCaseImpl   @Inject constructor(private val repository: EmployeeDetailsRepository) : EmployeeDetailUseCase {

    override fun getEmployeeDetail(employeeId: Int, callback: Callback<EmployeeData>): Observable<APIResult<EmployeeData>> {
        return repository.getUserDetail(employeeId).toObservable().map{
            val data = it
            callback.onSuccess(data)
            APIResult.Success(data) as  APIResult<EmployeeData>

        }
            .onErrorReturn {
                callback.onError(it)
                APIResult.Failure(it.localizedMessage)
            }

    }

    override fun getEmployeeDetailFromDb(
        employeeId: Int,
        callback: Callback<EmployeeData>
    ): EmployeeData {
        callback.onSuccess(repository.getUserDetailFromLocalDb(employeeId))
        return repository.getUserDetailFromLocalDb(employeeId)

    }

    override suspend fun updateEmployeeDetail(
        employeeUpdate: EmployeeUpdate,
        userId: Int,
        hasNetwork: Boolean,
        callback: Callback<EmployeeUpdate>
    ) {
        try {
            val result = repository.editUser(userId, employeeUpdate)
            callback.onSuccess(result)
        } catch (e: Exception) {
            callback.onError(e)
        }

    }

    override fun updateEmployeeDetailInDb(
        employeeData: EmployeeData,
        callback: Callback<EmployeeData>
    ) {
        repository.updateEmployeeDetailInDb(employeeData)

    }

    override fun insertEmployeeDetailInDb(
        employeeData: EmployeeData,
    ) {
        repository.insertEmployeeDataInDb(employeeData)
    }

}