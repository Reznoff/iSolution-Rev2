package com.example.kemalmaulana.isolution.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.kemalmaulana.isolution.Adapter.NilaiAdapter
import com.example.kemalmaulana.isolution.Helper.DummyData

import com.example.kemalmaulana.isolution.R


class NilaiFragment : Fragment() {

    lateinit var adapter: NilaiAdapter

    companion object {
        fun newInstance(): NilaiFragment {
            return NilaiFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_nilai, container, false)
        val listNilai = rootView.findViewById<ListView>(R.id.listNilai)
        adapter = NilaiAdapter(requireContext(), DummyData.nilai)
        listNilai.adapter = adapter
        return rootView
    }


}

