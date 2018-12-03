package com.example.kemalmaulana.isolution.presenter

import android.content.Context
import com.example.kemalmaulana.isolution.BuildConfig
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.model.content.KehadiranSemester
import com.example.kemalmaulana.isolution.model.repository.ApiLink
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.utils.CoroutineContextProvider
import com.example.kemalmaulana.isolution.view.kehadiran.`interface`.KehadiranSemesterView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg

class KehadiranSemesterPresenter(private val apiRepository: ApiRepository, private val gson: Gson, private val view: KehadiranSemesterView, private val context: Context, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    val baseUrl: String by lazy {
        val session = context.getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        session.getString("baseUrl", null)
    }

    fun getKehadiranSemester(nis: String?) {
        view.showLoading()
        GlobalScope.async(contextPool.main) {
            val kehadiran = bg {
                gson.fromJson(apiRepository.doRequest(baseUrl + ApiLink.getKehadiranSemesterSiswa(nis)), KehadiranSemester::class.java)
            }
            view.getEntryData(kehadiran.await())
            view.hideLoading()
        }
    }
}