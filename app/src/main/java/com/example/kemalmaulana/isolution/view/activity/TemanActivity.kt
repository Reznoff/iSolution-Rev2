package com.example.kemalmaulana.isolution.view.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.UserSession
import com.example.kemalmaulana.isolution.model.content.Teman
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.presenter.TemanPresenter
import com.example.kemalmaulana.isolution.view.teman.TemanAdapter
import com.example.kemalmaulana.isolution.view.teman.TemanView
import com.google.gson.Gson

class TemanActivity : BaseActivity(), TemanView {

    private lateinit var presenter: TemanPresenter
    private lateinit var adapter: TemanAdapter
    private lateinit var listTeman: RecyclerView
    private lateinit var loadingLayout: ConstraintLayout
    private lateinit var toolbar: Toolbar

    val nis: String by lazy {
        val session = getSharedPreferences(UserSession.PREF_NAME, Context.MODE_PRIVATE)
        session.getString("NIS", null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teman)

        loadingLayout = findViewById(R.id.loadingLayout)
        listTeman = findViewById(R.id.listTeman)

        initToolbar()

        presenter = TemanPresenter(ApiRepository(), Gson(), this, this)
        presenter.getListTeman(nis)

    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Daftar Teman"
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showLoading() {
        listTeman.visibility = View.GONE
        loadingLayout.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        listTeman.visibility = View.VISIBLE
        loadingLayout.visibility = View.GONE
    }

    override fun getListTeman(teman: List<Teman>) {
        adapter = TemanAdapter(this, teman)
        listTeman.adapter = adapter
        adapter.notifyDataSetChanged()

        val layoutManager = LinearLayoutManager(this)
        listTeman.layoutManager = layoutManager
        listTeman.setHasFixedSize(true)
    }
}
