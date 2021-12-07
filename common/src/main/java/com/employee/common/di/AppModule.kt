package com.employee.common.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.employee.data.login.database.AppDatabase
import dagger.Provides
import javax.inject.Singleton


@dagger.Module
@dagger.hilt.InstallIn(dagger.hilt.components.SingletonComponent::class)
class AppModule {
    @dagger.Provides
    @javax.inject.Singleton
    fun getLocalStorage(@dagger.hilt.android.qualifiers.ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("Account", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }




}