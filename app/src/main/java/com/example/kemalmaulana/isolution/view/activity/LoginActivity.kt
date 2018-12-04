package com.example.kemalmaulana.isolution.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar.*
import android.view.View
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kemalmaulana.isolution.BuildConfig

import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.content.Login
import com.example.kemalmaulana.isolution.model.repository.ApiLink
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import java.io.*
import java.lang.StringBuilder
import java.net.URLEncoder

class LoginActivity : BaseActivity() {

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
        //String Request
        val stringRequest: StringRequest = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
//                    Log.d("response", response.toString())
//                    Log.d("username", loginNis.text.toString())
//                    Log.d("password", loginPassword.text.toString())
                    try {
                        val parsedResponse = Gson().fromJson(response, Login::class.java)
//                        Log.d("loginStatus", parsedResponse.statusLogin)
                        if (loginNis.text.toString().isEmpty() && loginPassword.text.toString().isEmpty()) {
                            make(container, "Username / Password Tidak Boleh Kosong", LENGTH_LONG).show()
                        } else if (parsedResponse.statusLogin == "0") {
                            make(container, "Username / Password Salah", LENGTH_LONG).show()
                        } else if (parsedResponse.statusLogin == "1") {
                            UserSession.createSignInSession(this, parsedResponse.nis, parsedResponse.kelas, defaultBaseUrl)
                            val i = Intent(this, MainActivity::class.java)
                            i.putExtra(getString(R.string.nis), parsedResponse.nis)
                            startActivity(i)
                        }
                    } catch (e: JSONException) {
                        make(container, "Kesalahan : ${e.printStackTrace()}", LENGTH_LONG).show()
                    }
                },
                Response.ErrorListener { error ->
                    make(container, "Terjadi Kesalahan : ${error.printStackTrace()}", LENGTH_LONG).show()
                }) {

//            @Throws(AuthFailureError::class)
//            override fun getParams(): MutableMap<String, String> {
//                val params: MutableMap<String, String> = hashMapOf()
//                params["username"] = loginNis.text.toString()
//                params["password"] = loginPassword.text.toString()
//                return params
//            }

            @Throws(AuthFailureError::class)
            override fun getBody(): ByteArray? {
                val params: MutableMap<String, String> = hashMapOf()
                try {
                    params["username"] = loginNis.text.toString()
                    params["password"] = loginPassword.text.toString()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                return if (params.isNotEmpty()) {
                    encodeParameters(params, paramsEncoding)
                } else {
                    null
                }
            }

            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String> {
                val params: MutableMap<String, String> = mutableMapOf()
//                params["username"] = loginNis.text.toString()
//                params["password"] = loginPassword.text.toString()
                params["Content-Type"] = "application/x-www-form-urlencoded"
                return params
            }

            @Throws(AuthFailureError::class)
            override fun getBodyContentType(): String {
                return "application/x-www-form-urlencoded; charset=UTF-8"
            }
        }


        sign_in_button.setOnClickListener {
            queue.add(stringRequest)
        }
    }

    //to encode parameter
    private fun encodeParameters(params: Map<String, String>, paramsEncoding: String): ByteArray {
        val encodedParams = StringBuilder()
        try {
            for (entry: Map.Entry<String, String> in params.entries) {
                encodedParams.append(URLEncoder.encode(entry.key, paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.value, paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString().toByteArray()
        } catch (uee: UnsupportedEncodingException) {
            throw RuntimeException("Encoding not supported: $paramsEncoding", uee);
        }
    }

}
