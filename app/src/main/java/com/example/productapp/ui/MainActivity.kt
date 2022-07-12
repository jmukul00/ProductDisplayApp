package com.example.productapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productapp.*
import com.example.productapp.database.Product
import com.example.productapp.databinding.ActivityMainBinding
import com.example.productapp.viewModel.ProductsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ProductClickListener {

    lateinit var viewModel: ProductsViewModel

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        binding.rvNotes.layoutManager = GridLayoutManager(this,2)



        val notesAdapter = ProductsAdapter(this, this)
        binding.rvNotes.adapter = notesAdapter
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[ProductsViewModel ::class.java]

        // fetch the data from the room database
        viewModel.allProducts.observe(this) { list ->
            list?.let {
                if (it.isEmpty()){
                    binding.txtMessage.visibility = View.VISIBLE
                }
                else{
                    notesAdapter.updateList(it)
                    binding.txtMessage.visibility = View.GONE
                }

            }
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddProductActivity ::class.java)
            startActivity(intent)
            //this.finish()
        }

        setContentView(binding.root)

    }


    override fun onProductClick(product: Product) {
        val intent = Intent(this@MainActivity, ProductDetailsActivity::class.java)
        startActivity(intent)
        //this.finish()
    }
}