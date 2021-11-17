package com.kotlin.employeeaccountapp.dashboard.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.employee.common.di.addTo
import com.employee.domain.common.usecase.BaseUseCase
import com.employee.domain.login.entity.request.EmployeeUpdate
import com.employee.domain.login.entity.response.EmployeeData
import com.employee.domain.login.result.APIResult
import com.employee.domain.login.usecase.EmployeeDetailUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DashBoardActivityViewModel @ViewModelInject constructor(
    private val employeeDetailUseCase: EmployeeDetailUseCase
) : ViewModel() {
    var userDataLiveData = MutableLiveData<APIResult<EmployeeData>>()
    var updateUserDataLiveData = MutableLiveData<APIResult<EmployeeUpdate>>()
    private val disposables = CompositeDisposable()
    val progressVisible = MutableLiveData<Boolean>()


    fun getEmployeeDetail(employeeLogin: Int, hasNetwork: Boolean) {
        if (hasNetwork) {
            employeeDetailUseCase.getEmployeeDetail(
                employeeLogin,
                allUsersUseCaseCallback
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { allUsersUseCaseCallback }
                .addTo(disposables)
        } else {
            employeeDetailUseCase.getEmployeeDetailFromDb(employeeLogin, allUsersUseCaseCallback)
        }
        //  loginStatusLiveData.value = LoginStatus.Success
    }

    fun updateEmployeeDetail(userId: Int, employeeData: EmployeeUpdate) {
        viewModelScope.launch {
            employeeDetailUseCase.updateEmployeeDetail(
                employeeData,
                userId,
                true,
                updateUserUseCaseCallback
            )
        }
    }

    /* fun getUser(id: Int) {
         viewModelScope.launch {
             getUserUseCase.execute(id, userUseCaseCallback)
         }
     }*/

    private val allUsersUseCaseCallback = object : BaseUseCase.Callback<EmployeeData> {
        override fun onSuccess(result: EmployeeData) {
            userDataLiveData.postValue(APIResult.Success(data = result))
        }

        override fun onError(throwable: Throwable) {
            userDataLiveData.postValue(APIResult.Failure(throwable.toString()))
        }
    }

    private val updateUserUseCaseCallback = object : BaseUseCase.Callback<EmployeeUpdate> {
        override fun onSuccess(result: EmployeeUpdate) {
            // employeeDetailUseCase.updateEmployeeDetailInDb()
            updateUserDataLiveData.value = APIResult.Success(data =result)
        }

        override fun onError(throwable: Throwable) {
            updateUserDataLiveData.value = APIResult.Failure(throwable.toString())
        }
    }


    private fun handleResultUpdateUser(result: APIResult<EmployeeUpdate>) {
        when (result) {
            is APIResult.Loading -> progressVisible.value = true
            is APIResult.Success -> {

                updateUserDataLiveData.value = result
            }
            is APIResult.Failure -> {

                updateUserDataLiveData.value = result
            }
        }
    }

    private fun handleResultAllUser(result: APIResult<EmployeeData>) {
        when (result) {
            is APIResult.Loading -> progressVisible.postValue(true)
            is APIResult.Success -> {

                userDataLiveData.postValue(result)
            }
            is APIResult.Failure -> {

                userDataLiveData.postValue(result)
            }
        }
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}