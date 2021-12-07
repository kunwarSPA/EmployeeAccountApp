package com.employee.data.login.repositoryImpl

import com.employee.data.login.api.Api
import com.employee.data.login.database.AppDatabase
import com.employee.data.login.mapper.EmployeeMapper
import com.employee.domain.login.entity.request.EmployeeUpdate
import com.employee.domain.login.repository.EmployeeDetailsRepository
import com.employee.domain.model.EmployeeData
import io.reactivex.Single
import javax.inject.Inject

class EmployeeDetailsRepositoryImpl @Inject constructor(
    private val api: Api,
    private val database: AppDatabase,
    private val mapper : EmployeeMapper
) : EmployeeDetailsRepository {
    override fun getUserDetail(userId: Int): Single<EmployeeData> {
        return api.getUserDetail(userId).map { mapper.map(it) }
    }

    override suspend fun editUser(userId: Int, employeeUpdate: EmployeeUpdate): EmployeeUpdate {
        return api.editUserDetail(userId, employeeUpdate)
    }

    override fun getUserDetailFromLocalDb(userId: Int): EmployeeData {
        return database.employeeDataDao.loadEmployee(userId)
    }

    override fun updateEmployeeData(employeeData: EmployeeData): Long {
        return database.employeeDataDao.insert(employeeData)
    }

    override fun updateEmployeeDetailInDb(employeeData: EmployeeData) {
        database.employeeDataDao.update(employeeData)
    }
}