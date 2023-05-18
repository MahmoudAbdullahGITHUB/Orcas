package com.example.orcas.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userId") val userId: Int? = null,
    @SerializedName("userName") val userName: String? = null,
    @SerializedName("userEmail") val userEmail: String? = null,
    @SerializedName("userPhone") val userPhone: String? = null,
    @SerializedName("userLang") val userLang: String? = null,
    @SerializedName("userProfileImage") val userProfileImage: String? = null,
    @SerializedName("discount_access") val discount_access: Boolean? = false,
    @SerializedName("saleperson_type") val saleperson_type: String? = null,
    @SerializedName("max_discount") val max_discount: Int? = null
)