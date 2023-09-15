package com.codersandeep.shopeaze.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codersandeep.shopeaze.db.ProductsDao
import com.codersandeep.shopeaze.models.ProductsItem

@Database(entities = [ProductsItem :: class], version = 1)
abstract class ProductsDB : RoomDatabase() {
    abstract fun providesProductsDao() : ProductsDao
}