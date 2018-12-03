package com.example.kemalmaulana.isolution.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.kemalmaulana.isolution.BuildConfig

import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.R

import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    private val authUser: String = "999001"
    private val authPass: String = "999001"
    private lateinit var sekolahSpinner: Spinner
    private lateinit var sekolahAdapter: SpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.

        var defaultBaseUrl = BuildConfig.BASE_URL

        sekolahSpinner = findViewById(R.id.spinnerSekolah)

        val sekolahNama = listOf("SMK WB 1", "SMK WB 2", "SMP WB", "Demo")
        sekolahAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sekolahNama)
        sekolahSpinner.adapter = sekolahAdapter
        sekolahSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position) {
                    0 -> defaultBaseUrl = BuildConfig.SMK_WB1
                    1 -> defaultBaseUrl = BuildConfig.SMK_WB2
                    2 -> defaultBaseUrl = BuildConfig.SMP_WB
                    3 -> defaultBaseUrl = BuildConfig.BASE_URL
                }
            }

        }


        sign_in_button.setOnClickListener {
            val username: String = nis.text.toString()
            val password: String = passwords.text.toString()
            if(username == authUser && password == authPass) {
                UserSession.createSignInSession(this, username, defaultBaseUrl)
                val i = Intent(this, MainActivity::class.java)
                i.putExtra(getString(R.string.nis), username)
                startActivity(i)
            } else {
                Toast.makeText(this, "Can't Sign-in", Toast.LENGTH_SHORT).show()
            }
        }


    }


}
