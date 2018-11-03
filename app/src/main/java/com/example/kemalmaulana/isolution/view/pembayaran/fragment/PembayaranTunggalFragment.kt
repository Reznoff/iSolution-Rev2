package com.example.kemalmaulana.isolution.view.pembayaran.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.view.pembayaran.adapter.PembayaranSingleAdapter
import com.example.kemalmaulana.isolution.model.DummyData

import com.example.kemalmaulana.isolution.R

class PembayaranTunggalFragment : Fragment() {

    lateinit var adapter: PembayaranSingleAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_pembayaran_tunggal, container, false)
        val listPaymentSingle: RecyclerView = rootView.findViewById(R.id.listPaymentSingle)

        adapter = PembayaranSingleAdapter(rootView.context, DummyData.paymentSingle)
        listPaymentSingle.adapter = adapter

        val layoutManager = LinearLayoutManager(rootView.context)
        listPaymentSingle.layoutManager = layoutManager
        listPaymentSingle.setHasFixedSize(true)


        return rootView
    }
}
