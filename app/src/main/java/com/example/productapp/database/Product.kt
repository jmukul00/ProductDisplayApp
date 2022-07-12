package com.example.productapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productTable")
class Product(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "regularPrice") val regularPrice: String,
    @ColumnInfo(name = "salePrice") val salePrice: String,
    @ColumnInfo(name = "productPhoto") val productPhoto: String,
    @ColumnInfo(name = "colorList") val colorList: List<String>,
    @ColumnInfo(name = "storeList") val storeList: List<Store>
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}