package com.example.kemalmaulana.isolution.view.pembayaran.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.view.pembayaran.adapter.PembayaranBulananAdapter
import com.example.kemalmaulana.isolution.model.DummyData

import com.example.kemalmaulana.isolution.R

class PembayaranBulanFragment : Fragment() {

    lateinit var adapter: PembayaranBulananAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_pembayaran_bulan, container, false)
        val listPaymentMonthly: RecyclerView = rootView.findViewById(R.id.listPaymentMontly)
        adapter = PembayaranBulananAdapter(rootView.context, DummyData.paymentMontly)
        listPaymentMonthly.adapter = adapter

        val layoutManager = LinearLayoutManager(rootView.context)
        listPaymentMonthly.layoutManager = layoutManager
        listPaymentMonthly.setHasFixedSize(true)
        return rootView
    }

}