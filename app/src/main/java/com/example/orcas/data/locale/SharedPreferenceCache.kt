package com.example.orcas.data.locale

import android.content.SharedPreferences

import androidx.core.content.edit
import com.example.orcas.util.Language
import com.google.gson.Gson
import com.example.orcas.data.remote.request.auth.LoginRequest
import com.example.orcas.data.remote.response.auth.User
 import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.log

@Singleton
class SharedPreferenceCache @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveStartWork(start: Boolean) {
        sharedPreferences.edit { putBoolean("start_work", start) }
    }

    fun getStartWork(): Boolean? = sharedPreferences.getBoolean("start_work", false)

    fun saveLanguage(lang: String) {
        sharedPreferences.edit { putString("lang", lang) }
    }

    fun getLanguage(): String? = sharedPreferences.getString("lang", Language.ENGLISH.lang)

    fun saveAuthToken(token: String?) {
        sharedPreferences.edit { putString("token", token) }
    }

    fun getAuthToken(): String? = sharedPreferences.getString("token", "")

    fun saveUser(user: User?) {
        val userGson = Gson().toJson(user)
        sharedPreferences.edit { putString("user", userGson) }

    }

    fun getUser(): String? = sharedPreferences.getString("user", null)


    fun saveLoginRequest(loginRequest: LoginRequest?) {
        val loginGson = Gson().toJson(loginRequest)
        sharedPreferences.edit { putString("login", loginGson) }

    }

    fun getLoginRequest(): String? = sharedPreferences.getString("login", null)



}
