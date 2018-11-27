package com.example.kemalmaulana.isolution.model.content

import com.google.gson.annotations.SerializedName

data class Kehadiran(
        @SerializedName("status")
        val status: String? = null,

        @SerializedName("nama_pelajaran")
        val namaPelajaran: String? = null,

        @SerializedName("kehadiran")
        val kehadiran: String? = null
)