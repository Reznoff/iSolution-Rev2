package com.example.kemalmaulana.isolution.model.content

import com.google.gson.annotations.SerializedName

data class KehadiranJadwal(
        @SerializedName("hari")
        val hari: String? = null,

        @SerializedName("nama_pelajaran")
        val namaPelajaran: String? = null,

        @SerializedName("jam_awal")
        val jamAwal: String? = null,

        @SerializedName("jam_selesai")
        val jamAkhir: String? = null,

        @SerializedName("kehadiran")
        val kehadiran: String? = null
)