package com.example.orcas.data.remote.response

data class MyList(
    val dt: Int,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Int,
    val sys: Sys,
    val dt_txt: String
)