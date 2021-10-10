package com.employee.data.login.api

import com.employee.domain.login.entity.response.UserResponse
import com.employee.domain.login.entity.response.UsersResponse
import com.employee.domain.login.entity.request.EmployeeLogin
import io.reactivex.Single
import retrofit2.http.*

interface Api {
    @GET("/api/users")
    suspend fun getUsers(@Query("page") page: Int): UsersResponse

    @GET("/api/users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): UserResponse

    @POST("/api/login")
    suspend fun login(@Body employeeLogin: EmployeeLogin): Single<String>
}