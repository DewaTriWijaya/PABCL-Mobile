package com.pabcl.tikoapps.di

import android.content.Context
import com.pabcl.tikoapps.data.ProductsRepository
import com.pabcl.tikoapps.data.local.ProductsDatabase
import com.pabcl.tikoapps.network.ApiRetrofit

object Injection {
    fun provideRepository(context: Context): ProductsRepository {
        val apiService = ApiRetrofit.getApiService()
        val database = ProductsDatabase.getInstance(context)
        val productsDao = database.productsDao()
        return ProductsRepository.getInstance(apiService, productsDao)
    }
}