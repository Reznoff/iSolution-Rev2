package com.example.kemalmaulana.isolution.view.kehadiran.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kemalmaulana.isolution.model.DummyData
import com.example.kemalmaulana.isolution.R

class KehadiranAdapter(val context: Context, val listKehadiran: List<DummyData.Kehadiran>): RecyclerView.Adapter<KehadiranAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_kehadiran, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listKehadiran.count()
    }

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.bindKehadiran(listKehadiran[pos], context)

    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.textName)
        val textTime = itemView.findViewById<TextView>(R.id.textTime)
        val textStatus = itemView.findViewById<TextView>(R.id.textStatus)

        fun bindKehadiran(kehadiran: DummyData.Kehadiran, context: Context) {
            textName.text = kehadiran.nama
            textTime.text = kehadiran.waktu
            textStatus.text = kehadiran.status
        }

    }

}