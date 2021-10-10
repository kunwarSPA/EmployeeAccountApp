package com.employee.domain.login.repository

interface UserDataRepository {
    var unreadNotification: Int

    fun refreshNotification(): Int
}