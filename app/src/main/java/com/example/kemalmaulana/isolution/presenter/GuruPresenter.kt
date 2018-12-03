package com.example.kemalmaulana.isolution.presenter

import android.content.Context
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.model.repository.ApiLink
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.model.response.GuruResponse
import com.example.kemalmaulana.isolution.utils.CoroutineContextProvider
import com.example.kemalmaulana.isolution.view.guru.interfaces.GuruView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg

class GuruPresenter(private val apiRepository: ApiRepository, private val gson: Gson, private val view: GuruView, private val context: Context, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    val baseUrl: String by lazy {
        val session = context.getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        session.getString("baseUrl", null)
    }

    fun getListGuru() {
        view.showLoading()
        GlobalScope.async(contextPool.main) {
            val listGuru = bg {
                gson.fromJson(apiRepository.doRequest(baseUrl+ ApiLink.getListGuru()), GuruResponse::class.java)
            }
            listGuru.await().data?.let { view.getListGuru(it) }
            view.hideLoading()
        }
    }

}