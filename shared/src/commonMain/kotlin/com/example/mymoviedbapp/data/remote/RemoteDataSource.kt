package com.example.mymoviedbapp.data.remote

import com.example.mymoviedbapp.util.Dispatcher
import kotlinx.coroutines.withContext

// No need to provide Dispatcher since kmm will provide
// IO context for android and DEFAULT for IOS
internal class RemoteDataSource(
    private val apiService: MovieService,
    private val dispatcher: Dispatcher
) {
    suspend fun getMovies(page: Int) =
        withContext(dispatcher.io) { apiService.getMovies(page = page) }

    suspend fun getMovie(movieId: Int) =
        withContext(dispatcher.io) { apiService.getMovie(movieId = movieId) }
}