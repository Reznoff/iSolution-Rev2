package com.example.kemalmaulana.isolution.Fragment.Report


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.kemalmaulana.isolution.Helper.DummyData

import com.example.kemalmaulana.isolution.R

class ReportSikapFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_report_sikap, container, false)

        val tvPredikatSpiritual: TextView = view.findViewById(R.id.tvPredikatSpiritual)
        val descSpiritual: EditText = view.findViewById(R.id.descSpiritual)
        val tvPredikatSosial: TextView = view.findViewById(R.id.tvPredikatSosial)
        val descSosial: EditText = view.findViewById(R.id.descSosial)

        val spiritual: List<DummyData.SikapSpiritual> = DummyData.spiritual
        val sosial: List<DummyData.SikapSosial> = DummyData.sosial

        tvPredikatSpiritual.text = spiritual[0].predikat.toString()
        descSpiritual.text = Editable.Factory.getInstance().newEditable(spiritual[0].keterangan)

        tvPredikatSosial.text = sosial[0].predikat.toString()
        descSosial.text = Editable.Factory.getInstance().newEditable(sosial[0].keterangan)

        return view
    }


}
