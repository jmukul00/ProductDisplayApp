package com.example.productapp.viewModel

import androidx.lifecycle.LiveData
import com.example.productapp.database.Product
import com.example.productapp.database.ProductDao

class ProductRepository(private val productsDao: ProductDao) {
    val allNotes: LiveData<List<Product>> = productsDao.getAllProducts()

    fun insert(product: Product){
        productsDao.insert(product)
    }

    fun update(product: Product){
        productsDao.update(product)
    }

    fun delete(product: Product){
        productsDao.delete(product)
    }


}