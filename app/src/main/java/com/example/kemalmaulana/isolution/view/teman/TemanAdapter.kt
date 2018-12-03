package com.example.kemalmaulana.isolution.view.teman

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.content.Teman
import com.example.kemalmaulana.isolution.utils.CircleTransform
import com.squareup.picasso.Picasso

class TemanAdapter(val context: Context, val teman: List<Teman>): RecyclerView.Adapter<TemanAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): Holder {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.adapter_list_teman, parent, false)
        return Holder(rootView)
    }

    override fun getItemCount(): Int {
        return teman.count()
    }

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.bindJadwal(teman[pos], context)
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtNisTeman = itemView.findViewById<TextView>(R.id.txtNisTeman)
        val txtNamaTeman = itemView.findViewById<TextView>(R.id.txtNamaTeman)
        val imgTeman = itemView.findViewById<ImageView>(R.id.imgTeman)

        fun bindJadwal(teman: Teman, context: Context) {
            txtNisTeman.text = teman.nisTeman
            txtNamaTeman.text = teman.namaTeman
            Picasso.get().load("https://").error(R.drawable.ic_logo_profile).transform(CircleTransform()).into(imgTeman)
        }
    }
}