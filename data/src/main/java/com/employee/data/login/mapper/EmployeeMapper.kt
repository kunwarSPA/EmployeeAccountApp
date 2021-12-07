package com.employee.data.login.mapper

import com.employee.data.login.response.EmployeeDataResponse
import com.employee.domain.model.EmployeeData
import javax.inject.Inject

class EmployeeMapper  @Inject constructor() {

    fun map(responseList: List<EmployeeDataResponse>): List<EmployeeData> {
        return responseList.map { (map(it)) }
    }

    fun map(response: EmployeeDataResponse): EmployeeData {
        return EmployeeData(
            id = response.data.id,
            avatar = response.data.avatar,
            first_name = response.data.first_name,
            last_name = response.data.last_name,
            email = response.data.email,

        )
    }
}
