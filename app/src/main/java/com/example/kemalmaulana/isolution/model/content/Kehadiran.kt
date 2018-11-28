package com.example.kemalmaulana.isolution.model.content

import com.google.gson.annotations.SerializedName

data class Kehadiran(
        @SerializedName("status_jadwal")
        val statusJadwal: String? = null,

        @SerializedName("hadir")
        val statusHadir: String? = null,

        @SerializedName("kehadiran")
        val kehadiran: List<KehadiranJadwal>? = null
)