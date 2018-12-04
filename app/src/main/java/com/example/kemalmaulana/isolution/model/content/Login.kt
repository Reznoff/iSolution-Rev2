package com.example.kemalmaulana.isolution.model.content

import com.google.gson.annotations.SerializedName

data class Login(
        @SerializedName("loginStatus")
        val statusLogin: String? = null,

        @SerializedName("NIS")
        val nis: String? = null,

        @SerializedName("level")
        val level: String? = null,

        @SerializedName("id_kelas")
        val id_kelas: String? = null,

        @SerializedName("kelas")
        val kelas: String? = null,

        @SerializedName("msg")
        val msg: String? = null
)