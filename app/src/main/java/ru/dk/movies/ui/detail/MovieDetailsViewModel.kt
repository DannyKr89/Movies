package ru.dk.movies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ru.dk.movies.data.model.MovieDetails
import ru.dk.movies.domain.MoviesRepo

class MovieDetailsViewModel(private val repository: MoviesRepo) : ViewModel() {

    fun getMovieDetails(id: Int): LiveData<MovieDetails> {
        return repository.getMovieDetails(id).asLiveData(viewModelScope.coroutineContext)
    }
}