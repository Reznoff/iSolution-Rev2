package com.example.kemalmaulana.isolution.view.kehadiran.fragment

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.view.kehadiran.adapter.KehadiranAdapter
import com.example.kemalmaulana.isolution.model.DummyData
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.content.Gambar
import com.example.kemalmaulana.isolution.model.content.KehadiranSemester
import com.example.kemalmaulana.isolution.model.content.Profile
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.presenter.KehadiranSemesterPresenter
import com.example.kemalmaulana.isolution.view.kehadiran.`interface`.KehadiranSemesterView
import com.example.kemalmaulana.isolution.view.profile.`interface`.ProfileView
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_kehadiran.*

class KehadiranFragment : Fragment(), KehadiranSemesterView, ProfileView {

    private lateinit var adapter: KehadiranAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var contentLayout: ConstraintLayout
    private lateinit var loadingLayout: ConstraintLayout
    private val nis by lazy {
        val prefs: SharedPreferences = activity!!.getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        prefs.getString(getString(R.string.nis), null)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_kehadiran, container, false)

        contentLayout = rootView.findViewById(R.id.contentLayout)
        loadingLayout = rootView.findViewById(R.id.loadingLayout)

        val presenter = KehadiranSemesterPresenter(ApiRepository(), Gson(), this, requireContext())
        presenter.getKehadiranSemester(nis)

        initToolbar(rootView)
//        usePieChart(rootView, entries)
        recyclerViewAdapter(rootView)

        return rootView
    }

    override fun showLoading() {
        contentLayout.visibility = View.GONE
        loadingLayout.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        contentLayout.visibility = View.VISIBLE
        loadingLayout.visibility = View.GONE
    }

    override fun getEntryData(kehadiran: KehadiranSemester) {
        val colors: MutableList<Int> = mutableListOf(
                Color.rgb(64, 89, 128),
                Color.rgb(149, 165, 124),
                Color.rgb(217, 184, 162),
                Color.rgb(191, 134, 134),
                Color.rgb(179, 48, 80))
        //Setting dataset
        val pieChart = pieChart
        val entries: ArrayList<PieEntry> = arrayListOf()
        entries.add(PieEntry(kehadiran.kehadiranSiswa.toFloat(), "Hadir", 0))
        entries.add(PieEntry(kehadiran.siswaSakit.toFloat(), "Sakit", 1))
        entries.add(PieEntry(kehadiran.siswaIzin.toFloat(), "Izin", 2))
        entries.add(PieEntry(kehadiran.siswaTanpaKeterangan.toFloat(), "Tanpa Keterangan", 3))

        val dataset = PieDataSet(entries, null)
        val data = PieData(dataset)

        //Setting the permission
//        data.setValueFormatter(PercentFormatter) //for make the value percentage
        pieChart.data = data
        val desc = Description()
        desc.text = "Diagram kehadiran siswa $nis"
        pieChart.description = desc
        pieChart.isDrawHoleEnabled = true
        pieChart.transparentCircleRadius = 58f
        pieChart.holeRadius = 58f
        dataset.colors = colors //Color References
//        dataset.colors = ColorTemplate.JOYFUL_COLORS
        data.setValueTextSize(13f)
        data.setValueTextColor(Color.BLACK)

    }

    override fun getData(profile: Profile, gambar: Gambar) {

    }

    private fun recyclerViewAdapter(rootView: View) {
        val listLastRecord: RecyclerView = rootView.findViewById(R.id.listLastRecord)
        adapter = KehadiranAdapter(rootView.context, DummyData.daftarKehadiran)
        listLastRecord.adapter = adapter
        adapter.notifyDataSetChanged()

        val layoutManager = LinearLayoutManager(rootView.context)
        listLastRecord.layoutManager = layoutManager
        listLastRecord.setHasFixedSize(true)
    }

//    private fun usePieChart(rootView: View, entries: ArrayList<PieEntry>) {
//        //set mutable color for dataset
//        val colors: MutableList<Int> = mutableListOf(
//                Color.rgb(64, 89, 128),
//                Color.rgb(149, 165, 124),
//                Color.rgb(217, 184, 162),
//                Color.rgb(191, 134, 134),
//                Color.rgb(179, 48, 80))
//        //Setting dataset
//        val pieChart: PieChart = rootView.findViewById(R.id.pieChart)
//
//
//        val dataset = PieDataSet(entries, null)
//        val data = PieData(dataset)
//
//        //Setting the permission
////        data.setValueFormatter(PercentFormatter) //for make the value percentage
//        pieChart.data = data
//        val desc = Description()
//        desc.text = "Diagram kehadiran siswa $nis"
//        pieChart.description = desc
//        pieChart.isDrawHoleEnabled = true
//        pieChart.transparentCircleRadius = 58f
//        pieChart.holeRadius = 58f
//        dataset.colors = colors //Color References
////        dataset.colors = ColorTemplate.JOYFUL_COLORS
//        data.setValueTextSize(13f)
//        data.setValueTextColor(Color.BLACK)
//    }

    private fun initToolbar(rootView: View) {
        toolbar = rootView.findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.kehadiran)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.subtitle = getString(R.string.subtitle_kehadiran)
        toolbar.setSubtitleTextColor(Color.WHITE)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
