package com.example.productapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.productapp.database.Product
import com.example.productapp.database.ProductDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel(application: Application) : AndroidViewModel(application) {

    val allProducts: LiveData<List<Product>>
    val repository: ProductRepository


    init {
        val dao = ProductDatabase.getDatabase(application).getProductsDao()
        repository = ProductRepository(dao)
        allProducts = repository.allNotes
    }

    fun deleteProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(product)
    }

    fun updateProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(product)
    }

    fun addProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(product)
    }



}