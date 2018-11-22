package com.example.kemalmaulana.isolution.model.repository

import com.example.kemalmaulana.isolution.BuildConfig

object ApiLink {

    fun getKehadiranSiswa(nis: String): String {
        return BuildConfig.BASE_URL+"/kehadiran/api_kehadiran_siswa_hari_ini/"+nis
    }

    fun getJadwalSiswa(nis: String): String {
        return BuildConfig.BASE_URL+"/jadwal/api_lihat_jadwal_siswa/"+nis
    }

    fun getProfileSiswa(nis: String): String {
        return BuildConfig.BASE_URL+"/pelajar/api_profile_siswa/"+nis
    }

}