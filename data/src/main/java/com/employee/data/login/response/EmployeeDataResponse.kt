package com.employee.data.login.response


data class EmployeeDataResponse(val `data`: Data)


data class Data(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
)