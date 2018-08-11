package com.example.kemalmaulana.isolution.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.kemalmaulana.isolution.Helper.PojoJadwalPelajaran
import com.example.kemalmaulana.isolution.R

class PelajaranAdapter(context: Context, pel: List<PojoJadwalPelajaran>): BaseAdapter() {

    val context = context
    val pel = pel

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup?): View {
        val pelView: View = LayoutInflater.from(context).inflate(R.layout.adapter_pelajaran, null)


        return pelView
    }

    override fun getItem(pos: Int): Any {
        return pel[pos]
    }

    override fun getItemId(pos: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return pel.count()
    }
}