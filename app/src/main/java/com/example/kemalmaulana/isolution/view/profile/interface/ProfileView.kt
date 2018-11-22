package com.example.kemalmaulana.isolution.view.profile.`interface`

import com.example.kemalmaulana.isolution.model.content.Gambar
import com.example.kemalmaulana.isolution.model.content.Profile

interface ProfileView {
    fun showLoading()
    fun hideLoading()
    fun getData(profile: Profile, gambar: Gambar)
}