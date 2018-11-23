package com.example.kemalmaulana.isolution.view.jadwal.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.content.Jadwal
import com.example.kemalmaulana.isolution.utils.hariParser
import com.example.kemalmaulana.isolution.utils.jadwalParser

class JadwalPelajaranAdapter(val context: Context, val jadwal: List<Jadwal>): RecyclerView.Adapter<JadwalPelajaranAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): Holder {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.adapter_jadwal_pelajaran, parent, false)
        return Holder(rootView)
    }

    override fun getItemCount(): Int {
        return jadwal.count()
    }

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.bindJadwal(jadwal[pos], context)
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textHari = itemView.findViewById<TextView>(R.id.textHari)
        val textMapel = itemView.findViewById<TextView>(R.id.textMapel)
        val textDurasi = itemView.findViewById<TextView>(R.id.textDurasi)

        @SuppressLint("SetTextI18n")
        fun bindJadwal(jadwal: Jadwal, context: Context) {
            textHari.text = hariParser(jadwal.hari)
            textMapel.text = jadwal.namaPelajaran
            textDurasi.text = jadwalParser(jadwal.jamAwal, jadwal.jamAkhir)
        }


    }
}