package com.example.mymoviedbapp.data.remote

import com.example.mymoviedbapp.domain.model.toMovie
import com.example.mymoviedbapp.domain.repository.MovieRepository

internal class MovieRepositoryImpl(
    val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override suspend fun getMovies(page: Int) =
        remoteDataSource.getMovies(page).results.map { it.toMovie() }

    override suspend fun getMovie(movieId: Int) =
        remoteDataSource.getMovie(movieId).toMovie()

}