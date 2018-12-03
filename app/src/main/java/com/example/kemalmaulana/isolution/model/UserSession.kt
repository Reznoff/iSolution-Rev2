package com.example.kemalmaulana.isolution.model

import android.content.Context
import android.content.SharedPreferences
import com.example.kemalmaulana.isolution.R

object UserSession {
    lateinit var preference: SharedPreferences
    val PREF_NAME: String = "Session"
    fun createSignInSession(context: Context, nis: String, baseUrl: String) {
        preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) ?: return
        with(preference.edit()) {
            putString(context.getString(R.string.nis), nis)
            putString(context.getString(R.string.base_url), baseUrl)
//            putString("kelas", kelas)
//            commit()
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