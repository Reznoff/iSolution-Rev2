package com.example.kemalmaulana.isolution.model.repository

import java.net.URL

class ApiRepository {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}