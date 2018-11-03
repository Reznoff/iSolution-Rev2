package com.example.kemalmaulana.isolution.view.report.adapter

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.kemalmaulana.isolution.model.DummyData
import com.example.kemalmaulana.isolution.R

class ReportKeterampilanAdapter(val context: Context, val nilai: List<DummyData.Nilai>): BaseAdapter() {

    lateinit var builder: AlertDialog.Builder

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val nilaiView: View
        nilaiView = LayoutInflater.from(context).inflate(R.layout.adapter_report_keterampilan, null)
//        val tvKode: TextView = nilaiView.findViewById(R.id.tvKode)
        val tvNama: TextView = nilaiView.findViewById(R.id.tvNama)
        val tvTahun: TextView = nilaiView.findViewById(R.id.tvTahun)
        val tvKkm: TextView = nilaiView.findViewById(R.id.tvKkm)
        val tvNilai: TextView = nilaiView.findViewById(R.id.tvNilai)
        val tvPredikat: TextView = nilaiView.findViewById(R.id.tvPredikat)
        val cardKeterampilan: CardView = nilaiView.findViewById(R.id.cardKeterampilan)
        val nilaiValues: DummyData.Nilai = nilai[position]

//        val resourceId = context.resources.getIdentifier()

//        tvKode.text = nilaiValues.kode
        tvNama.text = nilaiValues.nama
        tvTahun.text = nilaiValues.tahunAjaran
        tvKkm.text = nilaiValues.kkm.toString()
        tvNilai.text = nilaiValues.nilai.toString()
        tvPredikat.text = nilaiValues.predikat.toString()
        cardKeterampilan.setOnClickListener { view ->
            dialog(context)
        }

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

    @TargetApi(Build.VERSION_CODES.M)
    fun dialog(context: Context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert)
        } else {
            builder = AlertDialog.Builder(context)
        }
        builder
                .setTitle(R.string.keterangan)
                .setMessage(R.string.lorem)
                .show()
    }

}