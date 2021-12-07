package com.employee.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "EmployeeData")
data class EmployeeData (
    val avatar: String,
    val email: String,
    val first_name: String,
    @PrimaryKey val id: Int,
    val last_name: String
)