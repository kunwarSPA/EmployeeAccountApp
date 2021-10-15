package com.employee.common.di

import android.content.Context
import android.content.SharedPreferences


@dagger.Module
@dagger.hilt.InstallIn(dagger.hilt.components.SingletonComponent::class)
class AppModule {
    @dagger.Provides
    @javax.inject.Singleton
    fun getLocalStorage(@dagger.hilt.android.qualifiers.ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("Account", Context.MODE_PRIVATE)
    }

}