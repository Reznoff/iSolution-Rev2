package com.example.kemalmaulana.isolution.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.R

import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private val authUser: String = "999001"
    private val authPass: String = "999001"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.

        sign_in_button.setOnClickListener {
            val username: String = nis.text.toString()
            val password: String = passwords.text.toString()
            if(username.equals(authUser) && password.equals(authPass)) {
                UserSession.createSignInSession(this, username)
                val i = Intent(this, MainActivity::class.java)
                i.putExtra(getString(R.string.nis), username)
                startActivity(i)
            } else {
//                Log.d("-u -p", "$username $password")
                Toast.makeText(this, "Can't Sign-in", Toast.LENGTH_SHORT).show()
            }
        }


    }


}
