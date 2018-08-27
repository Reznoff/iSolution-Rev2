package com.example.kemalmaulana.isolution.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kemalmaulana.isolution.Helper.DummyData
import com.example.kemalmaulana.isolution.R

class PaymentSingleAdapter(val context: Context, val payment: List<DummyData.TagihanTunggal>): RecyclerView.Adapter<PaymentSingleAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): Holder {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.adapter_payment_single, parent, false)
        return Holder(rootView)
    }

    override fun getItemCount(): Int {
        return payment.count()
    }


    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.bindPayment(payment[pos], context)
    }


    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textKomponen: TextView = itemView.findViewById(R.id.textKomponen)
        private val textPeriode: TextView = itemView.findViewById(R.id.textPeriode)
        private val textStat: TextView = itemView.findViewById(R.id.textStat)
        val textDate: TextView = itemView.findViewById(R.id.textDate)

        @SuppressLint("SetTextI18n")
        fun bindPayment(payment: DummyData.TagihanTunggal, context: Context) {
            val periode: String = payment.periode
            textKomponen.text = payment.komponen
            textPeriode.text = "Periode Pembayaran $periode"
            textStat.text = boolToEmoticon(payment.status)
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