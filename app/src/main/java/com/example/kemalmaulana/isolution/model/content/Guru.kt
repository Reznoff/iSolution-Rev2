package com.example.kemalmaulana.isolution.model.content

import com.google.gson.annotations.SerializedName

data class Guru(
        @SerializedName("id_staff")
        val idGuru: String? = null,

        @SerializedName("nama_staff")
        val namaGuru: String? = null,

        @SerializedName("NIP")
        val nipGuru: String? = null,

        @SerializedName("nama_status")
        val statusGuru: String? = null,

        @SerializedName("photo")
        val photo: String? = null
)