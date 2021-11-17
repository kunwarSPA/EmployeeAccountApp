package com.employee.data.login.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.employee.data.login.dao.EmployeeDataDao
import com.employee.domain.login.entity.response.Data

@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val employeeDataDao: EmployeeDataDao

    companion object {
        const val DB_NAME = "employeeDb.db"
    }
}
