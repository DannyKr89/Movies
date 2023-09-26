package ru.dk.movies.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.dk.movies.data.model.MovieDTO
import ru.dk.movies.data.model.MovieDetails

interface MoviesRepo {
    fun getMovies(): LiveData<PagingData<MovieDTO>>
    fun getMovieDetails(id: Int): Flow<MovieDetails>
}