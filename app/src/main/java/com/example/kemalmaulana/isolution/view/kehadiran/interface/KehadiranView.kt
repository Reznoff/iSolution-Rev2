package com.example.kemalmaulana.isolution.view.kehadiran.`interface`

import com.example.kemalmaulana.isolution.model.content.Gambar
import com.example.kemalmaulana.isolution.model.content.Kehadiran
import com.example.kemalmaulana.isolution.model.content.Profile

interface KehadiranView {
    fun showLoading()
    fun hideLoading()
    fun getStatus(status: Kehadiran)
    fun getProfile(profile: Profile, gambar: Gambar)
}