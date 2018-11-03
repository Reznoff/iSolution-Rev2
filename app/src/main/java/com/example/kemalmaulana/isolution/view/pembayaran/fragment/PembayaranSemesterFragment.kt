package com.example.kemalmaulana.isolution.view.pembayaran.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.view.pembayaran.adapter.PembayaranSemesterAdapter
import com.example.kemalmaulana.isolution.model.DummyData

import com.example.kemalmaulana.isolution.R

class PembayaranSemesterFragment : Fragment() {

    lateinit var adapter: PembayaranSemesterAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_pembayaran_semester, container, false)
        val listPaymentSemester: RecyclerView = rootView.findViewById(R.id.listPaymentSemester)

        adapter = PembayaranSemesterAdapter(rootView.context, DummyData.paymentSemester)
        listPaymentSemester.adapter = adapter

        val layoutManager = LinearLayoutManager(rootView.context)
        listPaymentSemester.layoutManager = layoutManager
        listPaymentSemester.setHasFixedSize(true)

        return rootView
    }


}
