package com.example.kemalmaulana.isolution.view.activity

import android.os.Bundle
import com.example.kemalmaulana.isolution.R
import kotlinx.android.synthetic.main.activity_detail_mapel.*

class DetailMapelActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_mapel)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
