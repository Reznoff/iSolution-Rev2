package com.example.kemalmaulana.isolution.presenter

import com.example.kemalmaulana.isolution.model.content.KehadiranSemester
import com.example.kemalmaulana.isolution.model.repository.ApiLink
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.utils.CoroutineContextProvider
import com.example.kemalmaulana.isolution.view.kehadiran.`interface`.KehadiranSemesterView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg

class KehadiranSemesterPresenter(private val apiRepository: ApiRepository, private val gson: Gson, private val view: KehadiranSemesterView, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getKehadiranSemester(nis: String?) {
        view.showLoading()
        GlobalScope.async(contextPool.main) {
            val kehadiran = bg {
                gson.fromJson(apiRepository.doRequest(ApiLink.getKehadiranSemesterSiswa(nis)), KehadiranSemester::class.java)
            }
            view.getEntryData(kehadiran.await())
            view.hideLoading()
        }
    }
}