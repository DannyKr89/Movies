package ru.dk.movies.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import retrofit2.await
import ru.dk.movies.data.model.MovieDTO
import ru.dk.movies.data.retrofit.MoviesApi
import java.io.IOException

class MovieDataSource(private val api: MoviesApi) : PagingSource<Int, MovieDTO>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDTO> {
        return try {
            val position = params.key ?: 1
            val response = api.getMovies(page = position, limit = params.loadSize).await()
            LoadResult.Page(
                data = response.movies,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.movies.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}