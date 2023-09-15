package com.codersandeep.shopeaze.di

import android.content.Context
import androidx.room.Room
import com.codersandeep.shopeaze.db.ProductsDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) : ProductsDB {
        return Room.databaseBuilder(context, ProductsDB::class.java, "ProductsDB").build()
    }
}