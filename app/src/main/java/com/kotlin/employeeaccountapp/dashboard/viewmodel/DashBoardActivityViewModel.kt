package com.kotlin.employeeaccountapp.dashboard.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.employee.common.di.addTo
import com.employee.domain.common.usecase.BaseUseCase
import com.employee.domain.login.entity.response.EmployeeData
import com.employee.domain.login.result.APIResult
import com.employee.domain.login.usecase.EmployeeDetailUseCase

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DashBoardActivityViewModel @ViewModelInject constructor(
    private val employeeDetailUseCase: EmployeeDetailUseCase
) : ViewModel() {
    var userDataLiveData = MutableLiveData<APIResult<EmployeeData>>()
    private val disposables = CompositeDisposable()
    val progressVisible = MutableLiveData<Boolean>()


    fun getEmployeeDetail(employeeLogin: Int) {
        employeeDetailUseCase.getEmployeeDetail(employeeLogin,true,allUsersUseCaseCallback)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleResult(it) }
            .addTo(disposables)
        //  loginStatusLiveData.value = LoginStatus.Success
    }

    private val allUsersUseCaseCallback = object : BaseUseCase.Callback<EmployeeData> {
        override fun onSuccess(result: EmployeeData) {
            userDataLiveData.value = APIResult.Success(data =result)
        }

        override fun onError(throwable: Throwable) {
            userDataLiveData.value = APIResult.Failure(throwable.toString())
        }
    }



    private fun handleResult(result: APIResult<EmployeeData>) {
        when (result) {
            is APIResult.Loading -> progressVisible.value = true
            is APIResult.Success -> {

                userDataLiveData.value = result
            }
            is APIResult.Failure -> {

                userDataLiveData.value = result
            }
        }
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}