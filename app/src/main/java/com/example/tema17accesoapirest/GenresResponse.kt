package com.example.tema17accesoapirest


data class GenresResponse(
    val genres: List<Genre>
) {
    data class Genre(
        val id: Int,
        val name: String
    )
}