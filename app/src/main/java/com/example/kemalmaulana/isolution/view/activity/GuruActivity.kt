package com.example.kemalmaulana.isolution.view.activity

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search_guru, menu)
        val menuItem = menu?.findItem(R.id.search_guru)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menuItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(ComponentName(this, CariGuruResultActivity::class.java)))
        searchView.queryHint = getString(R.string.cari_guru)
        return true
    }


    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Daftar Guru"
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showLoading() {
        loadingLayout.visibility = View.VISIBLE
        listGuru.visibility = View.GONE
    }

    override fun hideLoading() {
        loadingLayout.visibility = View.GONE
        listGuru.visibility = View.VISIBLE
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
