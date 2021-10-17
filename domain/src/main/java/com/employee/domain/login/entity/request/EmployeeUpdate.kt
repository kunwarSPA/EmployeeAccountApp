package com.employee.domain.login.entity.request

import com.google.gson.annotations.SerializedName

data class EmployeeUpdate(@SerializedName("name")val name: String, @SerializedName("job")val job: String)