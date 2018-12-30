package com.example.kemalmaulana.isolution.model

import android.content.Context
import android.content.SharedPreferences
import com.example.kemalmaulana.isolution.R

object UserSession {
    lateinit var preference: SharedPreferences

    val PREF_NAME: String = "Session"
    fun createSignInSession(context: Context, nomorInduk: String?, hakAkses: String?, baseUrl: String?) {
        preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) ?: return
        with(preference.edit()) {
            putString(context.getString(R.string.nis), nomorInduk)
            putString(context.getString(R.string.base_url), baseUrl)
            putString(context.getString(R.string.hak_akses), hakAkses)
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