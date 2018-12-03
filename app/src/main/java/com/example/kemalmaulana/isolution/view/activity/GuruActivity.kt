package com.example.kemalmaulana.isolution.view.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.DecorToolbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.kemalmaulana.isolution.R
import com.example.kemalmaulana.isolution.model.content.Guru
import com.example.kemalmaulana.isolution.model.repository.ApiRepository
import com.example.kemalmaulana.isolution.presenter.GuruPresenter
import com.example.kemalmaulana.isolution.view.guru.adapter.GuruAdapter
import com.example.kemalmaulana.isolution.view.guru.interfaces.GuruView
import com.google.gson.Gson

class GuruActivity : BaseActivity(), GuruView {

    private lateinit var presenter: GuruPresenter
    private lateinit var adapter: GuruAdapter
    private lateinit var listGuru: RecyclerView
    private lateinit var loadingLayout: ConstraintLayout
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guru)
        loadingLayout = findViewById(R.id.loadingLayout)
        listGuru = findViewById(R.id.listGuru)

        initToolbar()

        presenter = GuruPresenter(ApiRepository(), Gson(), this, this)
        presenter.getListGuru()


    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Daftar Guru"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun showLoading() {
        loadingLayout.visibility = View.VISIBLE
        listGuru.visibility = View.GONE
    }

    override fun hideLoading() {
        loadingLayout.visibility = View.GONE
        listGuru.visibility = View.INVISIBLE
    }

    override fun getListGuru(guru: List<Guru>) {
        adapter = GuruAdapter(this, guru)
        listGuru.adapter = adapter
        adapter.notifyDataSetChanged()

        val layoutManager = LinearLayoutManager(this)
        listGuru.layoutManager = layoutManager
        listGuru.setHasFixedSize(true)
    }
}
