package com.employee.domain.login.entity.response

import androidx.room.Entity
import androidx.room.PrimaryKey


data class EmployeeData(val `data`: Data)

@Entity(tableName = "EmployeeData")
data class Data(
    val avatar: String,
    val email: String,
    val first_name: String,
    @PrimaryKey val id: Int,
    val last_name: String
)