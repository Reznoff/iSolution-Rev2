package com.example.kemalmaulana.isolution.view.activity

import android.annotation.TargetApi
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import android.support.v4.content.ContextCompat
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
import com.example.kemalmaulana.isolution.model.content.Kehadiran
import com.example.kemalmaulana.isolution.model.content.KehadiranJadwal
import com.example.kemalmaulana.isolution.model.content.Profile
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.presenter.KehadiranPresenter
import com.example.kemalmaulana.isolution.utils.CircleTransform
import com.example.kemalmaulana.isolution.utils.formatToHourMinuteSecond
import com.example.kemalmaulana.isolution.utils.parseToHourMinuteSecond
import com.example.kemalmaulana.isolution.view.kehadiran.`interface`.KehadiranView
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, KehadiranView {

    private lateinit var builder: AlertDialog.Builder
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
        nav_view.itemIconTintList = null

        //On Swipable menu
        cardSekolah.setOnClickListener { startActivity(Intent(this, PengumumanSekolahActivity::class.java)) }
        //On Top Home Menu
        cardStatus.setOnClickListener { startActivity(Intent(this, DetailMapelActivity::class.java)) }
        //On Small Icon Menu
        cardInformasi.setOnClickListener { startActivity(Intent(this, PengumumanSekolahActivity::class.java)) }
        cardJadual.setOnClickListener { startActivity(Intent(this, DetailMapelActivity::class.java)) }
        cardGuru.setOnClickListener { startActivity(Intent(this, GuruActivity::class.java)) }
        cardProfile.setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
        cardTeman.setOnClickListener { startActivity(Intent(this, TemanActivity::class.java)) }

        val presenter = KehadiranPresenter(ApiRepository(), Gson(), this, this)
        presenter.getStatusKehadiran(nis)
        kehadiranNotification()
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            if(!it.isSuccessful) {
                Log.w("FCM", "Get Instance Failed", it.exception)
            }

            val token = it.result?.token
            Log.d("FCM","Current Token $token")

        }
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
            R.id.nav_informasi -> {
                startActivity(Intent(this, PengumumanSekolahActivity::class.java))
            }
            R.id.nav_guru -> {
                startActivity(Intent(this, GuruActivity::class.java))
            }
            R.id.nav_jadwal -> {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("section", "jadwal")
                startActivity(intent)
            }
            R.id.nav_teman -> {
                startActivity(Intent(this, TemanActivity::class.java))
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
                .setPositiveButton("Ya") { _, _ ->
                    UserSession.logoutSession(this)
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                .setNegativeButton("Tidak") { _, _ -> }
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
        //Status Kehadiran Kelas
        when(status.statusHadir) {
            "1" -> indicatorKSekolah.background = ContextCompat.getDrawable(this, R.drawable.circle_view_red)
            "2" -> indicatorKSekolah.background = ContextCompat.getDrawable(this, R.drawable.circle_view_green)
            "3" -> indicatorKSekolah.background = ContextCompat.getDrawable(this, R.drawable.circle_view_blue)
            "4" -> indicatorKSekolah.background = ContextCompat.getDrawable(this, R.drawable.circle_view_yellow)
            else -> indicatorKSekolah.background = ContextCompat.getDrawable(this, R.drawable.circle_view_unspecified)
        }

        //Status Kehadiran Kelas
        for(item: KehadiranJadwal in status.kehadiran!!) {
            //getCurrentTime from system
            val currentTime = formatToHourMinuteSecond(Calendar.getInstance().time)
            val date = parseToHourMinuteSecond(currentTime)
            val jamAkhir = parseToHourMinuteSecond(item.jamAkhir)
            val jamAwal = parseToHourMinuteSecond(item.jamAwal)
            if(date < jamAkhir && date > jamAwal) {
                txtMapel.text = getString(R.string.mata_pelajaran) + " ${item.namaPelajaran}"
                when(item.kehadiran) {
                    "1" -> indicatorKKelas.background = ContextCompat.getDrawable(this, R.drawable.circle_view_red)
                    "2" -> indicatorKKelas.background = ContextCompat.getDrawable(this, R.drawable.circle_view_green)
                    "3" -> indicatorKKelas.background = ContextCompat.getDrawable(this, R.drawable.circle_view_blue)
                    "4" -> indicatorKKelas.background = ContextCompat.getDrawable(this, R.drawable.circle_view_yellow)
                    else -> indicatorKKelas.background = ContextCompat.getDrawable(this, R.drawable.circle_view_unspecified)
                }
            } else {
                txtMapel.text = getString(R.string.tidak_ada_jadwal)
                when(item.kehadiran) {
                    "1" -> indicatorKKelas.background = ContextCompat.getDrawable(this, R.drawable.circle_view_red)
                    "2" -> indicatorKKelas.background = ContextCompat.getDrawable(this, R.drawable.circle_view_green)
                    "3" -> indicatorKKelas.background = ContextCompat.getDrawable(this, R.drawable.circle_view_blue)
                    "4" -> indicatorKKelas.background = ContextCompat.getDrawable(this, R.drawable.circle_view_yellow)
                    else -> indicatorKKelas.background = ContextCompat.getDrawable(this, R.drawable.circle_view_unspecified)
                }
            }
        }
    }

    override fun getProfile(profile: Profile, gambar: Gambar) {
        //Main Info
        Picasso.get()
                .load("https:${gambar.photo}")
                .error(R.drawable.ic_logo_profile)
                .transform(CircleTransform())
                .into(imgDashBoardProfile)
        txtNamaLengkap.text = profile.namaLengkap

        //Navigation Header
        val header: View = nav_view.getHeaderView(0)
        val navImage: ImageView = header.findViewById(R.id.navimgSiswa)
        val navNama: TextView = header.findViewById(R.id.navNama)
        val navNis: TextView = header.findViewById(R.id.navNis)
        navNama.text = profile.namaLengkap
        navNis.text = profile.nis
        Picasso.get()
                .load("https:${gambar.photo}")
                .error(R.drawable.ic_logo_profile)
                .transform(CircleTransform())
                .into(navImage)
        navImage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    public fun kehadiranNotification() {
        val notifyId = 1
        val channelId = "kehadiran_notification"
        val name = "Status Kehadiran"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel  = NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_DEFAULT)
            val notification: Notification? = NotificationCompat.Builder(this, channelId)
                    .setContentTitle("Kehadiran Siswa")
                    .setContentText("Pesan yang akan diterima")
                    .setSmallIcon(R.drawable.icon_pena)
                    .setShowWhen(true)
                    .setChannelId(channelId)
                    .build()

            val mNotificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotificationManager.createNotificationChannel(channel)
//            mNotificationManager.notify(notifyId, notification)

            val stackBuilder = TaskStackBuilder.create(this)
            stackBuilder.addNextIntent(Intent(this, MainActivity::class.java))
            val resultPendingIntent: PendingIntent? = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            notification?.contentIntent = resultPendingIntent
            mNotificationManager.notify(notifyId, notification)
        } else {
            val notification: NotificationCompat.Builder = NotificationCompat.Builder(this)
                    .setContentTitle("Kehadiran Siswa")

            TODO("VERSION.SDK_INT < O")
        }

    }
}
