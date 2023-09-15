package com.codersandeep.shopeaze.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.codersandeep.shopeaze.db.ProductsDB
import com.codersandeep.shopeaze.models.ProductsItem
import com.codersandeep.shopeaze.utils.Constants.LOG_TAG
import com.codersandeep.shopeaze.utils.ProductResponse
import javax.inject.Inject

class RoomRepository @Inject constructor(private val productsDB: ProductsDB) {

    fun getAllProducts() = productsDB.providesProductsDao().getAllProducts()
    fun getProductById(productId : Int) = productsDB.providesProductsDao().getProductById(productId)

    suspend fun deleteProduct(productId: Int) = productsDB.providesProductsDao().deleteProduct(productId)
    suspend fun saveProduct(productsItem: ProductsItem) = productsDB.providesProductsDao().upsert(productsItem)
}