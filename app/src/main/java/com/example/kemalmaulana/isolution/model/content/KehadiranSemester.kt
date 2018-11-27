package com.example.kemalmaulana.isolution.model.content

import com.google.gson.annotations.SerializedName

data class KehadiranSemester(
        @SerializedName("kehadiran_siswa")
        val kehadiranSiswa: Int,

        @SerializedName("siswa_sakit")
        val siswaSakit: Int,

        @SerializedName("siswa_tanpa_keterangan")
        val siswaTanpaKeterangan: Int,

        @SerializedName("siswa_izin")
        val siswaIzin: Int
)