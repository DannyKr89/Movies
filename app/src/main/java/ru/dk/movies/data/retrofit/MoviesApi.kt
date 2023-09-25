package ru.dk.movies.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesApi {
    @GET("v1.3/movie")
    @Headers("X-API-KEY: 4VZNMR1-1NQMMGB-N93ER7D-BM87XXG")
    fun getMovies(
        @Query("sort") sort: String = "rating.kp",
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10
    ): Call<MovieResponse>

    companion object {
        const val BASE_URL = "https://api.kinopoisk.dev/"
    }
}