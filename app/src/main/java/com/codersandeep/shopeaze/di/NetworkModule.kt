package com.codersandeep.shopeaze.di

import com.codersandeep.shopeaze.api.ProductsAPI
import com.codersandeep.shopeaze.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()
    }
    @Singleton
    @Provides
    fun providesProductAPI(retrofit: Retrofit) : ProductsAPI{
        return retrofit.create(ProductsAPI::class.java)
    }
}