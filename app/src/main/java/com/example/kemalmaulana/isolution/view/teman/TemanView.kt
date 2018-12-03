package com.example.kemalmaulana.isolution.view.teman

import com.example.kemalmaulana.isolution.model.content.Teman

interface TemanView {
    fun showLoading()
    fun hideLoading()
    fun getListTeman(teman: List<Teman>)
}