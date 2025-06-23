package com.bengkelhelp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationFragment : Fragment() {

    private lateinit var rvNotifications: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        initViews(view)
        setupRecyclerView()
        loadNotifications()

        return view
    }

    private fun initViews(view: View) {
        rvNotifications = view.findViewById(R.id.rv_notifications)
    }

    private fun setupRecyclerView() {
        notificationAdapter = NotificationAdapter(emptyList())
        rvNotifications.layoutManager = LinearLayoutManager(context)
        rvNotifications.adapter = notificationAdapter
    }

    private fun loadNotifications() {
        val notificationList = listOf(
            NotificationItem("Paket diantar", "Paket sedang diantar ke lokasi anda.", R.drawable.ic_shopping_bag),
            NotificationItem("Panggil Montir", "Panggilan montirmu telah diterima, montir telah dikirim ke lokasi anda silahkan tunggu beberapa saat.", R.drawable.ic_build),
            NotificationItem("Top up berhasil", "Saldo anda telah ditambahkan Rp100.000.", R.drawable.ic_add),
            NotificationItem("Ganti oli mobil", "Waktunya untuk mengganti oli mobil! Pastikan kendaraan Anda tetap terjaga dengan baik dengan mengganti oli secara teratur.", R.drawable.ic_notifications),
            NotificationItem("Service Mobil", "Sudah waktunya untuk layanan mobil Anda! Pastikan mobil Anda tetap dalam kondisi prima dengan melakukan layanan berkala.", R.drawable.ic_notifications),
            NotificationItem("Pembayaran anda berhasil", "Pembayaran Anda berhasil! Barang pesanan Anda sedang diproses oleh toko.", R.drawable.ic_shopping_bag),
            NotificationItem("Ganti ban motor", "Waktunya untuk mengganti ban motor Anda! Pastikan keamanan perjalanan Anda dengan memeriksa kondisi ban secara berkala", R.drawable.ic_notifications)
        )

        notificationAdapter.updateData(notificationList)
    }
}
