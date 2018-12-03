package com.example.kemalmaulana.isolution.presenter

import android.content.Context
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.model.repository.ApiLink
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.model.response.TemanResponse
import com.example.kemalmaulana.isolution.utils.CoroutineContextProvider
import com.example.kemalmaulana.isolution.view.teman.TemanView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.jetbrains.anko.coroutines.experimental.bg

class TemanPresenter(private val apiRepository: ApiRepository, private val gson: Gson, private val view: TemanView, private val context: Context, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    val baseUrl: String by lazy {
        val session = context.getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        session.getString("baseUrl", null)
    }

    fun getListTeman(nis: String) {
        view.showLoading()
        GlobalScope.async(contextPool.main) {
            val listTeman = bg {
                gson.fromJson(apiRepository.doRequest(baseUrl+ ApiLink.getListTeman(nis)), TemanResponse::class.java)
            }
            listTeman.await().teman?.let { view.getListTeman(it) }
            view.hideLoading()
        }
    }
}