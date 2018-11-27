package com.example.kemalmaulana.isolution.view.kehadiran.`interface`

import com.example.kemalmaulana.isolution.model.content.Kehadiran

interface KehadiranView {
    fun showLoading()
    fun hideLoading()
    fun getStatus(status: Kehadiran)
}