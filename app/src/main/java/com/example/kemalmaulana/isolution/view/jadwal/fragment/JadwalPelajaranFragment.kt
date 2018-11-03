package com.example.kemalmaulana.isolution.view.jadwal.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.view.jadwal.adapter.JadwalPelajaranAdapter
import com.example.kemalmaulana.isolution.model.DummyData

import com.example.kemalmaulana.isolution.R

class JadwalPelajaranFragment : Fragment() {

    lateinit var adapter: JadwalPelajaranAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_jadwal_pelajaran, container, false)

        val listPelajaran: RecyclerView = rootView.findViewById(R.id.listPelajaran)
        adapter = JadwalPelajaranAdapter(rootView.context, DummyData.jadwalPelajaran)
        listPelajaran.adapter = adapter

        val layoutManager = LinearLayoutManager(rootView.context)
        listPelajaran.layoutManager = layoutManager
        listPelajaran.setHasFixedSize(true)
        return rootView
    }


}
