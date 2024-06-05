package com.example.mymoviedbapp.di

import com.example.mymoviedbapp.data.remote.MovieService
import com.example.mymoviedbapp.data.remote.RemoteDataSource
import com.example.mymoviedbapp.domain.repository.MovieRepository
import com.example.mymoviedbapp.data.remote.MovieRepositoryImpl
import com.example.mymoviedbapp.domain.use_case.GetMovieUseCase
import com.example.mymoviedbapp.domain.use_case.GetMoviesUseCase
import com.example.mymoviedbapp.util.provideDispatcher
import org.koin.dsl.module

private val dataModule = module {
    factory { RemoteDataSource(get(), get()) }
    factory { MovieService() }
}

private val dispatcherModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    factory { GetMoviesUseCase() }
    factory { GetMovieUseCase() }
}

private val sharedModules = listOf(domainModule, dataModule, dispatcherModule)

fun getSharedModules() = sharedModules