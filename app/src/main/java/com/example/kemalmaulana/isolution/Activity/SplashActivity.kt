package com.example.kemalmaulana.isolution.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.kemalmaulana.isolution.R

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler: Handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 3000);
    }
}

