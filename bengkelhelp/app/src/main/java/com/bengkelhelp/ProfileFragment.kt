package com.bengkelhelp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        setupClickListeners(view)
        return view
    }

    private fun setupClickListeners(view: View) {
        view.findViewById<View>(R.id.menu_alamat_saya).setOnClickListener {
            // Handle alamat saya click
        }

        view.findViewById<View>(R.id.menu_buka_bengkel).setOnClickListener {
            // Handle buka bengkel click
        }

        view.findViewById<View>(R.id.menu_riwayat_pesanan).setOnClickListener {
            // Handle riwayat pesanan click
        }

        view.findViewById<View>(R.id.menu_ganti_pin).setOnClickListener {
            // Handle ganti PIN click
        }

        view.findViewById<View>(R.id.menu_customer_service).setOnClickListener {
            // Handle customer service click
        }

        view.findViewById<View>(R.id.menu_tentang_aplikasi).setOnClickListener {
            // Handle tentang aplikasi click
        }

        view.findViewById<View>(R.id.menu_keluar).setOnClickListener {
            // Keluar dari aplikasi
            requireActivity().finishAffinity()
        }
    }
}
