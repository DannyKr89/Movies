package ru.dk.movies.data.model

data class MovieDetails(
    val id: Int,
    val name: String,
    val year: Int,
    val rating: MovieDTO.Rating,
    val poster: MovieDTO.Poster,
    val description: String
)
