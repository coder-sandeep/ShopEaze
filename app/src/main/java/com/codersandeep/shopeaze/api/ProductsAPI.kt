package com.codersandeep.shopeaze.api

import com.codersandeep.shopeaze.models.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductsAPI {
    @GET("/products")
    suspend fun getAllProducts() : Response<Products>

    //@BODY for POST
}