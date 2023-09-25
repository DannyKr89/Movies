package ru.dk.movies.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ru.dk.movies.data.model.MovieDTO
import ru.dk.movies.domain.MoviesRepo

class MoviesViewModel(private val repository: MoviesRepo) : ViewModel() {


    fun getMovies(): LiveData<PagingData<MovieDTO>> {
        return repository.getMovies().cachedIn(viewModelScope)
    }

}