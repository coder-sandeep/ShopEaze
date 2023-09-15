package com.codersandeep.shopeaze.api

import com.codersandeep.shopeaze.models.ProductsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsAPI {
    @GET("/products")
    suspend fun getAllProducts() : Response<List<ProductsItem>>

    @GET("/products/{productId}")
    suspend fun getSingleProduct(@Path("productId") id : Int) : Response<ProductsItem>

    //@BODY for POST
}