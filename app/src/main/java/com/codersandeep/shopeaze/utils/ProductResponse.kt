package com.codersandeep.shopeaze.utils

sealed class ProductResponse<T>(val data : T? = null, val message : String? = null) {
    class Loading<T> : ProductResponse<T>()
    class Success<T>(data: T?) : ProductResponse<T>(data)
    class Error<T>(data: T?, message: String?) : ProductResponse<T>(data, message)
}