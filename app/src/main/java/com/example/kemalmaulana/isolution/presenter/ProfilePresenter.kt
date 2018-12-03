package com.example.kemalmaulana.isolution.presenter

import android.content.Context
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.model.repository.ApiLink
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.model.response.ProfileResponse
import com.example.kemalmaulana.isolution.utils.CoroutineContextProvider
import com.example.kemalmaulana.isolution.view.profile.`interface`.ProfileView
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg

class ProfilePresenter(private val apiRepository: ApiRepository, private val gson: Gson, private val view: ProfileView, private val context: Context, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    val baseUrl: String by lazy {
        val session = context.getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        session.getString("baseUrl", null)
    }

    fun getProfileData(nis: String) {
        view.showLoading()
        GlobalScope.async(contextPool.main) {
            val profileData = bg {
                gson.fromJson(apiRepository.doRequest(baseUrl+ApiLink.getProfileSiswa(nis)), ProfileResponse::class.java)
            }
            view.getData(profileData.await().profil, profileData.await().gambar)
            view.hideLoading()
        }
    }

}