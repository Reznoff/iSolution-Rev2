package com.example.kemalmaulana.isolution.presenter

import android.content.Context
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.model.content.Kehadiran
import com.example.kemalmaulana.isolution.model.repository.ApiLink
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.model.response.JadwalResponse
import com.example.kemalmaulana.isolution.utils.CoroutineContextProvider
import com.example.kemalmaulana.isolution.view.jadwal.`interface`.JadwalView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg

class JadwalPresenter(private val apiRepository: ApiRepository, private val gson: Gson, private val view: JadwalView, private val context: Context, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    val baseUrl: String by lazy {
        val session = context.getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        session.getString("baseUrl", null)
    }

    fun getJadwalData(nis: String) {
        view.showLoading()
        GlobalScope.async(contextPool.main) {
            val dataJadwal = bg {
                gson.fromJson(apiRepository.doRequest(baseUrl+ApiLink.getJadwalSiswa(nis)), JadwalResponse::class.java)
            }
            view.showJadwal(dataJadwal.await().jadwal)
            view.hideLoading()
        }
    }
}