package com.example.productapp

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.productapp.database.Product
import com.squareup.picasso.Picasso

class ProductsAdapter(
    val context: Context,
    val productClickListener: ProductClickListener,
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private val allProducts = ArrayList<Product>()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNote: TextView = itemView.findViewById(R.id.txtTitle)
        val txtRegularPrice: TextView = itemView.findViewById(R.id.txtRegularPrice)
        val txtSalePrice: TextView = itemView.findViewById(R.id.txtSalePrice)
        val imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtNote.text = allProducts[position].name
        holder.txtRegularPrice.text = "Rs. ${allProducts[position].regularPrice}"
        holder.txtRegularPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.txtSalePrice.text = "Rs. ${allProducts[position].salePrice}"
        Picasso.get().load(allProducts[position].productPhoto).into(holder.imgProduct)


        holder.itemView.setOnClickListener {
            Common.clickedProduct = allProducts[position]
            productClickListener.onProductClick(allProducts[position])
        }

    }

    override fun getItemCount(): Int {
        return allProducts.size
    }

    fun updateList(newList: List<Product>) {
        allProducts.clear()
        allProducts.addAll(newList)
        notifyDataSetChanged()
    }

}


interface ProductClickListener {
    fun onProductClick(product: Product)
}