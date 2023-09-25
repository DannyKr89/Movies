package ru.dk.movies.data.retrofit

import com.google.gson.annotations.SerializedName
import ru.dk.movies.data.model.MovieDTO

data class MovieResponse(
    @SerializedName("docs")
    val movies: List<MovieDTO>
) {
}