package com.employee.common.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesData @Inject constructor(){
    private val LOGGED_IN = "login"

    @Inject
    lateinit var prefs : SharedPreferences

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(LOGGED_IN, false)
    }
    fun setLoggedIn(loggedIn: Boolean) {
        prefs.edit().putBoolean(LOGGED_IN, loggedIn).apply()
    }
}