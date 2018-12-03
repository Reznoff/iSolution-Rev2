package com.example.kemalmaulana.isolution.view.guru.interfaces

import com.example.kemalmaulana.isolution.model.content.Guru

interface GuruView {
    fun showLoading()
    fun hideLoading()
    fun getListGuru(guru: List<Guru>)
}