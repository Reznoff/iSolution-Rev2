package com.example.kemalmaulana.isolution.view.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.model.content.Gambar
import com.example.kemalmaulana.isolution.model.content.Profile
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.presenter.ProfilePresenter
import com.example.kemalmaulana.isolution.view.profile.`interface`.ProfileView
import com.example.kemalmaulana.isolution.utils.genderParser
import com.example.kemalmaulana.isolution.view.profile.fragment.ProfileOrangTuaFragment
import com.example.kemalmaulana.isolution.view.profile.fragment.ProfilePribadiFragment
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), ProfileView {

    private lateinit var pagerAdapter: SectionPagerAdapter
    private lateinit var pager: ViewPager
    lateinit var pribadi: Bundle
    lateinit var orangtua: Bundle

    private val nis: String by lazy {
        val session = getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        session.getString("NIS", null)
    }

    lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    lateinit var appBar: AppBarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val presenter = ProfilePresenter(ApiRepository(), Gson(), this)
        presenter.getProfileData(nis)

        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pribadi = Bundle()
        orangtua = Bundle()

        val tabLayout: TabLayout = findViewById(R.id.tabs)
        pagerAdapter = SectionPagerAdapter(supportFragmentManager)
        pager = findViewById(R.id.vpProfile)
        pager.adapter = pagerAdapter

        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(pager))

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun getData(profile: Profile, gambar: Gambar) {
        Picasso.get().load("https:${gambar.photo}").into(imgProfile)
        txtNis.text = profile.nis
        txtNama.text = profile.namaLengkap
        txtAlamat.text = profile.alamat
        txtJenisKelamin.text = genderParser(profile.jenisKelamin)

        offsetToolbar(profile.namaLengkap)
    }

    private fun offsetToolbar(message: String?) {
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar)
        appBar = findViewById(R.id.appbar)

        var isShow = true
        var scrollRange = -1
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if(scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }
            if(scrollRange + verticalOffset == 0) {
                collapsingToolbarLayout.title = "$message Profile"
                isShow = true
            } else if(isShow) {
                collapsingToolbarLayout.title = " "
                isShow = false
            }
        })
    }

    inner class SectionPagerAdapter(fm: FragmentManager?): FragmentStatePagerAdapter(fm) {
        override fun getItem(pos: Int): Fragment? {
            when(pos) {
                0 -> {
                    val fragment = ProfilePribadiFragment()
                    pribadi.putString("nis", nis)
                    fragment.arguments = pribadi
                    return fragment
                }
                1 -> {
                    val fragment = ProfileOrangTuaFragment()
                    orangtua.putString("nis", nis)
                    fragment.arguments = orangtua
                    return fragment
                }
            }
            return null
        }

        override fun getCount(): Int = 2

    }
}
