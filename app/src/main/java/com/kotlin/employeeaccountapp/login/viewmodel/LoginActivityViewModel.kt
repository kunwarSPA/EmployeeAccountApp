package com.kotlin.employeeaccountapp.login.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.employee.common.di.addTo
import com.employee.domain.login.entity.request.EmployeeLogin
import com.employee.domain.login.entity.response.LoginResponse
import com.employee.domain.login.result.APIResult
import com.employee.domain.login.usecase.LoginUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class LoginActivityViewModel @ViewModelInject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    var loginStatusLiveData = MutableLiveData<String>()
    private val disposables = CompositeDisposable()
    val progressVisible = MutableLiveData<Boolean>()

    fun login(employeeLogin: EmployeeLogin) {
        loginUseCase.login(employeeLogin,true) .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleResult(it) }
            .addTo(disposables)
      //  loginStatusLiveData.value = LoginStatus.Success
    }


    private fun handleResult(result: APIResult<LoginResponse>) {
        when (result) {
            is APIResult.Loading -> progressVisible.value = true
            is APIResult.Success -> {

                loginStatusLiveData.value = result.data.token
            }
            is APIResult.Failure -> {

                loginStatusLiveData.value = result.message
            }
        }
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}

sealed class LoginStatus {
    object Success : LoginStatus()

    object Failure : LoginStatus()
}