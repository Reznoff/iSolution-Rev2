package com.example.kemalmaulana.isolution.Activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.kemalmaulana.isolution.Fragment.*
import com.example.kemalmaulana.isolution.R

class DetailActivity : BaseActivity() {

    lateinit var kehadiran: KehadiranFragment
    lateinit var nilai: KehadiranFragment
    lateinit var jadwal: KehadiranFragment
    lateinit var pembayaran: KehadiranFragment
    lateinit var reward: KehadiranFragment
    lateinit var report: KehadiranFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val manager: FragmentManager = supportFragmentManager
        var fragment: Fragment? = manager.findFragmentById(R.id.container)

        val section = intent.extras.getString("section")
//        Log.d(TAG, "section: "+section)

        when(section) {
            "kehadiran" -> manager.beginTransaction().add(R.id.container, KehadiranFragment()).commit()
            "nilai" -> manager.beginTransaction().add(R.id.container, NilaiFragment()).commit()
            "jadwal" -> manager.beginTransaction().add(R.id.container, JadwalFragment()).commit()
            "pembayaran" -> manager.beginTransaction().add(R.id.container, PembayaranFragment()).commit()
            "reward" -> manager.beginTransaction().add(R.id.container, RewardFragment()).commit()
            "report" -> manager.beginTransaction().add(R.id.container, ReportFragment()).commit()
        }
    }





}
