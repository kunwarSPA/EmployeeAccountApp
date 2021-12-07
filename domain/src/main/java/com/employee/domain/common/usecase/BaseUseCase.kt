package com.employee.domain.common.usecase


    interface Callback<in R> {
        fun onSuccess(result: R)
        fun onError(throwable: Throwable)
    }
