package com.example.kemalmaulana.isolution.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.kemalmaulana.isolution.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun kehadiranClicked(view: View) {
        val kehadiran = Intent(this, DetailActivity::class.java)
        kehadiran.putExtra("section", "kehadiran")
        startActivity(kehadiran)
    }

    fun nilaiClicked(view: View) {
        val kehadiran = Intent(this, DetailActivity::class.java)
        kehadiran.putExtra("section", "nilai")
        startActivity(kehadiran)
    }

    fun jadwalClicked(view: View) {
        val kehadiran = Intent(this, DetailActivity::class.java)
        kehadiran.putExtra("section", "jadwal")
        startActivity(kehadiran)
    }

    fun pembayaranClicked(view: View) {
        val kehadiran = Intent(this, DetailActivity::class.java)
        kehadiran.putExtra("section", "pembayaran")
        startActivity(kehadiran)
    }

    fun rewardClicked(view: View) {
        val kehadiran = Intent(this, DetailActivity::class.java)
        kehadiran.putExtra("section", "reward")
        startActivity(kehadiran)
    }

    fun reportClicked(view: View) {
        val kehadiran = Intent(this, DetailActivity::class.java)
        kehadiran.putExtra("section", "report")
        startActivity(kehadiran)
    }
}
