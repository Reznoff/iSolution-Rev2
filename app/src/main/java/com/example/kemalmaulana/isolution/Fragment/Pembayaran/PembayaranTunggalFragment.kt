package com.example.kemalmaulana.isolution.Fragment.Pembayaran

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.Adapter.PaymentSemesterAdapter
import com.example.kemalmaulana.isolution.Adapter.PaymentSingleAdapter
import com.example.kemalmaulana.isolution.Helper.DummyData

import com.example.kemalmaulana.isolution.R

class PembayaranTunggalFragment : Fragment() {

    lateinit var adapter: PaymentSingleAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_pembayaran_tunggal, container, false)
        val listPaymentSingle: RecyclerView = rootView.findViewById(R.id.listPaymentSingle)

        adapter = PaymentSingleAdapter(rootView.context, DummyData.paymentSingle)
        listPaymentSingle.adapter = adapter

        val layoutManager = LinearLayoutManager(rootView.context)
        listPaymentSingle.layoutManager = layoutManager
        listPaymentSingle.setHasFixedSize(true)


        return rootView
    }
}
