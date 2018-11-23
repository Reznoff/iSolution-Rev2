package com.example.kemalmaulana.isolution.view.profile.`interface`

import com.example.kemalmaulana.isolution.model.content.Jadwal

interface JadwalView {
    fun showLoading()
    fun hideLoading()
    fun showJadwal(jadwal: List<Jadwal>)
}