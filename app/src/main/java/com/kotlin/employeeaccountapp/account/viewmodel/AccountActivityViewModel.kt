package com.kotlin.employeeaccountapp.account.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.employee.domain.login.repository.UserDataRepository
import com.kotlin.employeeaccountapp.login.di.entryPoint.UserComponentEntryPoint
import com.kotlin.employeeaccountapp.login.di.handler.UserComponentHandler
import dagger.hilt.EntryPoints

class AccountActivityViewModel @ViewModelInject constructor(
    private val userComponentHandler: UserComponentHandler
) : ViewModel() {

    private var userDataRepository: UserDataRepository

    init {
        val entryPoint = EntryPoints.get(userComponentHandler, UserComponentEntryPoint::class.java)
        userDataRepository = entryPoint.getUserDataRepository()
    }

    fun logout() {
        userComponentHandler.logout()
    }

    fun refreshNotification() {
        userDataRepository.refreshNotification()
        Log.d("REPOSITORY", userDataRepository.toString())
    }
}