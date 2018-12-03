package com.example.kemalmaulana.isolution.model.content

import com.google.gson.annotations.SerializedName

data class Teman(
        @SerializedName("nama_lengkap")
        val namaTeman: String? = null,

        @SerializedName("NIS")
        val nisTeman: String? = null
)
