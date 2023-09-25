package com.codersandeep.shopeaze.repository

import androidx.lifecycle.MutableLiveData
import com.codersandeep.shopeaze.api.ProductsAPI
import com.codersandeep.shopeaze.models.ProductsItem
import com.codersandeep.shopeaze.utils.NetworkResponse
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val productsAPI: ProductsAPI){
    private val _productsLiveData = MutableLiveData<NetworkResponse<List<ProductsItem>>>()
    private val _singleProductLiveData = MutableLiveData<NetworkResponse<ProductsItem>>()
    val singleProductLiveData get() = _singleProductLiveData
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

    suspend fun getSingleProduct(productId : Int){
        _singleProductLiveData.postValue(NetworkResponse.Loading())

        val result = productsAPI.getSingleProduct(productId)
        if(result.isSuccessful && result.body() != null) {
            _singleProductLiveData.postValue(NetworkResponse.Success(result.body()))
        }
        else
            _singleProductLiveData.postValue(NetworkResponse.Error(result.body(),"Something went wrong"),)
    }
}