package com.bengkelhelp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bengkelhelp.models.Bengkel

class HomeFragment : Fragment() {

    private lateinit var rvTopBengkel: RecyclerView
    private lateinit var bengkelAdapter: BengkelAdapter
    private lateinit var ivNotification: ImageView
    private lateinit var ivChat: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initViews(view)
        setupRecyclerView()
        loadTopBengkel()

        return view
    }

    private fun initViews(view: View) {
        rvTopBengkel = view.findViewById(R.id.rv_top_bengkel)
        ivNotification = view.findViewById(R.id.iv_notification)
        ivChat = view.findViewById(R.id.iv_chat)


        ivChat.setOnClickListener {
            val intent = Intent(requireContext(), ChatBengkelActivity::class.java)
            startActivity(intent)
        }
        val topup = view.findViewById<ImageView>(R.id.topup)
        topup.setOnClickListener {
            val intent = Intent(requireContext(), TopupActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setupRecyclerView() {
        bengkelAdapter = BengkelAdapter(emptyList())
        rvTopBengkel.layoutManager = GridLayoutManager(context, 3)
        rvTopBengkel.adapter = bengkelAdapter
    }

    private fun loadTopBengkel() {
        val bengkelList = listOf(
            Bengkel("Bengkel Cahaya Mobil", R.drawable.bengkel_placeholder),
            Bengkel("Bengkel Fafa Auto", R.drawable.bengkel_placeholder),
            Bengkel("Bengkel Mobil Rofiq", R.drawable.bengkel_placeholder),
            Bengkel("Bengkel Motor Mobil", R.drawable.bengkel_placeholder)
        )
        bengkelAdapter.updateData(bengkelList)
    }
}
