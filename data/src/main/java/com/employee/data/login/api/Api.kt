package com.employee.data.login.api

import com.employee.domain.login.entity.request.EmployeeLogin
import com.employee.domain.login.entity.response.*
import io.reactivex.Single
import retrofit2.http.*

interface Api {
    @GET("/api/users")
    suspend fun getUsers(@Query("page") page: Int): UsersResponse

    @GET("/api/users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): UserResponse

    @POST("/api/login")
     fun login(@Body employeeLogin: EmployeeLogin): Single<LoginResponse>

    @POST("/api/login")
    fun logout(@Body employeeLogin: String): Single<String>

    @GET("/api/users/{userId}")
    fun getUserDetail(@Path("userId") userId: Int): Single<EmployeeData>

    @POST("/api/users/{}")
    fun editUserDetail(@Body userId: Int): Single<EmployeeData>
}