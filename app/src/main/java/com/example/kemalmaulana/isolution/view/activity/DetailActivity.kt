package com.example.kemalmaulana.isolution.view.activity

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.WindowManager
import com.example.kemalmaulana.isolution.view.jadwal.fragment.JadwalFragment
import com.example.kemalmaulana.isolution.view.pembayaran.fragment.PembayaranFragment
import com.example.kemalmaulana.isolution.view.report.fragment.ReportFragment
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.view.kehadiran.fragment.KehadiranFragment
import com.example.kemalmaulana.isolution.view.reward.RewardFragment

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val manager: FragmentManager = supportFragmentManager
        val section = intent?.getStringExtra("section")

        when (section) {
            "kehadiran" -> {
                manager.beginTransaction().replace(R.id.container, KehadiranFragment()).commit()
            }
            "jadwal" -> {
                manager.beginTransaction().replace(R.id.container, JadwalFragment()).commit()
            }
//            "report" -> {
//                manager.beginTransaction().replace(R.id.container, ReportFragment()).commit()
//            }
//            "pembayaran" -> {
//                manager.beginTransaction().replace(R.id.container, PembayaranFragment()).commit()
//            }
        }
    }


}
