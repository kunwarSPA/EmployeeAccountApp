package com.employee.data.login.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.employee.data.login.dao.EmployeeDataDao
import com.employee.domain.model.EmployeeData

@Database(entities = [EmployeeData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val employeeDataDao: EmployeeDataDao

    companion object {
        const val DB_NAME = "employeeDb.db"
    }
}
