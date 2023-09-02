package com.codersandeep.shopeaze.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.codersandeep.shopeaze.api.ProductsAPI
import com.codersandeep.shopeaze.models.Products
import com.codersandeep.shopeaze.utils.Constants.LOG_TAG
import com.codersandeep.shopeaze.utils.NetworkResponse
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val productsAPI: ProductsAPI){
    private val _productsLiveData = MutableLiveData<NetworkResponse<Products>>()
    val productsLiveData get() = _productsLiveData

    suspend fun getAllProducts(){
        _productsLiveData.postValue(NetworkResponse.Loading())

        val result = productsAPI.getAllProducts()
        if(result.isSuccessful && result.body() != null) {
            _productsLiveData.postValue(NetworkResponse.Success(result.body()))
        }
        else
            _productsLiveData.postValue(NetworkResponse.Error(result.body(),"Something went wrong"),)
    }
}