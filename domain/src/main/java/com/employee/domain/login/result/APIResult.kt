package com.employee.domain.login.result


sealed class APIResult<out R> {
    object Loading: APIResult<Nothing>()

    data class Success<out R>(val data: R): APIResult<R>()

    data class Failure(val message: String): APIResult<Nothing>()
}