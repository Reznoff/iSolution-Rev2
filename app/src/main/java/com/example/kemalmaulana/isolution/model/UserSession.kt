package com.example.kemalmaulana.isolution.model

import android.content.Context
import android.content.SharedPreferences
import com.example.kemalmaulana.isolution.R

object UserSession {
    lateinit var preference: SharedPreferences

    val PREF_NAME: String = "Session"
    fun createSignInSession(context: Context, nis: String?, idKelas: String?, kelas: String?, baseUrl: String?) {
        preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) ?: return
        with(preference.edit()) {
            putString(context.getString(R.string.nis), nis)
            putString(context.getString(R.string.base_url), baseUrl)
            putString(context.getString(R.string.idKelas), idKelas)
            putString(context.getString(R.string.kelas), kelas)
//            commit()
            apply()
        }
    }

    fun setFcmToken(context: Context, token: String?) {
        preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) ?: return
        with(preference.edit()) {
            putString("fcmToken", token)
            apply()
        }
    }

    fun logoutSession(context: Context) {
        preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) ?: return
        with(preference.edit()) {
            clear()
//            commit()
            apply()
        }
    }
}