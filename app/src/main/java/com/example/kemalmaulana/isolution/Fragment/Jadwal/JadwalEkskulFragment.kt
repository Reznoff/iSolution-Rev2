package com.example.kemalmaulana.isolution.Fragment.Jadwal


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.Adapter.JadwalPelajaranAdapter
import com.example.kemalmaulana.isolution.Adapter.PaymentMonthlyAdapter
import com.example.kemalmaulana.isolution.Helper.DummyData

import com.example.kemalmaulana.isolution.R

class JadwalEkskulFragment : Fragment() {
//    lateinit var adapter: JadwalPelajaranAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_jadwal_ekskul, container, false)

        return rootView
    }


}