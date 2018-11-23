package com.example.kemalmaulana.isolution.view.profile.fragment


import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.content.Gambar
import com.example.kemalmaulana.isolution.model.content.Profile
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.presenter.ProfilePresenter
import com.example.kemalmaulana.isolution.utils.genderParser
import com.example.kemalmaulana.isolution.utils.ttlParser
import com.example.kemalmaulana.isolution.view.profile.`interface`.ProfileView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile_pribadi.*


class ProfilePribadiFragment : Fragment(), ProfileView {

    private val nis by lazy {
        arguments!!.getString("nis")
    }

    private lateinit var presenter: ProfilePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile_pribadi, container, false)
        presenter = ProfilePresenter(ApiRepository(), Gson(), this)
        presenter.getProfileData(nis)


        return rootView
    }

    private fun setData(data: Profile?) {
        txtNis.text = data?.nis ?: " "
        txtNisn.text = data?.nisn ?: " "
        txtName.text = data?.namaLengkap ?: " "
        txtTtl.text = ttlParser(data?.tempatLahir?: " ", data?.tanggalLahir ?: " ")
        txtJk.text = genderParser(data?.jenisKelamin ?: " ")
        txtAgama.text = data?.agama ?: " "
        txtStatus.text = data?.statusKeluarga ?: " "
        txtAlamat.text = data?.alamat ?: " "
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun getData(profile: Profile, gambar: Gambar) {
        setData(profile)
    }
}
