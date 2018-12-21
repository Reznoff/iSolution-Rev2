package com.example.kemalmaulana.isolution.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar.*
import android.view.View
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kemalmaulana.isolution.BuildConfig

import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.content.Login
import com.example.kemalmaulana.isolution.model.repository.ApiLink
import com.google.firebase.iid.FirebaseInstanceId
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
    private val token by lazy {
        val session = getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        session.getString("fcmToken", null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.

        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { result ->
            UserSession.setFcmToken(this, result.result?.token)
        }

        container = findViewById(R.id.linearLayout2)
        queue = Volley.newRequestQueue(this)
//        open var defaultBaseUrl = BuildConfig.BASE_URL

        sekolahSpinner = findViewById(R.id.spinnerSekolah)
        var defaultBaseUrl: String = ""
        val sekolahNama = listOf("SMK WB 1", "SMK WB 2", "SMP WB", "Demo")
        sekolahAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sekolahNama)
        sekolahSpinner.adapter = sekolahAdapter
        sekolahSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (parent?.selectedItemPosition) {
                    0 -> {
                        sign_in_button.setOnClickListener {
                            queue.add(doLogin(BuildConfig.SMK_WB1))
                            queue.add(doFCMLogin(BuildConfig.SMK_WB1))
                        }
                    }
                    1 -> {
                        sign_in_button.setOnClickListener {
                            queue.add(doLogin(BuildConfig.SMK_WB2))
                            queue.add(doFCMLogin(BuildConfig.SMK_WB2))
                        }
                    }
                    2 -> {
                        sign_in_button.setOnClickListener {
                            queue.add(doLogin(BuildConfig.SMP_WB))
                            queue.add(doFCMLogin(BuildConfig.SMP_WB))
                        }
                    }
                    3 -> {
                        sign_in_button.setOnClickListener {
                            queue.add(doLogin(BuildConfig.BASE_URL))
                            queue.add(doFCMLogin(BuildConfig.BASE_URL))
                        }
                    }
                    else -> {
                        sign_in_button.setOnClickListener {
                            queue.add(doLogin(BuildConfig.BASE_URL))
                            queue.add(doFCMLogin(BuildConfig.BASE_URL))

                        }
                    }
                }
            }
        }
//
//        sign_in_button.setOnClickListener {
//            queue.add(stringRequest)
//        }
    }

    private fun doLogin(defaultBaseUrl: String): StringRequest {
        val url = "$defaultBaseUrl${ApiLink.goLogin()}"
        //String Request
        return object : StringRequest(Method.POST, url,
                Response.Listener { response ->
                    //                    Log.d("response", response.toString())
//                    Log.d("username", loginNis.text.toString())
//                    Log.d("password", loginPassword.text.toString())
//                    Log.d("url", url)
                    try {
                        val parsedResponse = Gson().fromJson(response, Login::class.java)
//                        Log.d("loginStatus", parsedResponse.statusLogin)
                        if (loginNis.text.toString().isEmpty() && loginPassword.text.toString().isEmpty()) {
                            make(container, "Username / Password Tidak Boleh Kosong", LENGTH_LONG).show()
                        } else if (parsedResponse.statusLogin == "0") {
                            make(container, "Username / Password Salah", LENGTH_LONG).show()
                        } else if (parsedResponse.statusLogin == "1") {
                            //onSuccess

                            //doFCMLogin
//                            Log.d("fcmsuccess", token)
                            UserSession.createSignInSession(this, parsedResponse.nis, parsedResponse.id_kelas, parsedResponse.kelas, defaultBaseUrl)
                            val i = Intent(this, MainActivity::class.java)
                            i.putExtra(getString(R.string.nis), parsedResponse.nis)
                            startActivity(i)
                        }
                    } catch (e: JSONException) {
                        make(container, "Kesalahan : ${e.localizedMessage}", LENGTH_LONG).show()
                    } catch (ex: Exception) {
                        make(container, "Kesalahan : ${ex.localizedMessage}", LENGTH_LONG).show()
                    }
                },
                Response.ErrorListener { error ->
                    make(container, "Terjadi Kesalahan : ${error.printStackTrace()}", LENGTH_LONG).show()
                }) {

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
                params["Content-Type"] = "application/x-www-form-urlencoded"
                return params
            }

            @Throws(AuthFailureError::class)
            override fun getBodyContentType(): String {
                return "application/x-www-form-urlencoded; charset=UTF-8"
            }
        }
    }


    private fun doFCMLogin(defaultBaseUrl: String): StringRequest {
        val url = "$defaultBaseUrl${ApiLink.fcmAfterLogin()}"
        //String Request
        return object : StringRequest(Method.POST, url,
                Response.Listener { response ->
                    try {

//                        Log.d("fcmResponse", response)
                        val parsedResponse = Gson().fromJson(response, Login::class.java)

                        if (parsedResponse.status == "1") {
//                            Log.d("fcm", "ok (${parsedResponse.status})")
                        }


                    } catch (e: java.lang.Exception) {

                    }
                },
                Response.ErrorListener { error ->
                    make(container, "Terjadi Kesalahan : ${error.printStackTrace()}", LENGTH_LONG).show()
                }) {

            @Throws(AuthFailureError::class)
            override fun getBody(): ByteArray? {
                val params: MutableMap<String, String> = hashMapOf()
                try {
                    params["nis"] = loginNis.text.toString()
                    params["fcm"] = token
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
                params["Content-Type"] = "application/x-www-form-urlencoded"
                return params
            }

            @Throws(AuthFailureError::class)
            override fun getBodyContentType(): String {
                return "application/x-www-form-urlencoded; charset=UTF-8"
            }
        }
    }

    //to encode parameter
    private fun encodeParameters(params: Map<String, String>, paramsEncoding: String): ByteArray {
        val encodedParams = StringBuilder()
        try {
            for (entry: Map.Entry<String, String> in params.entries) {
                encodedParams.append(URLEncoder.encode(entry.key, paramsEncoding))
                encodedParams.append('=')
                encodedParams.append(URLEncoder.encode(entry.value, paramsEncoding))
                encodedParams.append('&')
            }
            return encodedParams.toString().toByteArray()
        } catch (uee: UnsupportedEncodingException) {
            throw RuntimeException("Encoding not supported: $paramsEncoding", uee)
        }
    }

}
