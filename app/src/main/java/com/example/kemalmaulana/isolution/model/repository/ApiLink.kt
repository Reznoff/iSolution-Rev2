package com.example.kemalmaulana.isolution.model.repository


object ApiLink {

    fun getKehadiranSiswa(nis: String?): String = "/kehadiran/api_kehadiran_siswa_hari_ini/$nis"

    fun getKehadiranSemesterSiswa(nis: String?): String = "/kehadiran/api_get_kehadiran_siswa_per_semester_detail/$nis"

    fun getJadwalSiswa(nis: String?): String = "/jadwal/api_lihat_jadwal_siswa/$nis"

    fun getProfileSiswa(nis: String?): String = "/pelajar/api_profile_siswa/$nis"

    fun getPengumumanSekolah(): String = "/pengumuman/api_get/"

    fun getListGuru(): String = "/staff/api_get_pengajar"

    fun getListTeman(nis: String?, kelas: String?): String = "/pelajar/api_teman_siswa/$nis/$kelas"

    fun goLogin(): String = "login/api_login/"
}