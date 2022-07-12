package com.example.productapp.ui

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.productapp.Common
import com.example.productapp.R
import com.example.productapp.databinding.ActivityProductDetailsBinding
import com.example.productapp.viewModel.ProductsViewModel
import com.squareup.picasso.Picasso


class ProductDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailsBinding
    lateinit var viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[ProductsViewModel::class.java]

        binding.txtProductTitle.text = Common.clickedProduct.name
        binding.txtDescription.text = Common.clickedProduct.description
        binding.txtProductRegularPrice.text = "Rs. ${Common.clickedProduct.regularPrice}"
        binding.txtProductRegularPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        binding.txtProductSalePrice.text = "Rs. ${Common.clickedProduct.salePrice}"

        for (i in 0 until Common.clickedProduct.colorList.size){
            val rbColor = RadioButton(this)

            rbColor.background = ContextCompat.getDrawable(this, R.drawable.radio_selector)
            rbColor.setTextColor(ContextCompat.getColor(this, R.color.white))
            rbColor.buttonDrawable = StateListDrawable()
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(15, 15, 15, 15)
            rbColor.setPadding(30,20,30,20)
            rbColor.layoutParams = params
            rbColor.text = Common.clickedProduct.colorList[i]
            binding.rgColors.addView(rbColor)
        }

        binding.txtStoreName.text = "Sold By :- ${Common.clickedProduct.storeList[0].name}"
        binding.btnDelete.setOnClickListener {
            viewModel.deleteProduct(Common.clickedProduct)
            val intent = Intent(this, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)

            this.finish()
            Toast.makeText(this, "Product Deleted", Toast.LENGTH_SHORT).show()
        }

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(this, EditProductActivity::class.java)
            startActivity(intent)
        }

        binding.imgBtnBack.setOnClickListener {
            onBackPressed()
        }

        Picasso.get().load(Common.clickedProduct.productPhoto).into(binding.imgProduct)
        binding.imgProduct.setOnClickListener {
            showImage()
        }

        binding.txtProductName.text = Common.clickedProduct.name

        setContentView(binding.root)
    }

    fun showImage() {
        val builder = Dialog(this)
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
        builder.window?.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
        builder.setOnDismissListener(DialogInterface.OnDismissListener {
            //nothing;
        })
        val imageView = ImageView(this)
        Picasso.get().load(Common.clickedProduct.productPhoto).into(imageView)
        builder.addContentView(
            imageView, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        builder.show()
    }
}