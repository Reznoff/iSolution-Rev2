package com.example.kemalmaulana.isolution.view.jadwal.`interface`

import com.example.kemalmaulana.isolution.model.content.KehadiranJadwal

interface JadwalView {
    fun showLoading()
    fun hideLoading()
    fun showJadwal(jadwal: List<KehadiranJadwal>)
}