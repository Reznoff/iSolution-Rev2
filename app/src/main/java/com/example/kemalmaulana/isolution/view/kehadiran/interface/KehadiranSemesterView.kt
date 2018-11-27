package com.example.kemalmaulana.isolution.view.kehadiran.`interface`

import com.example.kemalmaulana.isolution.model.content.KehadiranSemester

interface KehadiranSemesterView {
    fun showLoading()
    fun hideLoading()
    fun getEntryData(kehadiran: KehadiranSemester)
}