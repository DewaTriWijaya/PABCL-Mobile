package com.pabcl.tikoapps.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.pabcl.tikoapps.data.local.ProductsDao
import com.pabcl.tikoapps.data.local.ProductsEntity
import com.pabcl.tikoapps.network.ApiService

class ProductsRepository private constructor(
    private val apiService: ApiService,
    private val productDao: ProductsDao
) {
    fun getProducts(): LiveData<Result<List<ProductsEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getProducts()
            val products = response.products
            val productsList = products.map {
                val isFavorite = productDao.isProductsFavorite(it.id)
                ProductsEntity(
                    it.id,
                    it.title,
                    it.description,
                    it.price,
                    it.rating,
                    it.stock,
                    it.brand,
                    it.category,
                    it.thumbnail,
                    isFavorite
                )
            }
            productDao.deleteAll()
            productDao.insertProducts(productsList)
        } catch (e: Exception) {
            Log.d("ProductsRepository", "getHeadlineProducts: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<ProductsEntity>>> = productDao.getProducts().map { Result.Success(it) }
        emitSource(localData)
    }

    fun getFavoriteProducts(): LiveData<List<ProductsEntity>> {
        return productDao.getFavoriteProducts()
    }

    suspend fun setProductsFavorite(products: ProductsEntity, favoriteState: Boolean) {
        products.isFavorite = favoriteState
        productDao.updateProducts(products)
    }

    companion object {
        @Volatile
        private var instance: ProductsRepository? = null
        fun getInstance(
            apiService: ApiService,
            productsDao: ProductsDao
        ): ProductsRepository =
            instance ?: synchronized(this) {
                instance ?: ProductsRepository(apiService, productsDao)
            }.also { instance = it }
    }

}