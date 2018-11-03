package com.example.kemalmaulana.isolution.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.R

class SplashActivity : BaseActivity() {

    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val handler = Handler()
        handler.postDelayed({
            var i: Intent
            prefs = this.getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
            try {
                try {
                    //already login
                    val id: String? = prefs.getString(getString(R.string.nis), null)
                    Log.d("Username", id)
                    i = Intent(this, MainActivity::class.java)
                    i.putExtra(getString(R.string.nis), id)
                } catch (e: Exception) {
                    //not login
                    i = Intent(this, LoginActivity::class.java)
                }
                startActivity(i)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, 3000)

    }
}

