package com.example.kemalmaulana.isolution.Fragment.Pembayaran


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.Adapter.PaymentMonthlyAdapter
import com.example.kemalmaulana.isolution.Helper.DummyData

import com.example.kemalmaulana.isolution.R

class PembayaranBulanFragment : Fragment() {

    lateinit var adapter: PaymentMonthlyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_pembayaran_bulan, container, false)
        val listPaymentMonthly: RecyclerView = rootView.findViewById(R.id.listPaymentMontly)
        adapter = PaymentMonthlyAdapter(rootView.context, DummyData.paymentMontly)
        listPaymentMonthly.adapter = adapter

        val layoutManager = LinearLayoutManager(rootView.context)
        listPaymentMonthly.layoutManager = layoutManager
        listPaymentMonthly.setHasFixedSize(true)
        return rootView
    }

}