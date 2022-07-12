package com.example.productapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.productapp.Common
import com.example.productapp.viewModel.ProductsViewModel
import com.example.productapp.R
import com.example.productapp.database.Product
import com.example.productapp.database.Store
import com.example.productapp.databinding.ActivityAddProductBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception


class AddProductActivity : AppCompatActivity() {

    lateinit var binding:ActivityAddProductBinding
    lateinit var viewModel: ProductsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddProductBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[ProductsViewModel::class.java]


        binding.btnAddProduct1.setOnClickListener{
            try {
                // Store the data in the Room database
                viewModel.addProduct(getJsonData(0))
                Toast.makeText(this, "ProductAdded", Toast.LENGTH_SHORT).show()
            }
            catch (e:Exception){
                e.printStackTrace()
            }


        }

        binding.btnAddProduct2.setOnClickListener{
            try {
                viewModel.addProduct(getJsonData(1))
                Toast.makeText(this, "ProductAdded", Toast.LENGTH_SHORT).show()
            }
            catch (e:Exception){
                e.printStackTrace()
            }


        }

        binding.btnAddProduct3.setOnClickListener{
            try {
                viewModel.addProduct(getJsonData(2))
                Toast.makeText(this, "ProductAdded", Toast.LENGTH_SHORT).show()
            }
            catch (e:Exception){
                e.printStackTrace()
            }


        }


        setContentView(binding.root)
    }

    private fun getJsonData(index:Int): Product{
        // To read the json data from the file
        val text = resources.openRawResource(R.raw.products)
            .bufferedReader().use { it.readText() }
            val obj = JSONObject(text)

            val products: JSONArray = obj.getJSONArray("products")

            val product  = products.getJSONObject(index)

            val productName = product.getString("name")
            val productDescription = product.getString("description")
            val productRegularPrice = product.getString("regularPrice")
            val productSalePrice = product.getString("salePrice")
            val productPhotoUrl = product.getString("productPhoto")
            val jsonArray = product.getJSONArray("colorList")
            val colorList = ArrayList<String>()
            for (i in 0 until jsonArray.length()){
                colorList.add(jsonArray.getString(i))
            }

        val stores: JSONArray = product.getJSONArray("storeList")
        var storeList = ArrayList<Store>()
        for (i in 0 until stores.length()){
            val store  = stores.getJSONObject(i)
            storeList.add(Store(Common.getRandomString(), store.getString("name"), store.getString("address")))
        }

           return Product(productName, productDescription, productRegularPrice, productSalePrice, productPhotoUrl, colorList, storeList)



    }
}