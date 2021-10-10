package com.kotlin.employeeaccountapp.login.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.employeeaccountapp.login.di.handler.UserComponentHandler

class LoginActivityViewModel @ViewModelInject constructor(
    private val userComponentHandler: UserComponentHandler
) : ViewModel() {
    var loginStatusLiveData = MutableLiveData<LoginStatus>()

    fun login(username: String, password: String) {
        userComponentHandler.login(username, password)
        loginStatusLiveData.value = LoginStatus.Success
    }
}

sealed class LoginStatus {
    object Success : LoginStatus()

    object Failure : LoginStatus()
}