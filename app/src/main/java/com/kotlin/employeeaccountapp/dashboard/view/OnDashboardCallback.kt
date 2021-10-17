package com.kotlin.employeeaccountapp.dashboard.view

import android.widget.ImageView

interface OnDashboardCallback {

    fun navigateToEditPage()

    fun navigateToDashboardPage()

    fun navigateToLoginPage()
}