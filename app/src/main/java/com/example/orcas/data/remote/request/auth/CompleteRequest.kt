package com.example.orcas.data.remote.request.auth

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class CompleteRequest(
    @SerializedName("full_name")
    val fullName: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("picture_url")
    val picture_url: String? = null,

    @SerializedName("gender")
    val gender: String? = null,

    @SerializedName("birth_date")
    val birth_date: String? = null,

    @SerializedName("identification_card_url")
    val identification_card_url: String? = null,

    @SerializedName("licence_card_url")
    val licence_card_url: String? = null,

    @SerializedName("card_number")
    val card_number: String? = null,

    @SerializedName("expiration_date")
    val expiration_date: String? = null
): Parcelable