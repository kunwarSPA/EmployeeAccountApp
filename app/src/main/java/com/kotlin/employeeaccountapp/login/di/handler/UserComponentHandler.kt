package com.kotlin.employeeaccountapp.login.di.handler

interface UserComponentHandler {
    fun login(username: String, password: String)

    fun logout()

    fun isLoggedIn(): Boolean
}