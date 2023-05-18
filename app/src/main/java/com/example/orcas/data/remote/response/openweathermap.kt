package com.example.orcas.data.remote.response

data class openweathermap(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<MyList>,
    val city: City
)