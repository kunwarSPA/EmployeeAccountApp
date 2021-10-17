package com.employee.domain.common.usecase

import com.employee.domain.login.entity.request.EmployeeUpdate
import io.reactivex.Single

interface BaseUseCase<in P, out R> {
    interface Callback<in R> {
        fun onSuccess(result: R)
        fun onError(throwable: Throwable)
    }

    suspend fun execute(params: P, callback: Callback<R>)
}