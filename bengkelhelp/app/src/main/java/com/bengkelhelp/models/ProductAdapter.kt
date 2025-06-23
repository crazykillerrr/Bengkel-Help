package com.bengkelhelp.models

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bengkelhelp.DetailProductActivity
import com.bengkelhelp.R
import com.bumptech.glide.Glide

class ProductAdapter(
    private var productList: List<Product>,
    private val onItemClick: ((Product) -> Unit)? = null
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    fun updateData(newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.tvName.text = product.name
        holder.tvPrice.text = product.price
        holder.tvRating.text = product.rating

        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .placeholder(R.drawable.product_placeholder)
            .into(holder.ivImage)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailProductActivity::class.java)
            intent.putExtra("name", product.name)
            intent.putExtra("price", product.price)
            intent.putExtra("rating", product.rating)
            intent.putExtra("imageUrl", product.imageUrl)
            intent.putExtra("description", product.description) // ← JANGAN LUPA
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_product_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_product_price)
        val tvRating: TextView = itemView.findViewById(R.id.tv_product_rating)
        val ivImage: ImageView = itemView.findViewById(R.id.iv_product_image)
    }
}
