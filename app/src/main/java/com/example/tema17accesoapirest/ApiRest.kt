package com.example.tema17accesoapirest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRest {
    lateinit var service: ApiService
    val URL = "https://api.themoviedb.org/3/"
    val URL_IMAGES = "https://image.tmdb.org/t/p/w500"
    val api_key = "23b11b8578cfddee50642519282a40a4"
    val language = "es-ES"
    fun initService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java)
    }
}