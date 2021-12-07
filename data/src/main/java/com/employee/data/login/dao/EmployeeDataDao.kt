package com.employee.data.login.dao

import androidx.room.*
import com.employee.domain.model.EmployeeData

@Dao
interface EmployeeDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employeeData: EmployeeData): Long

    @Delete
    fun delete(employeeData: EmployeeData)

    @Update
    fun update(employeeData: EmployeeData)


    @Query("SELECT * FROM EmployeeData where id = :employeeId")
    fun loadEmployee(employeeId: Int): EmployeeData
}