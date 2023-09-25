package ru.dk.movies.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dk.movies.data.model.MovieDTO
import ru.dk.movies.domain.MoviesRepo

class MoviesViewModel(private val repository: MoviesRepo) : ViewModel() {

    private val _livedata = MutableLiveData<List<MovieDTO>>()
    val liveData: LiveData<List<MovieDTO>> = _livedata


    fun getMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getMovies().collect {
                _livedata.postValue(it.movies)
            }

        }
    }

}