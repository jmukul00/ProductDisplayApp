package com.example.productapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.productapp.Common
import com.example.productapp.viewModel.ProductsViewModel
import com.example.productapp.R
import com.example.productapp.database.Product
import com.example.productapp.databinding.ActivityEditProductBinding
import java.text.SimpleDateFormat
import java.util.*

class EditProductActivity : AppCompatActivity() {


    lateinit var viewModel: ProductsViewModel
    lateinit var binding: ActivityEditProductBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Show the data in the edit text for editing
        binding.txtUpdateProductName.setText(Common.clickedProduct.name)
        binding.txtUpdateProductDescription.setText(Common.clickedProduct.description)
        binding.txtUpdateProductRegularPrice.setText(Common.clickedProduct.regularPrice)
        binding.txtUpdateProductSalePrice.setText(Common.clickedProduct.salePrice)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[ProductsViewModel::class.java]


        binding.btnUpdateProduct.setOnClickListener {
            val updateProduct = Product(
                binding.txtUpdateProductName.text.toString(),
                binding.txtUpdateProductDescription.text.toString(),
                binding.txtUpdateProductRegularPrice.text.toString(),
                binding.txtUpdateProductSalePrice.text.toString(),
                Common.clickedProduct.productPhoto,
                Common.clickedProduct.colorList,
                Common.clickedProduct.storeList
            )
            updateProduct.id = Common.clickedProduct.id

            // update the data in the room database
            viewModel.updateProduct(updateProduct)

            val intent = Intent(this, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            this.finish()
            Toast.makeText(this, "Product Updated...", Toast.LENGTH_LONG).show()
        }

    }
}