package ru.dk.movies.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import ru.dk.movies.data.retrofit.MovieResponse
import ru.dk.movies.data.retrofit.MoviesApi
import ru.dk.movies.domain.MoviesRepo

class MoviesRepoImpl(private val api: MoviesApi) : MoviesRepo {
    override suspend fun getMovies(): Flow<MovieResponse> = flow {
        emit(api.getMovies(page = 1).await())
    }
}