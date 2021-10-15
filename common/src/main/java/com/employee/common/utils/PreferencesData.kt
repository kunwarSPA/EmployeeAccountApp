package com.employee.common.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesData @Inject constructor(){
    private val LOGGED_IN = "token"

    @Inject
    lateinit var prefs : SharedPreferences

    fun isLoggedIn(): Boolean {
        return !prefs.getString(LOGGED_IN, "").isNullOrEmpty()
    }
    fun setLoggedIn(loggedIn: String) {
        prefs.edit().putString(LOGGED_IN, loggedIn).apply()
    }
}