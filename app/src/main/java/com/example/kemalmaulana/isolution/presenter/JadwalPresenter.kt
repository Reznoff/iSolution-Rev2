package com.example.kemalmaulana.isolution.presenter

import com.example.kemalmaulana.isolution.model.repository.ApiLink
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.model.response.JadwalResponse
import com.example.kemalmaulana.isolution.utils.CoroutineContextProvider
import com.example.kemalmaulana.isolution.view.profile.`interface`.JadwalView
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg

class JadwalPresenter(private val apiRepository: ApiRepository, private val gson: Gson, private val view: JadwalView, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getJadwalData(nis: String) {
        view.showLoading()
        CoroutineScope(contextPool.main).async {
            val dataJadwal = bg {
                gson.fromJson(apiRepository.doRequest(ApiLink.getJadwalSiswa(nis)), JadwalResponse::class.java)
            }
            view.showJadwal(dataJadwal.await().jadwal)
            view.hideLoading()
        }
    }
}