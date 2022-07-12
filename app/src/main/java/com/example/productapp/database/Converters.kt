package com.example.productapp.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun storeListToJson(value: List<Store>?) = Gson().toJson(value)

    @TypeConverter
    fun storeJsonToList(value: String) = Gson().fromJson(value, Array<Store>::class.java).toList()
}