package com.example.productapp.database

import android.content.Context
import androidx.room.*


@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProductDatabase :RoomDatabase(){

    abstract fun getProductsDao(): ProductDao

    companion object{

        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database").build()
                INSTANCE = instance
                instance
            }
        }
    }

}