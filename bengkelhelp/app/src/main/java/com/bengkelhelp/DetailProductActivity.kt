package com.bengkelhelp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bengkelhelp.models.CartItem
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase

class DetailProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        // Ambil data dari intent
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val rating = intent.getStringExtra("rating")
        val imageUrl = intent.getStringExtra("imageUrl")
        val description = intent.getStringExtra("description") ?: "Belum ada deskripsi."

        // Hubungkan ke layout
        val ivProductImage = findViewById<ImageView>(R.id.iv_product_image)
        val tvProductName = findViewById<TextView>(R.id.tv_product_name)
        val tvProductPrice = findViewById<TextView>(R.id.tv_product_price)
        val tvProductRating = findViewById<TextView>(R.id.tv_product_rating)
        val tvProductDescription = findViewById<TextView>(R.id.tv_product_description)
        val btnAddToCart = findViewById<Button>(R.id.btn_add_to_cart)

        // Set data ke view
        tvProductName.text = name
        tvProductPrice.text = price
        tvProductRating.text = rating
        tvProductDescription.text = description

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.product_placeholder)
            .into(ivProductImage)

        // Tombol keranjang
        btnAddToCart.setOnClickListener {
            val userId = "user123" // Ganti dengan UID login sesungguhnya
            val cartRef = FirebaseDatabase.getInstance().getReference("Cart").child(userId)
            val cartId = cartRef.push().key ?: return@setOnClickListener

            val cartItem = CartItem(
                shopName = "", // kamu bisa ambil dari UI/bengkel login
                productName = name ?: "",
                quantity = "1",
                price = price ?: "",
                totalPrice = price ?: "",
                imageUrl = imageUrl ?: "",
                description = description ?: ""
            )
            cartRef.child(cartId).setValue(cartItem).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Berhasil ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Gagal menambahkan", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
