package com.example.orcas.util

sealed class Resource<T>(data : T?,) {

    data class Success<S>(val data: S?) : Resource<S>(data)
    data class Loading<L>(val message: String,  val data: L?) : Resource<L>(data)
    data class Error<F>(val data: F?) : Resource<F>(data)


}


//sealed class Resource<T>(
//    val data: T? = null,
//    val message: String? = null
//) {
//
//    class Success<T>(data: T,message: String) : Resource<T>(data,message)
//    class Error<T>(message: String) : Resource<T>(message = message)
//    class Loading<T> : Resource<T>()
//}