package com.example.kemalmaulana.isolution.presenter

import com.example.kemalmaulana.isolution.model.content.Kehadiran
import com.example.kemalmaulana.isolution.model.repository.ApiLink
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.model.response.KehadiranResponse
import com.example.kemalmaulana.isolution.utils.CoroutineContextProvider
import com.example.kemalmaulana.isolution.view.kehadiran.`interface`.KehadiranView
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg

class KehadiranPresenter(private val apiRepository: ApiRepository, private val gson: Gson, private val view: KehadiranView, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getStatusKehadiran(nis: String) {
        view.showLoading()
        GlobalScope.async(contextPool.main){
            val kehadiran = bg {
                gson.fromJson(apiRepository.doRequest(ApiLink.getKehadiranSiswa(nis)), Kehadiran::class.java)
            }
            view.getStatus(kehadiran.await())
            view.hideLoading()
        }
    }
}