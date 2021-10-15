package com.kotlin.employeeaccountapp.splash.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.employee.common.utils.PreferencesData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel  @ViewModelInject constructor(
    preferences: PreferencesData
) : ViewModel() {

    var loginStatusLiveData = MutableLiveData<Boolean>()


    init {
        viewModelScope.launch {
            delay(2000)
            loginStatusLiveData.value =preferences.isLoggedIn()
        }
    }
}