package com.pabcl.tikoapps.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pabcl.tikoapps.data.ProductsRepository
import com.pabcl.tikoapps.data.Result
import com.pabcl.tikoapps.data.local.ProductsEntity
import kotlinx.coroutines.launch

class HomeViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    fun getProducts(): LiveData<Result<List<ProductsEntity>>> =
        productsRepository.getProducts()

    fun getFavorite() = productsRepository.getFavoriteProducts()

    fun saveProducts(products: ProductsEntity) {
        viewModelScope.launch {
            productsRepository.setProductsFavorite(products, true)
        }
    }

    fun deleteProducts(products: ProductsEntity) {
        viewModelScope.launch {
            productsRepository.setProductsFavorite(products, false)
        }
    }
}