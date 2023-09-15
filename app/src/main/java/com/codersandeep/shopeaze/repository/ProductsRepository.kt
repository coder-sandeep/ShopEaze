package com.codersandeep.shopeaze.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.codersandeep.shopeaze.api.ProductsAPI
import com.codersandeep.shopeaze.models.ProductsItem
import com.codersandeep.shopeaze.utils.Constants.LOG_TAG
import com.codersandeep.shopeaze.utils.ProductResponse
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val productsAPI: ProductsAPI){
    private val _productsLiveData = MutableLiveData<ProductResponse<List<ProductsItem>>>()
    private val _singleProductLiveData = MutableLiveData<ProductResponse<ProductsItem>>()
    val singleProductLiveData get() = _singleProductLiveData
    val productsLiveData get() = _productsLiveData

    suspend fun getAllProducts(){
        _productsLiveData.postValue(ProductResponse.Loading())

        val result = productsAPI.getAllProducts()
        if(result.isSuccessful && result.body() != null) {
            _productsLiveData.postValue(ProductResponse.Success(result.body()))
        }
        else
            _productsLiveData.postValue(ProductResponse.Error(result.body(),"Something went wrong"),)
    }

    suspend fun getSingleProduct(productId : Int){
        _singleProductLiveData.postValue(ProductResponse.Loading())

        val result = productsAPI.getSingleProduct(productId)
        if(result.isSuccessful && result.body() != null) {
            _singleProductLiveData.postValue(ProductResponse.Success(result.body()))
        }
        else
            _singleProductLiveData.postValue(ProductResponse.Error(result.body(),"Something went wrong"),)
    }
}