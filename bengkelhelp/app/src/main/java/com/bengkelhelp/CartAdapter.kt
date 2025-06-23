package com.bengkelhelp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bengkelhelp.models.CartItem
import com.bumptech.glide.Glide

class CartAdapter(private var cartItemList: List<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    fun updateData(newItems: List<CartItem>) {
        cartItemList = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItemList[position]

        holder.tvShopName.text = item.shopName
        holder.tvProductName.text = item.productName
        holder.tvQuantity.text = "Jumlah: ${item.quantity}"
        holder.tvPrice.text = item.price
        holder.tvTotalPrice.text = item.totalPrice
        holder.tvDescription.text = item.description

        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(R.drawable.product_placeholder)
            .into(holder.ivProductImage)
    }

    override fun getItemCount(): Int = cartItemList.size

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvShopName: TextView = itemView.findViewById(R.id.tv_shop_name)
        val tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        val tvQuantity: TextView = itemView.findViewById(R.id.tv_quantity)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        val tvTotalPrice: TextView = itemView.findViewById(R.id.tv_total_price)
        val ivProductImage: ImageView = itemView.findViewById(R.id.iv_product_image)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_product_description)
    }
}
