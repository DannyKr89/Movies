package ru.dk.movies.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDTO(
    val id: Int,
    val name: String,
    val poster: Poster,
    val year: Int,
    val rating: Rating
) : Parcelable {
    @Parcelize
    data class Poster(
        val url: String
    ) : Parcelable

    @Parcelize
    data class Rating(
        val kp: Float
    ) : Parcelable
}