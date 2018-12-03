package com.example.kemalmaulana.isolution.presenter

import android.content.Context
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.model.content.Kehadiran
import com.example.kemalmaulana.isolution.model.repository.ApiLink
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.model.response.KehadiranResponse
import com.example.kemalmaulana.isolution.model.response.ProfileResponse
import com.example.kemalmaulana.isolution.utils.CoroutineContextProvider
import com.example.kemalmaulana.isolution.view.kehadiran.`interface`.KehadiranView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg

class KehadiranPresenter(private val apiRepository: ApiRepository, private val gson: Gson, private val view: KehadiranView, private val context: Context, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    val baseUrl: String by lazy {
        val session = context.getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        session.getString("baseUrl", null)
    }

    fun getStatusKehadiran(nis: String) {
        view.showLoading()
        GlobalScope.async(contextPool.main){
            val kehadiran = bg {
                gson.fromJson(apiRepository.doRequest(baseUrl+ApiLink.getKehadiranSiswa(nis)), Kehadiran::class.java)
            }
            val profile = bg {
                gson.fromJson(apiRepository.doRequest(baseUrl+ApiLink.getProfileSiswa(nis)), ProfileResponse::class.java)
            }
            view.getProfile(profile.await().profil, profile.await().gambar)
            view.getStatus(kehadiran.await())
            view.hideLoading()
        }
    }
}