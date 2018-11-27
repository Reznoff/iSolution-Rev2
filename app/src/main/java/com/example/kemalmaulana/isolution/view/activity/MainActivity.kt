package com.example.kemalmaulana.isolution.view.activity

import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.content.Kehadiran
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.presenter.KehadiranPresenter
import com.example.kemalmaulana.isolution.utils.kehadiranParser
import com.example.kemalmaulana.isolution.view.kehadiran.`interface`.KehadiranView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, KehadiranView {

    private lateinit var builder: AlertDialog.Builder
    private lateinit var presenter: KehadiranPresenter
    private lateinit var rootLayout: ConstraintLayout
    private lateinit var loadingLayout: ConstraintLayout
    val nis: String by lazy {
        val session = getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        session.getString("NIS", null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        vectorSetup()

        rootLayout = findViewById(R.id.rootLayout)
        loadingLayout = findViewById(R.id.loadingLayout)

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

        presenter = KehadiranPresenter(ApiRepository(), Gson(), this)
        presenter.getStatusKehadiran(nis)
//        val snackbar = Snackbar.make(findViewById(R.id.drawer_layout), "Anak anda dinyatakan mabal", Snackbar.LENGTH_INDEFINITE)
//        snackbar.view.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
//        snackbar.show()
//        Snackbar.make(findViewById(R.id.rootLayout), "Anak anda dinyatakan mabal", Snackbar.LENGTH_INDEFINITE).show()
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

    override fun showLoading() {
        rootLayout.visibility = View.GONE
        loadingLayout.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rootLayout.visibility = View.VISIBLE
        loadingLayout.visibility = View.GONE
    }

    override fun getStatus(status: Kehadiran) {
        val kehadiran = kehadiranParser(status.status)
//        val snackbar = Snackbar.make(findViewById(R.id.drawer_layout), "Anak anda dinyatakan $kehadiran", Snackbar.LENGTH_INDEFINITE)
        val snackbar = Snackbar.make(findViewById(R.id.drawer_layout), "Anak anda dinyatakan ${status.status}", Snackbar.LENGTH_INDEFINITE)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        snackbar.show()
//        Toast.makeText(this, "Anak anda dinyatakan $kehadiran", Toast.LENGTH_LONG).show()
    }


}
