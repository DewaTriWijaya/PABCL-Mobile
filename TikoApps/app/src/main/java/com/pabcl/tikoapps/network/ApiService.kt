package com.pabcl.tikoapps.network

import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Products
}