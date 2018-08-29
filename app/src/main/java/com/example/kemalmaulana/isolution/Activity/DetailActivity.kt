package com.example.kemalmaulana.isolution.Activity

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.WindowManager
import com.example.kemalmaulana.isolution.Fragment.*
import com.example.kemalmaulana.isolution.Fragment.Jadwal.JadwalFragment
import com.example.kemalmaulana.isolution.Fragment.Pembayaran.PembayaranFragment
import com.example.kemalmaulana.isolution.Fragment.Report.ReportFragment
import com.example.kemalmaulana.isolution.R

class DetailActivity : BaseActivity() {

//    lateinit var kehadiran: KehadiranFragment
//    lateinit var nilai: KehadiranFragment
//    lateinit var jadwal: KehadiranFragment
//    lateinit var pembayaran: KehadiranFragment
//    lateinit var reward: KehadiranFragment
//    lateinit var report: KehadiranFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setHomeButtonEnabled(true)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        val manager: FragmentManager = supportFragmentManager
        val section = intent?.extras?.getString("section")

        when (section) {
            "kehadiran" -> {
                manager.beginTransaction().replace(R.id.container, KehadiranFragment()).commit()
                supportActionBar?.title = getString(R.string.kehadiran)
                supportActionBar?.setSubtitle(getString(R.string.subtitle_kehadiran))
            }
            "report" -> {
                manager.beginTransaction().replace(R.id.container, ReportFragment()).commit()
                supportActionBar?.title = getString(R.string.report)
                supportActionBar?.setSubtitle(getString(R.string.subtitle_report))
            }
            "jadwal" -> {
                manager.beginTransaction().replace(R.id.container, JadwalFragment()).commit()
                supportActionBar?.title = getString(R.string.jadwal)
                supportActionBar?.setSubtitle(getString(R.string.subtitle_jadwal))
            }
            "pembayaran" -> {
                manager.beginTransaction().replace(R.id.container, PembayaranFragment()).commit()
                supportActionBar?.title = getString(R.string.pembayaran)
                supportActionBar?.setSubtitle(getString(R.string.subtitle_pembayaran))
            }
            "reward" -> {
                manager.beginTransaction().replace(R.id.container, RewardFragment()).commit()
                supportActionBar?.title = getString(R.string.reward)
                supportActionBar?.setSubtitle(getString(R.string.subtitle_reward))
            }
        }
    }

    override fun onBackPressed() {
        this.finish()
    }
}
