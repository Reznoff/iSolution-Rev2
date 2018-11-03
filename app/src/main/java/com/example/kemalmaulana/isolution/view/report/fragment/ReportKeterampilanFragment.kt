package com.example.kemalmaulana.isolution.view.report.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import com.example.kemalmaulana.isolution.view.report.adapter.ReportKeterampilanAdapter
import com.example.kemalmaulana.isolution.model.DummyData

import com.example.kemalmaulana.isolution.R

class ReportKeterampilanFragment : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var adapterListReport: ReportKeterampilanAdapter
    lateinit var adapterSpinner: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_report_keterampilan, container, false)
        val spinnerTahunAjaran: Spinner = view.findViewById(R.id.spinnerTahunAjaran)
        spinnerTahunAjaran.onItemSelectedListener = this

        //setting the spinner
        val tahunAjaran: ArrayList<String> = arrayListOf()
        val hashSet = HashSet<String>()
        for (i: Int in 0 until DummyData.nilai.count()) {
            tahunAjaran.add(DummyData.nilai[i].tahunAjaran)
        }
        hashSet.addAll(tahunAjaran)
        tahunAjaran.clear()
        tahunAjaran.addAll(hashSet)
        adapterSpinner = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, tahunAjaran)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTahunAjaran.adapter = adapterSpinner

        return view
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        val item: String = parent!!.getItemAtPosition(pos).toString()
        val listNilai = parent.rootView!!.findViewById<ListView>(R.id.listNilai)
        if(item.isNotEmpty()) {
            adapterListReport = ReportKeterampilanAdapter(requireContext(), DummyData.nilai)
            listNilai!!.adapter = adapterListReport
        }
//        Toast.makeText(parent.context, "Selected : "+item, Toast.LENGTH_SHORT).show()
    }
}
