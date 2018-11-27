package com.example.kemalmaulana.isolution.view.jadwal.fragment


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.view.jadwal.adapter.JadwalPelajaranAdapter

import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.model.content.Jadwal
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.presenter.JadwalPresenter
import com.example.kemalmaulana.isolution.view.jadwal.`interface`.JadwalView
import com.google.gson.Gson

class JadwalPelajaranFragment : Fragment(), JadwalView {

    lateinit var adapter: JadwalPelajaranAdapter
    lateinit var presenter: JadwalPresenter
    lateinit var listPelajaran: RecyclerView

    private val currentNis by lazy {
        val prefs: SharedPreferences = activity!!.getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        prefs.getString(getString(R.string.nis), null)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_jadwal_pelajaran, container, false)

        presenter = JadwalPresenter(ApiRepository(), Gson(), this)
        presenter.getJadwalData(currentNis)

        listPelajaran = rootView.findViewById(R.id.listPelajaran)
//        adapter = JadwalPelajaranAdapter(rootView.context, DummyData.jadwalPelajaran)
//        listPelajaran.adapter = adapter


        return rootView
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showJadwal(jadwal: List<Jadwal>) {
        adapter = JadwalPelajaranAdapter(requireContext(), jadwal)
        listPelajaran.adapter = adapter
        adapter.notifyDataSetChanged()

        val layoutManager = LinearLayoutManager(requireContext())
        listPelajaran.layoutManager = layoutManager
        listPelajaran.setHasFixedSize(true)
    }
}
