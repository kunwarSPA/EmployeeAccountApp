package com.kotlin.employeeaccountapp.login.di.module

import android.content.SharedPreferences
import com.employee.data.login.repositoryImpl.UserDataRepositoryImpl
import com.employee.domain.login.repository.UserDataRepository
import com.kotlin.employeeaccountapp.common.di.LoggedInScope
import com.kotlin.employeeaccountapp.login.di.component.UserComponent
import com.kotlin.employeeaccountapp.login.entity.LoggedInUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(UserComponent::class)
class UserModule {
    @Provides
    @LoggedInScope
    fun providesUserData(preferences: SharedPreferences): LoggedInUser {
        return LoggedInUser(preferences.getString("username", "Kunwar")!!)
    }

    @Provides
    @LoggedInScope
    fun providesUserDataRepository(userDataRepository: UserDataRepositoryImpl): UserDataRepository {
        return userDataRepository
    }
}