package com.bengkelhelp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { onNavigationItemSelected(it) }

        // Fragment default saat pertama kali dibuka
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }

    // Fungsi navigasi fragment dari bottom nav
    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        val selectedFragment: Fragment? = when (item.itemId) {
            R.id.nav_home -> HomeFragment()
            R.id.nav_bengkel_mart -> BengkelMartFragment()
            R.id.nav_notification -> NotificationFragment()
            R.id.nav_profile -> ProfileFragment()
            else -> null
        }

        selectedFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, it)
                .commit()
            return true
        }

        return false
    }

    // Fungsi untuk membuka halaman chat bengkel
    fun openChatBengkel() {
        val intent = Intent(this, ChatBengkelActivity::class.java)
        startActivity(intent)
    }

    // Fungsi untuk membuka halaman keranjang
    fun openCart() {
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }
}
