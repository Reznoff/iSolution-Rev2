package com.example.kemalmaulana.isolution.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.kemalmaulana.isolution.Helper.PojoNilai
import com.example.kemalmaulana.isolution.R

class NilaiAdapter(context: Context, nilai: List<PojoNilai>): BaseAdapter() {

    val context = context
    val nilai = nilai

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val nilaiView: View
        nilaiView = LayoutInflater.from(context).inflate(R.layout.adapter_nilai, null)
        val tvKode: TextView = nilaiView.findViewById(R.id.tvKode)
        val tvNama: TextView = nilaiView.findViewById(R.id.tvNama)
        val tvTahun: TextView = nilaiView.findViewById(R.id.tvTahun)
        val tvNilai: TextView = nilaiView.findViewById(R.id.tvNilai)

        val nilaiValues = nilai[position]

//        val resourceId = context.resources.getIdentifier()

        tvKode.text = nilaiValues.kode
        tvNama.text = nilaiValues.nama
        tvTahun.text = nilaiValues.tahunAjaran
        tvNilai.text = nilaiValues.nilai.toString()
        return nilaiView
    }

    override fun getItem(position: Int): Any {
        return nilai[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return nilai.count()
    }

}