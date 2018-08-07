package com.example.kemalmaulana.isolution.Helper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.example.kemalmaulana.isolution.Activity.LoginActivity
import com.example.kemalmaulana.isolution.Activity.MainActivity

open class UserSession(context: Context) {

    var prefs: SharedPreferences
    var editor: SharedPreferences.Editor
    var thisContext: Context

    val PRIVATE_MODE: Int = 0
    val PREFER_NAME: String = "Session"
    val IS_USER_LOGGED_IN: String = "IsLogin"
    val KEY_NAME: String = "admin"
    val KEY_PASS: String = "admin"

    init {
        thisContext = context
        prefs = thisContext.getSharedPreferences(PREFER_NAME, PRIVATE_MODE)
        editor = prefs.edit()
    }

    open fun createUserLoginSession(keyName: String, keyPass: String) {
        if(keyName.equals(KEY_NAME) && keyPass.equals(KEY_PASS)) {
            editor.putBoolean(IS_USER_LOGGED_IN, true)
            editor.putString(KEY_NAME, keyName)
            editor.putString(KEY_PASS, keyPass)
            editor.commit()
        }
    }

    open fun isUserLoggedIn(): Boolean {
        Log.d("IsLogged", prefs.getBoolean(IS_USER_LOGGED_IN, true).toString())
        return prefs.getBoolean(IS_USER_LOGGED_IN, true)
    }

    open fun checkLogin(): Boolean {
        if(!this.isUserLoggedIn()) {
            val i: Intent = Intent(thisContext, MainActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            thisContext.startActivity(i)
            return true
        } else {
            return false
        }
    }

    open fun getUserDetails(): HashMap<String, String> {
        val user: HashMap<String, String> = HashMap()
        user.put(KEY_NAME, prefs.getString(KEY_NAME, null))
        return user
    }

    open fun logoutUser() {
        editor.clear()
        editor.commit()

        val i: Intent = Intent(thisContext, LoginActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        thisContext.startActivity(i)
    }


}