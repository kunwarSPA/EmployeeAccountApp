package com.employee.data.login.api

import com.employee.domain.login.entity.request.EmployeeLogin
import com.employee.domain.login.entity.response.EmployeeData
import com.employee.domain.login.entity.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("/api/login")
     fun login(@Body employeeLogin: EmployeeLogin): Single<LoginResponse>

    @POST("/api/login")
    fun logout(@Body employeeLogin: String): Single<String>

    @GET("/api/users/{userId}")
    fun getUserDetail(@Path("userId") userId: Int): Single<EmployeeData>

    @POST("/api/users/{}")
    fun editUserDetail(@Body userId: Int): Single<EmployeeData>
}