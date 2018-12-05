package com.example.kemalmaulana.isolution.view.guru.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.content.Guru
import com.example.kemalmaulana.isolution.utils.CircleTransform
import com.squareup.picasso.Picasso

class GuruAdapter(val context: Context, val guru: List<Guru>): RecyclerView.Adapter<GuruAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): Holder {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.adapter_list_guru, parent, false)
        return Holder(rootView)
    }

    override fun getItemCount(): Int {
        return guru.count()
    }

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.bindJadwal(guru[pos], context)
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtNipGuru = itemView.findViewById<TextView>(R.id.txtNipGuru)
        val txtNamaGuru = itemView.findViewById<TextView>(R.id.txtNamaGuru)
        val txtStatusGuru = itemView.findViewById<TextView>(R.id.txtStruktural)
        val imgGuru = itemView.findViewById<ImageView>(R.id.imgGuru)

        fun bindJadwal(guru: Guru, context: Context) {
            txtNipGuru.text = guru.nipGuru
            txtNamaGuru.text = guru.namaGuru
            txtStatusGuru.text = guru.statusGuru
            Picasso.get().load("https://${guru.photo}").error(R.drawable.ic_logo_profile).transform(CircleTransform()).into(imgGuru)
        }
    }
}