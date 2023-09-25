package com.codersandeep.shopeaze.api

import com.codersandeep.shopeaze.models.SigninRequest
import com.codersandeep.shopeaze.models.SigninResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST("/auth/login")
    suspend fun signin(@Body signinRequest: SigninRequest) : Response<SigninResponse>
}