package com.bengkelhelp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bengkelhelp.models.Product
import com.bengkelhelp.models.ProductAdapter
import com.google.firebase.database.*

class BengkelMartFragment : Fragment() {

    private lateinit var rvProducts: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private var productList: List<Product> = emptyList()
    private lateinit var databaseRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bengkel_mart, container, false)

        initViews(view)
        setupRecyclerView()
        loadProducts()

        return view
    }

    private fun initViews(view: View) {
        rvProducts = view.findViewById(R.id.rv_products)

        val cartButton = view.findViewById<View>(R.id.cart_button)
        cartButton?.setOnClickListener {
            openCart()
        }

        val searchBar = view.findViewById<EditText>(R.id.searchbarproduk)
        val tombolCari = view.findViewById<View>(R.id.tombolsearchproduk)

        tombolCari.setOnClickListener {
            val query = searchBar.text.toString().trim()
            filterProducts(query)
        }

        // Optional: Enable real-time search too
        /*
        searchBar?.addTextChangedListener {
            val query = it.toString().trim()
            filterProducts(query)
        }
        */
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(emptyList()) { product ->
            val intent = Intent(requireContext(), DetailProductActivity::class.java)
            intent.putExtra("name", product.name)
            intent.putExtra("price", product.price)
            intent.putExtra("rating", product.rating)
            intent.putExtra("imageUrl", product.imageUrl)
            startActivity(intent)
        }

        rvProducts.layoutManager = GridLayoutManager(context, 2)
        rvProducts.adapter = productAdapter
    }

    private fun loadProducts() {
        databaseRef = FirebaseDatabase.getInstance().getReference("Product")

        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val products = mutableListOf<Product>()
                for (productSnapshot in snapshot.children) {
                    val product = productSnapshot.getValue(Product::class.java)
                    if (product != null) {
                        products.add(product)
                    }
                }
                productList = products
                productAdapter.updateData(productList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }


    private fun filterProducts(query: String) {
        val filtered = productList.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.price.contains(query, ignoreCase = true) ||
                    it.rating.contains(query, ignoreCase = true)
        }

        productAdapter.updateData(filtered)
    }

    private fun openCart() {
        val intent = Intent(activity, CartActivity::class.java)
        startActivity(intent)
    }
}
