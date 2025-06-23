package com.bengkelhelp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatBengkelActivity : AppCompatActivity() {

    private lateinit var rvChatList: RecyclerView
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bengkel)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // untuk tombol back di Toolbar

        initViews()
        setupRecyclerView()
        loadChatList()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun initViews() {
        rvChatList = findViewById(R.id.rv_chat_list)
    }

    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(emptyList())
        rvChatList.layoutManager = LinearLayoutManager(this)
        rvChatList.adapter = chatAdapter
    }

    private fun loadChatList() {
        val chatList = listOf(
            ChatItem("Bengkel Cahaya Mobil", "Terimakasih atas minat anda terhadap produk kami", "02-05", R.drawable.bengkel_placeholder),
            ChatItem("Bengkel Mobil Fafa Auto", "", "30-04", R.drawable.bengkel_placeholder),
            ChatItem("Bengkel Mobil Rizky", "Bagaimana pengalaman anda?", "30-04", R.drawable.bengkel_placeholder),
            ChatItem("Bengkel Motor Mobil", "Ayo belanja lagi di toko kami", "22-04", R.drawable.bengkel_placeholder),
            ChatItem("Bengkel Motor Gunter", "Ada produk baru loh di toko kami", "19-04", R.drawable.bengkel_placeholder),
            ChatItem("Bengkel Malik Pertalite", "Terimakasih sudah membeli di toko kami", "13-04", R.drawable.bengkel_placeholder),
            ChatItem("Bengkel Pertamax", "Yuk, checkout pesanan anda", "07-04", R.drawable.bengkel_placeholder),
            ChatItem("Bengkel Mobil Suci", "Promo dan diskon khusus untuk anda", "04-04", R.drawable.bengkel_placeholder)
        )

        chatAdapter.updateData(chatList)
    }
}
