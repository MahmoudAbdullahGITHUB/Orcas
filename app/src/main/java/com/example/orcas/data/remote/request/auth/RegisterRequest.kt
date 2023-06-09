package com.example.orcas.data.remote.request.auth

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("password")
    val password: String? = null
)