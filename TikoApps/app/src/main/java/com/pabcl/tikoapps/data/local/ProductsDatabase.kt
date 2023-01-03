package com.pabcl.tikoapps.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductsEntity::class], version = 2, exportSchema = false)
abstract class ProductsDatabase: RoomDatabase(){
    abstract fun productsDao(): ProductsDao

    companion object {
        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ProductsDatabase {
            val instanceA = INSTANCE
            if (instanceA != null) {
                return instanceA
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "products_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}