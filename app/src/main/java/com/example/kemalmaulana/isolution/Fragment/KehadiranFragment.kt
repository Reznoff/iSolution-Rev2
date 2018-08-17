package com.example.kemalmaulana.isolution.Fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class KehadiranFragment : Fragment() {

    companion object {
        fun newInstance(): KehadiranFragment {
            return KehadiranFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_kehadiran, container, false)
        var colors: MutableList<Int> = mutableListOf(
                Color.rgb(64, 89, 128),
                Color.rgb(149, 165, 124),
                Color.rgb(217, 184, 162),
                Color.rgb(191, 134, 134),
                Color.rgb(179, 48, 80))
        //Setting dataset
        var pieChart: PieChart = rootView.findViewById(R.id.pieChart)
        var entries: ArrayList<PieEntry> = arrayListOf()
        entries.add(PieEntry(8f, "Hadir", 0))
        entries.add(PieEntry(1f, "Tidak Masuk", 1))
        entries.add(PieEntry(2f, "Sakit", 2))

        val dataset = PieDataSet(entries,getString(R.string.kehadiran))
        val data = PieData(dataset)

        //Setting the permission
//        data.setValueFormatter(PercentFormatter) //for make the value percentage
        pieChart.data = data
        val desc = Description()
        desc.text = "Diagram kehadiran siswa x"
        pieChart.description = desc
        pieChart.isDrawHoleEnabled = true
        pieChart.transparentCircleRadius = 58f
        pieChart.holeRadius = 58f
        dataset.colors = colors //Color References
        data.setValueTextSize(13f)
        data.setValueTextColor(Color.BLACK)

        return rootView
    }

}
