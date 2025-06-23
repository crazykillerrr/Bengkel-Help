package com.bengkelhelp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bengkelhelp.models.CartItem
import com.google.firebase.database.*

class CartActivity : AppCompatActivity() {

    private lateinit var rvCartItems: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var databaseRef: DatabaseReference
    private lateinit var tvCartCount: TextView
    private lateinit var tvTotalHarga: TextView

    private val cartList = mutableListOf<CartItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        initViews()
        setupRecyclerView()
        loadCartItems()
    }

    private fun initViews() {
        rvCartItems = findViewById(R.id.rv_cart_items)
        tvCartCount = findViewById(R.id.tv_cart_count)
        tvTotalHarga = findViewById(R.id.totalharga)

        findViewById<View>(R.id.btn_back).setOnClickListener {
            finish()
        }

        findViewById<View>(R.id.btn_checkout).setOnClickListener {
            // TODO: implementasi proses checkout
        }
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(cartList)
        rvCartItems.layoutManager = LinearLayoutManager(this)
        rvCartItems.adapter = cartAdapter
    }

    private fun loadCartItems() {
        // Mengambil data dari node "Produk" di Firebase
        databaseRef = FirebaseDatabase.getInstance().getReference("Produk")

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cartList.clear()
                var totalHargaSemua = 0

                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(CartItem::class.java)
                    if (item != null) {
                        val harga = item.price
                            .replace("Rp", "")
                            .replace(".", "")
                            .replace(",", "")
                            .trim()
                            .toIntOrNull() ?: 0

                        val jumlah = item.quantity.toIntOrNull() ?: 1
                        val totalItem = harga * jumlah

                        val updatedItem = item.copy(
                            totalPrice = "Rp${String.format("%,d", totalItem).replace(',', '.')}"
                        )

                        cartList.add(updatedItem)
                        totalHargaSemua += totalItem
                    }
                }

                cartAdapter.updateData(cartList)

                tvCartCount.text = "(${cartList.size})"
                tvTotalHarga.text = "Rp${String.format("%,d", totalHargaSemua).replace(',', '.')}"
            }

            override fun onCancelled(error: DatabaseError) {
                // Tangani error
            }
        })
    }
}
