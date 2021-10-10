package com.kotlin.employeeaccountapp.login.di.entryPoint

import com.employee.domain.login.repository.UserDataRepository
import com.kotlin.employeeaccountapp.login.di.component.UserComponent
import com.kotlin.employeeaccountapp.login.entity.LoggedInUser
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@InstallIn(UserComponent::class)
interface UserComponentEntryPoint {
    fun getLoggedInUser(): LoggedInUser
    fun getUserDataRepository(): UserDataRepository
}