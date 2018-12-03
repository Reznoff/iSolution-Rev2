package com.example.kemalmaulana.isolution.view.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.UserSession

class PengumumanSekolahActivity : BaseActivity() {

    private val currentNis by lazy {
        val prefs: SharedPreferences = getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        prefs.getString(getString(R.string.nis), null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman_sekolah)
    }
}
