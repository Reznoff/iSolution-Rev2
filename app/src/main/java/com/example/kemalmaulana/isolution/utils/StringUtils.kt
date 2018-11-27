package com.example.kemalmaulana.isolution.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

fun genderParser(gender: String?): String {
    return when (gender) {
        "1" -> "Pria"
        "2" -> "Wanita"
        else -> "Tidak Ditentukan"
    }
}

@SuppressLint("SimpleDateFormat")
fun ttlParser(tempat: String?, tanggal: String?): String {
    val date: Date = SimpleDateFormat("yyyy-MM-dd").parse(tanggal)
    val newFormat = SimpleDateFormat("dd MMM yyyy").format(date)
    return "$tempat, $newFormat"
}

fun jadwalParser(jamAwal: String?, jamSelesai: String?): String {
    val newAwal = jamAwal?.substring(0, 5)
    val newAkhir = jamSelesai?.substring(0, 5)
    return "$newAwal - $newAkhir"
}

fun hariParser(hari: String?): String = when (hari) {
    "1" -> "Senin"
    "2" -> "Selasa"
    "3" -> "Rabu"
    "4" -> "Kamis"
    "5" -> "Jum'at"
    "6" -> "Sabtu"
    "7" -> "Minggu"
    else -> ""
}


fun kehadiranParser(kehadiran: String?): String = when (kehadiran) {
    "0" -> "Tidak Hadir"
    "1" -> "Hadir"
    else -> "Tanpa Keterangan"
}