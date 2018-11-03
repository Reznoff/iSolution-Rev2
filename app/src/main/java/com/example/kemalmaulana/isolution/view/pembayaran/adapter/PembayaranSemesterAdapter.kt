package com.example.kemalmaulana.isolution.view.pembayaran.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kemalmaulana.isolution.model.DummyData
import com.example.kemalmaulana.isolution.R

class PembayaranSemesterAdapter(val context: Context, val payment: List<DummyData.TagihanSemesteran>): RecyclerView.Adapter<PembayaranSemesterAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): Holder {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.adapter_payment_semester, parent, false)
        return Holder(rootView)
    }

    override fun getItemCount(): Int {
        return payment.count()
    }

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.bindPayment(payment[pos], context)
    }


    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        private val textAdmin: TextView = itemView.findViewById(R.id.statAdmin)
        private val textUas: TextView = itemView.findViewById(R.id.statUas)
        private val textPembangunan: TextView = itemView.findViewById(R.id.statPembangunan)
        val textDate: TextView = itemView.findViewById(R.id.textDate)

        fun bindPayment(payment: DummyData.TagihanSemesteran, context: Context) {
            textTitle.text = payment.semester
            textAdmin.text = boolToEmoticon(payment.adminPerpus)
            textUas.text = boolToEmoticon(payment.biayaUas)
            textPembangunan.text = boolToEmoticon(payment.pembangunan)
            textDate.text = payment.deadline
        }


        private fun boolToEmoticon(value: Boolean?): String? {
            return if("true" == value.toString()) {
                "O"
            } else {
                "X"
            }
        }

    }
}