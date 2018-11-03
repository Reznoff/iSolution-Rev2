package com.example.kemalmaulana.isolution.view.report.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kemalmaulana.isolution.model.DummyData

import com.example.kemalmaulana.isolution.R

class ReportSikapFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_report_sikap, container, false)

        val tvPredikatSpiritual: TextView = view.findViewById(R.id.tvPredikatSpiritual)
        val descSpiritual: TextView = view.findViewById(R.id.descSpiritual)
        val tvPredikatSosial: TextView = view.findViewById(R.id.tvPredikatSosial)
        val descSosial: TextView = view.findViewById(R.id.descSosial)

        val spiritual: List<DummyData.SikapSpiritual> = DummyData.spiritual
        val sosial: List<DummyData.SikapSosial> = DummyData.sosial

        tvPredikatSpiritual.text = spiritual[0].predikat.toString()
        descSpiritual.text = spiritual[0].keterangan

        tvPredikatSosial.text = sosial[0].predikat.toString()
        descSosial.text = sosial[0].keterangan

        return view
    }


}
