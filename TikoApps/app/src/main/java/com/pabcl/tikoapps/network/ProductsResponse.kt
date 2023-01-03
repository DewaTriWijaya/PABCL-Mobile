package com.pabcl.tikoapps.network

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("rating")
    val rating: Float,

    @SerializedName("stock")
    val stock: Int,

    @SerializedName("brand")
    val brand: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("thumbnail")
    val thumbnail: String,
)

data class Products(
    @SerializedName("total")
    val total: Int,

    @SerializedName("products")
    val products: List<ProductsResponse>,
)