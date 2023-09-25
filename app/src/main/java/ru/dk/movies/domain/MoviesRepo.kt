package ru.dk.movies.domain

import kotlinx.coroutines.flow.Flow
import ru.dk.movies.data.retrofit.MovieResponse

interface MoviesRepo {
    suspend fun getMovies(): Flow<MovieResponse>
}