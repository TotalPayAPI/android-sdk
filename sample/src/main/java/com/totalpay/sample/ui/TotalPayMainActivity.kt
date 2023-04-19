/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.totalpay.sample.databinding.ActivityMainBinding

class TotalPayMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureView()
    }

    private fun configureView() {
        binding.btnSale.setOnClickListener {
            startActivity(Intent(this, TotalPaySaleActivity::class.java))
        }
        binding.btnRecurringSale.setOnClickListener {
            startActivity(Intent(this, TotalPayRecurringSaleActivity::class.java))
        }
        binding.btnCapture.setOnClickListener {
            startActivity(Intent(this, TotalPayCaptureActivity::class.java))
        }
        binding.btnCreditVoid.setOnClickListener {
            startActivity(Intent(this, TotalPayCreditVoidActivity::class.java))
        }
        binding.btnGetTransStatus.setOnClickListener {
            startActivity(Intent(this, TotalPayGetTransStatusActivity::class.java))
        }
        binding.btnGetTransDetails.setOnClickListener {
            startActivity(Intent(this, TotalPayGetTransDetailsActivity::class.java))
        }
    }
}
