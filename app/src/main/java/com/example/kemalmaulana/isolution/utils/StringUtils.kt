package com.example.kemalmaulana.isolution.utils

fun genderParser(gender: String?): String {
    return when(gender) {
        "1" -> "Pria"
        "2" -> "Wanita"
        else -> "Tidak Ditentukan"
    }
}