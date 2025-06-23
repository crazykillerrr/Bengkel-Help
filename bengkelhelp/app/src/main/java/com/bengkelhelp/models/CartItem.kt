package com.bengkelhelp.models

data class CartItem(
    val shopName: String = "",
    val productName: String = "",
    val quantity: String = "1",
    val price: String = "",
    val totalPrice: String = "",
    val imageUrl: String = "",
    val description: String = ""
)
