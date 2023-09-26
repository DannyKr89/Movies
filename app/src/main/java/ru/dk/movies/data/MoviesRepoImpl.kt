package ru.dk.movies.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import kotlinx.coroutines.flow.flow
import retrofit2.await
import ru.dk.movies.data.datasource.MovieDataSource
import ru.dk.movies.data.retrofit.MoviesApi
import ru.dk.movies.domain.MoviesRepo

class MoviesRepoImpl(private val api: MoviesApi) : MoviesRepo {
    override fun getMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MovieDataSource(api) }
        ).liveData

    override fun getMovieDetails(id: Int) = flow {
        emit(api.getMovieDetail(id).await())
    }
}