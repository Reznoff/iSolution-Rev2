package com.example.kemalmaulana.isolution.view.jadwal.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kemalmaulana.isolution.model.DummyData
import com.example.kemalmaulana.isolution.R

class JadwalPelajaranAdapter(val context: Context, val jadwal: List<DummyData.JadwalPelajaran>): RecyclerView.Adapter<JadwalPelajaranAdapter.Holder>() {

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
        val textKelas = itemView.findViewById<TextView>(R.id.textKelas)
        val textTahunPelajaran = itemView.findViewById<TextView>(R.id.textTahunPelajaran)
        val textMapel = itemView.findViewById<TextView>(R.id.textMapel)
        val textPengajar = itemView.findViewById<TextView>(R.id.textPengajar)
        val textDurasi = itemView.findViewById<TextView>(R.id.textDurasi)

        @SuppressLint("SetTextI18n")
        fun bindJadwal(jadwal: DummyData.JadwalPelajaran, context: Context) {
            textHari.text = jadwal.hari
            textKelas.text = jadwal.kelas
            textTahunPelajaran.text = jadwal.tahun_pelajaran
            textMapel.text = jadwal.nama_pelajaran
            textPengajar.text = jadwal.pengajar
            textDurasi.text = "${jadwal.jam_masuk} - ${jadwal.jam_keluar}"
        }


    }
}