package com.example.kemalmaulana.isolution.view.profile.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.content.Gambar
import com.example.kemalmaulana.isolution.model.content.Profile
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.presenter.ProfilePresenter
import com.example.kemalmaulana.isolution.view.profile.`interface`.ProfileView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile_orang_tua.*


class ProfileOrangTuaFragment : Fragment(), ProfileView {

    private val nis by lazy {
        arguments!!.getString("nis")
    }

    private lateinit var presenter: ProfilePresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile_orang_tua, container, false)

        presenter = ProfilePresenter(ApiRepository(), Gson(), this)
        presenter.getProfileData(nis)
        return rootView
    }

    private fun setData(data: Profile) {
        txtAyah.text = data.namaAyah
        txtIbu.text = data.namaIbu
        txtKontak.text = data.kontak
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun getData(profile: Profile, gambar: Gambar) {
        setData(profile)
    }
}
