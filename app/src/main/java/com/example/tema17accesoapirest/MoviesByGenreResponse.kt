package com.example.tema17accesoapirest


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoviesByGenreResponse(
    @SerializedName("results")
    val results: List<Result>
) {
    data class Result(
        @SerializedName("poster_path")
        val posterPath: String,
        val title: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("release_date")
        val releaseDate: String,
        val overview: String,
        @SerializedName("vote_average")
        val voteAverage: Float
    ): Serializable
}