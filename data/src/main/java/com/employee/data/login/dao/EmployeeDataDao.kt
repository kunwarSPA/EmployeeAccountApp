package com.employee.data.login.dao

import androidx.room.*
import com.employee.domain.login.entity.response.Data

@Dao
interface EmployeeDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employeeData: Data): Long

    @Delete
    fun delete(employeeData: Data)

    @Update
    fun update(employeeData: Data)


    @Query("SELECT * FROM EmployeeData where id = :employeeId")
    fun loadEmployee(employeeId: Int): Data
}