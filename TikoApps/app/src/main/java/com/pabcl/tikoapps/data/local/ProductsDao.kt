package com.pabcl.tikoapps.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductsDao {
    @Query("SELECT * FROM products_information ORDER BY id ASC")
    fun getProducts(): LiveData<List<ProductsEntity>>

    @Query("SELECT * FROM products_information where favorite = 1")
    fun getFavoriteProducts(): LiveData<List<ProductsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProducts(products: List<ProductsEntity>)

    @Update
    suspend fun updateProducts(Products: ProductsEntity)

    @Query("DELETE FROM products_information WHERE favorite = 0")
    suspend fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM products_information WHERE id = :id AND favorite = 1)")
    suspend fun isProductsFavorite(id: String): Boolean
}