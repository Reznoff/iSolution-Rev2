package com.example.kemalmaulana.isolution.view.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.content.Gambar
import com.example.kemalmaulana.isolution.model.content.Profile
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.presenter.ProfilePresenter
import com.example.kemalmaulana.isolution.view.profile.`interface`.ProfileView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var builder: AlertDialog.Builder
    val nis: String by lazy {
        val session = getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        session.getString("NIS", null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        vectorSetup()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val header: View = nav_view.getHeaderView(0)
        val navImage: ImageView = header.findViewById(R.id.imgSiswa)
        val navNis: TextView = header.findViewById(R.id.navNis)
        val navNama:TextView = header.findViewById(R.id.navNama)

        navNis.text = nis
        navNama.visibility = View.GONE
        navImage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    fun kehadiranClicked(view: View) {
        val kehadiran = Intent(this, DetailActivity::class.java)
        kehadiran.putExtra("section", "kehadiran")
        startActivity(kehadiran)
    }

    fun reportClicked(view: View) {
        val kehadiran = Intent(this, DetailActivity::class.java)
        kehadiran.putExtra("section", "report")
        startActivity(kehadiran)
    }

    fun jadwalClicked(view: View) {
        val kehadiran = Intent(this, DetailActivity::class.java)
        kehadiran.putExtra("section", "jadwal")
        startActivity(kehadiran)
    }

    fun pembayaranClicked(view: View) {
        val kehadiran = Intent(this, DetailActivity::class.java)
        kehadiran.putExtra("section", "pembayaran")
        startActivity(kehadiran)
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_pengumuman -> {
                //TODO- DO SOMETHING
            }
            R.id.nav_setting -> {
                //TODO- DO SOMETHING
            }
            R.id.nav_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }
            R.id.nav_logout -> {
                dialog(this)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun dialog(context: Context) {
        builder = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert)
        } else {
            AlertDialog.Builder(context)
        }
        builder.setTitle(R.string.keterangan)
                .setMessage(R.string.logout_alert)
                .setPositiveButton("Ya") { dialog, which ->
                    UserSession.logoutSession(this)
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                .setNegativeButton("Tidak") { dialog, which -> }
                .show()
    }

    private fun vectorSetup() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

}
