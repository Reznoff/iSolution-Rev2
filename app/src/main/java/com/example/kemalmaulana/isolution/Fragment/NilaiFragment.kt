package com.example.kemalmaulana.isolution.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.example.kemalmaulana.isolution.Adapter.NilaiAdapter
import com.example.kemalmaulana.isolution.Helper.DummyData

import com.example.kemalmaulana.isolution.R


class NilaiFragment : Fragment(), OnItemSelectedListener {

    lateinit var adapterList: NilaiAdapter
    lateinit var adapterSpinner: ArrayAdapter<String>

    companion object {
        fun newInstance(): NilaiFragment {
            return NilaiFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_nilai, container, false)

        val spinnerTahunAjaran: Spinner = rootView.findViewById(R.id.spinnerTahunAjaran)
        spinnerTahunAjaran.onItemSelectedListener = this


        var tahunAjaran: ArrayList<String> = arrayListOf()
        for (i: Int in 0 until DummyData.nilai.count()) {
            tahunAjaran.add(DummyData.nilai[i].tahunAjaran)
        }
        adapterSpinner = ArrayAdapter(context, android.R.layout.simple_spinner_item, tahunAjaran)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTahunAjaran.adapter = adapterSpinner

        return rootView
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        val item: String = parent!!.getItemAtPosition(pos).toString()
        val listNilai = parent.rootView!!.findViewById<ListView>(R.id.listNilai)
        if(item.isNotEmpty()) {
            adapterList = NilaiAdapter(requireContext(), DummyData.nilai)
            listNilai!!.adapter = adapterList
        }
//        Toast.makeText(parent.context, "Selected : "+item, Toast.LENGTH_SHORT).show()
    }


}

