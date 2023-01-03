package com.pabcl.tikoapps.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_information")
class ProductsEntity (
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    val id: String,

    @field:ColumnInfo(name = "title")
    val title: String,

    @field:ColumnInfo(name = "description")
    val description: String,

    @field:ColumnInfo(name = "price")
    val price: Int,

    @field:ColumnInfo(name = "rating")
    val rating: Float,

    @field:ColumnInfo(name = "stock")
    val stock: Int,

    @field:ColumnInfo(name = "brand")
    val brand: String,

    @field:ColumnInfo(name = "category")
    val category: String,

    @field:ColumnInfo(name = "thumbnail")
    val thumbnail: String,

    @field:ColumnInfo(name = "favorite")
    var isFavorite: Boolean
)