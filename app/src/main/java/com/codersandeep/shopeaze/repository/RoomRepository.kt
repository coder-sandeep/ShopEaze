package com.codersandeep.shopeaze.repository

import com.codersandeep.shopeaze.db.ProductsDB
import com.codersandeep.shopeaze.models.ProductsItem
import javax.inject.Inject

class RoomRepository @Inject constructor(private val productsDB: ProductsDB) {

    fun getAllProducts() = productsDB.providesProductsDao().getAllProducts()
    fun getProductById(productId : Int) = productsDB.providesProductsDao().getProductById(productId)

    suspend fun deleteProduct(productId: Int) = productsDB.providesProductsDao().deleteProduct(productId)
    suspend fun saveProduct(productsItem: ProductsItem) = productsDB.providesProductsDao().upsert(productsItem)
}