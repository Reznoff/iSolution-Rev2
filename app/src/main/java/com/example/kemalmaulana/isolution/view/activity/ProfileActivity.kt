package com.example.kemalmaulana.isolution.view.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.model.content.Gambar
import com.example.kemalmaulana.isolution.model.content.Profile
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.presenter.ProfilePresenter
import com.example.kemalmaulana.isolution.view.profile.`interface`.ProfileView
import com.example.kemalmaulana.isolution.utils.genderParser
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), ProfileView {

    val nis: String by lazy {
        val session = getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        session.getString("NIS", null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val presenter = ProfilePresenter(ApiRepository(), Gson(), this)
        presenter.getProfileData(nis)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun getData(profile: Profile, gambar: Gambar) {
        Picasso.get().load("https:${gambar.photo}").into(imgProfile)
        txtNis.text = profile.nis
        txtNama.text = profile.namaLengkap
        txtAlamat.text = profile.alamat
        txtJenisKelamin.text = genderParser(profile.jenisKelamin)

        Log.d("NIS", profile.nis.toString())
        Log.d("image", gambar.photo.toString())
    }
}
