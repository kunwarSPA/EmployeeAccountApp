package com.kotlin.employeeaccountapp.common.di

import android.content.Context
import android.content.SharedPreferences
import com.kotlin.employeeaccountapp.login.di.handler.UserComponentHandler
import com.kotlin.employeeaccountapp.login.di.handler.UserComponentHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun getLocalStorage(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("Account", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun getUserComponentHandler(userComponentHandler: UserComponentHandlerImpl): UserComponentHandler {
        return userComponentHandler
    }
}