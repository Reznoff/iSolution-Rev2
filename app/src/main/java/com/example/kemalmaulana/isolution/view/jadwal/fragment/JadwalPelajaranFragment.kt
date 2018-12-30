package com.example.kemalmaulana.isolution.view.jadwal.fragment


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.view.jadwal.adapter.JadwalPelajaranAdapter

import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.model.content.Jadwal
import com.example.kemalmaulana.isolution.model.content.KehadiranJadwal
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.presenter.JadwalPresenter
import com.example.kemalmaulana.isolution.view.jadwal.`interface`.JadwalView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_jadwal_pelajaran.*

class JadwalPelajaranFragment : Fragment(), JadwalView {

    lateinit var adapter: JadwalPelajaranAdapter
    lateinit var presenter: JadwalPresenter
    lateinit var listPelajaran: RecyclerView
    lateinit var loadingLayout: ConstraintLayout

    private val currentNis by lazy {
        val prefs: SharedPreferences = requireContext().getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        prefs.getString(getString(R.string.nis), null)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_jadwal_pelajaran, container, false)
        listPelajaran = rootView.findViewById(R.id.listPelajaran)
        loadingLayout = rootView.findViewById(R.id.loadingLayout)

        presenter = JadwalPresenter(ApiRepository(), Gson(), this, requireContext())
        presenter.getJadwalData(currentNis)

        return rootView
    }

    override fun showLoading() {
        loadingLayout.visibility = View.VISIBLE
        listPelajaran.visibility = View.GONE
    }

    override fun hideLoading() {
        loadingLayout.visibility = View.GONE
        listPelajaran.visibility = View.VISIBLE
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
