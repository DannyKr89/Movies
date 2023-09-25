package ru.dk.movies.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import ru.dk.movies.data.model.MovieDTO

interface MoviesRepo {
    fun getMovies(): LiveData<PagingData<MovieDTO>>
}