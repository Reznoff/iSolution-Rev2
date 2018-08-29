package com.example.kemalmaulana.isolution.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kemalmaulana.isolution.Helper.DummyData
import com.example.kemalmaulana.isolution.R

class PaymentMonthlyAdapter(val context: Context, val payment: List<DummyData.TagihanBulanan>): RecyclerView.Adapter<PaymentMonthlyAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): Holder {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.adapter_payment_montly, parent, false)
        return Holder(rootView)
    }

    override fun getItemCount(): Int {
        return payment.count()
    }

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.bindPayment(payment[pos], context)
    }


    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textMonth: TextView = itemView.findViewById(R.id.textMonth)
        val textKeamanan: TextView = itemView.findViewById(R.id.textKeamanan)
        val textKebersihan: TextView = itemView.findViewById(R.id.textKebersihan)
        val textLaundry: TextView = itemView.findViewById(R.id.textLaundry)
        val textPraktikum: TextView = itemView.findViewById(R.id.textPraktikum)
        val textDate: TextView = itemView.findViewById(R.id.textDate)

        fun bindPayment(payment: DummyData.TagihanBulanan, context: Context) {
            textMonth.text = payment.bulan
            textKeamanan.text = boolToEmoticon(payment.keamanan)
            textKebersihan.text = boolToEmoticon(payment.kebersihan)
            textLaundry.text = boolToEmoticon(payment.laundry)
            textPraktikum.text = boolToEmoticon(payment.praktikum)
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