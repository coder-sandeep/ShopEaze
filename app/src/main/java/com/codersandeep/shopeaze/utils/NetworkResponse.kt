package com.codersandeep.shopeaze.utils

sealed class NetworkResponse<T>(val data : T? = null, val message : String? = null) {
    class Loading<T> : NetworkResponse<T>()
    class Success<T>(data: T?) : NetworkResponse<T>(data)
    class Error<T>(data: T?, message: String?) : NetworkResponse<T>(data, message)
}