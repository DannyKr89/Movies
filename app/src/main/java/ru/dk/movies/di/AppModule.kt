package ru.dk.movies.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dk.movies.data.MoviesRepoImpl
import ru.dk.movies.data.retrofit.MoviesApi
import ru.dk.movies.domain.MoviesRepo
import ru.dk.movies.ui.detail.MovieDetailsViewModel
import ru.dk.movies.ui.movies.MovieAdapter
import ru.dk.movies.ui.movies.MoviesViewModel

val appModule = module {
    factory { MovieAdapter() }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(MoviesApi.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<MoviesApi> { get<Retrofit>().create(MoviesApi::class.java) }
    single<MoviesRepo> { MoviesRepoImpl(get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
}