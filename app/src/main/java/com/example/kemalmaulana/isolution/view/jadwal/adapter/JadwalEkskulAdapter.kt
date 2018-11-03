package com.example.kemalmaulana.isolution.view.jadwal.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kemalmaulana.isolution.model.DummyData
import com.example.kemalmaulana.isolution.R

class JadwalEkskulAdapter(val context: Context, val jadwal: List<DummyData.JadwalEkskul>): RecyclerView.Adapter<JadwalEkskulAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): Holder {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.adapter_jadwal_ekskul, parent, false)
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
        val textEkskul = itemView.findViewById<TextView>(R.id.textEkskul)
        val textPembimbing = itemView.findViewById<TextView>(R.id.textPembimbing)

        fun bindJadwal(jadwal: DummyData.JadwalEkskul, context: Context) {
            textHari.text = jadwal.hari
            textEkskul.text = jadwal.nama_ekskul
            textPembimbing.text = jadwal.pembimbing
        }
    }
}