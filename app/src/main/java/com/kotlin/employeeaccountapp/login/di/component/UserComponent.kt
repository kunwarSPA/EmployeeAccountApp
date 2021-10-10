package com.kotlin.employeeaccountapp.login.di.component

import com.kotlin.employeeaccountapp.common.di.LoggedInScope
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@LoggedInScope
@DefineComponent(parent = SingletonComponent::class)
interface UserComponent {
    @DefineComponent.Builder
    interface Factory {
        fun create(): UserComponent
    }
}