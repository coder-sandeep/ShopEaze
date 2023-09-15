package com.codersandeep.shopeaze.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codersandeep.shopeaze.models.ProductsItem
import retrofit2.Response

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(productsItem: ProductsItem)

    @Query("SELECT * FROM product")
    fun getAllProducts() : LiveData<List<ProductsItem>>

    @Query("DELETE FROM product WHERE id=:productId ")
    suspend fun deleteProduct(productId: Int)

    @Query("SELECT * FROM product WHERE id=:productId ")
    fun getProductById(productId: Int): LiveData<ProductsItem>
}