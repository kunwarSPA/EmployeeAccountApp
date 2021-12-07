package com.employee.data.login.api

import com.employee.data.login.response.EmployeeDataResponse
import com.employee.data.login.response.LoginResponse
import com.employee.domain.login.entity.request.EmployeeLogin
import com.employee.domain.login.entity.request.EmployeeUpdate
import io.reactivex.Single
import retrofit2.http.*

interface Api {

    @POST("/api/login")
     fun login(@Body employeeLogin: EmployeeLogin): Single<LoginResponse>

    @POST("/api/logout")
    fun logout(@Body employeeLogin: String): Single<String>

    @GET("/api/users/{userId}")
    fun getUserDetail(@Path("userId") userId: Int): Single<EmployeeDataResponse>

    @PUT("/api/users/{userId}")
    suspend fun editUserDetail(@Path("userId") userId: Int,@Body employeeUpdate: EmployeeUpdate): EmployeeUpdate
}