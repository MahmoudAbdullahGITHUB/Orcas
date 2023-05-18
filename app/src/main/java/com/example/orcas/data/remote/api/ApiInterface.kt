package com.example.orcas.data.remote.api

import com.example.orcas.data.remote.response.openweathermap
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    //    data/2.5/forecast?lat=30.033333&lon=31.233334&appid=af7e6a456fcef225e303ffddd3ad32b2
    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): openweathermap


}