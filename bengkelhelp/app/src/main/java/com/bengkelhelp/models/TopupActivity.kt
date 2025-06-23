package com.bengkelhelp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class TopupActivity : AppCompatActivity() {

    private lateinit var etAmount: EditText
    private lateinit var tvCurrentBalance: TextView
    private lateinit var btn10k: Button
    private lateinit var btn50k: Button
    private lateinit var btn100k: Button
    private lateinit var btn500k: Button
    private lateinit var btnPayNow: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up)

        initViews()
        setupQuickButtons()
    }

    private fun initViews() {
        etAmount = findViewById(R.id.et_amount)
        tvCurrentBalance = findViewById(R.id.tv_current_balance)
        btn10k = findViewById(R.id.btn_10k)
        btn50k = findViewById(R.id.btn_50k)
        btn100k = findViewById(R.id.btn_100k)
        btn500k = findViewById(R.id.btn_500k)
        btnPayNow = findViewById(R.id.btn_pay_now)

        // Placeholder saldo saat ini
        tvCurrentBalance.text = "Saldo BengkelPay saat ini: Rp1.000.000"
    }

    private fun setupQuickButtons() {
        btn10k.setOnClickListener { etAmount.setText("10000") }
        btn50k.setOnClickListener { etAmount.setText("50000") }
        btn100k.setOnClickListener { etAmount.setText("100000") }
        btn500k.setOnClickListener { etAmount.setText("500000") }

        btnPayNow.setOnClickListener {
            // Handle pembayaran, misal tampilkan toast
            val input = etAmount.text.toString()
            if (input.isNotBlank()) {
                // Simulasi pembayaran
                val nominal = input.toIntOrNull() ?: 0
                if (nominal > 0) {
                    // Lanjutkan logika top up ke backend, Firebase, atau tampilkan konfirmasi
                    // Contoh:
                    showToast("Top up sebesar Rp$nominal berhasil.")
                } else {
                    showToast("Masukkan nominal yang valid.")
                }
            } else {
                showToast("Nominal tidak boleh kosong.")
            }
        }
    }

    private fun showToast(message: String) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}
