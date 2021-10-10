package com.employee.domain.login.entity.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val `data`: UsersResponse.User,
    @SerializedName("support")
    val support: Support
) {
    data class Support(
        @SerializedName("text")
        val text: String,
        @SerializedName("url")
        val url: String
    )
}