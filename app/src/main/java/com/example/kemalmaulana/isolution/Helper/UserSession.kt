package com.example.kemalmaulana.isolution.Helper

import android.content.Context
import android.content.SharedPreferences

object UserSession {
    lateinit var preference: SharedPreferences
    var PREF_NAME: String = "Session"
    fun createSignInSession(context: Context, nis: String) {
        preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) ?: return
        with(preference.edit()) {
            putString(context.getString(com.example.kemalmaulana.isolution.R.string.nis), nis)
            commit()
        }
    }

    fun logoutSession(context: Context) {
        preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) ?: return
        with(preference.edit()) {
            clear()
            commit()
        }
    }
}