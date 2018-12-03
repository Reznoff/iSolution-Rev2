package com.example.kemalmaulana.isolution.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.kemalmaulana.isolution.BuildConfig

import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.repository.ApiLink

import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject


class LoginActivity : BaseActivity() {

    private val authUser: String = "999001"
    private val authPass: String = "999001"
    private lateinit var sekolahSpinner: Spinner
    private lateinit var sekolahAdapter: SpinnerAdapter
    private lateinit var queue: RequestQueue
    private lateinit var container: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.

        container = findViewById(R.id.linearLayout2)
        queue = Volley.newRequestQueue(this)
        var defaultBaseUrl = BuildConfig.BASE_URL

        sekolahSpinner = findViewById(R.id.spinnerSekolah)

        val sekolahNama = listOf("SMK WB 1", "SMK WB 2", "SMP WB", "Demo")
        sekolahAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sekolahNama)
        sekolahSpinner.adapter = sekolahAdapter
        sekolahSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> defaultBaseUrl = BuildConfig.SMK_WB1
                    1 -> defaultBaseUrl = BuildConfig.SMK_WB2
                    2 -> defaultBaseUrl = BuildConfig.SMP_WB
                    3 -> defaultBaseUrl = BuildConfig.BASE_URL
                }
            }

        }

        val url: String = defaultBaseUrl + ApiLink.goLogin()
        val request: JsonObjectRequest = object : JsonObjectRequest(Request.Method.POST, url, null,
                Response.Listener<JSONObject> { response ->
                    Log.d("response", response.toString())
                    if (response.getString("loginStatus") == "0") {
                        Snackbar.make(container, "Kesalahan : ${response.getString("msg")}", Snackbar.LENGTH_LONG).show()
                    } else if (response.getString("loginStatus") == "1") {
                        UserSession.createSignInSession(this, response.getString("NIS"), defaultBaseUrl)
                        val i = Intent(this, MainActivity::class.java)
                        i.putExtra(getString(R.string.nis), response.getString("NIS"))
                        startActivity(i)
                    }
                },
                Response.ErrorListener { error ->
                    Snackbar.make(container, "Terjadi Kesalahan : $error", Snackbar.LENGTH_LONG).show()
                }) {
            override fun getParams(): MutableMap<String, String> {
                val params: MutableMap<String, String> = mutableMapOf()
                params["username"] = loginNis.text.toString()
                params["password"] = loginPassword.text.toString()
                return params
            }

            override fun getBody(): ByteArray {
                val params: HashMap<String, String> = hashMapOf()
                params["username"] = loginNis.text.toString()
                params["password"] = loginPassword.text.toString()
                return JSONObject(params).toString().toByteArray()
            }

            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String> {
                val params: MutableMap<String, String> = mutableMapOf()
                params["username"] = loginNis.text.toString()
                params["password"] = loginPassword.text.toString()
                return params
            }
        }

        sign_in_button.setOnClickListener {
            queue.add(request)
//            val username: String = nis.text.trim().toString()
//            val password: String = passwords.text.trim().toString()
//            if (username == authUser && password == authPass) {
//                UserSession.createSignInSession(this, username, defaultBaseUrl)
//                val i = Intent(this, MainActivity::class.java)
//                i.putExtra(getString(R.string.nis), username)
//                startActivity(i)
//            } else {
//                Toast.makeText(this, "Can't Sign-in", Toast.LENGTH_SHORT).show()
//            }
        }


    }

}
