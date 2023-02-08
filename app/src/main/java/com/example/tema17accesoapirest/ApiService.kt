package com.example.tema17accesoapirest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apikey: String = ApiRest.api_key,
        @Query("language") language: String = ApiRest.language): Call<GenresResponse>

    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("with_genres") genre: String,
        @Query("api_key") apikey: String = ApiRest.api_key,
        @Query("language") language: String = ApiRest.language): Call<MoviesByGenreResponse>
}