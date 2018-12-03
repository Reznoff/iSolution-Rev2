package com.example.kemalmaulana.isolution.view.jadwal.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.content.KehadiranJadwal

class JadwalPelajaranAdapter(val context: Context, val jadwal: List<KehadiranJadwal>): RecyclerView.Adapter<JadwalPelajaranAdapter.Holder>() {

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
        val indicatorStatus = itemView.findViewById<View>(R.id.indicatorCurrent)
        val txtNamaMapel = itemView.findViewById<TextView>(R.id.txtNamaMapel)
        val txtJamAwal = itemView.findViewById<TextView>(R.id.txtJamAwal)
        val txtJamAkhir = itemView.findViewById<TextView>(R.id.txtJamAkhir)
        val txtNamaGuru = itemView.findViewById<TextView>(R.id.txtNamaGuru)

        @SuppressLint("SetTextI18n")
        fun bindJadwal(jadwal: KehadiranJadwal, context: Context) {
            when(jadwal.kehadiran) {
                "1" -> indicatorStatus.background = ContextCompat.getDrawable(context, R.drawable.circle_view_red)
                "2" -> indicatorStatus.background = ContextCompat.getDrawable(context, R.drawable.circle_view_green)
                "3" -> indicatorStatus.background = ContextCompat.getDrawable(context, R.drawable.circle_view_blue)
                "4" -> indicatorStatus.background = ContextCompat.getDrawable(context, R.drawable.circle_view_yellow)
                else -> indicatorStatus.background = ContextCompat.getDrawable(context, R.drawable.circle_view_unspecified)
            }
            txtNamaMapel.text = jadwal.namaPelajaran
            txtJamAwal.text = jadwal.jamAwal
            txtJamAkhir.text = jadwal.jamAkhir
            txtNamaGuru.text = jadwal.pengajar
        }


    }
}