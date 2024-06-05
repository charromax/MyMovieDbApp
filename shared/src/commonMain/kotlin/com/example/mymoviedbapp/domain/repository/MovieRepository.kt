package com.example.mymoviedbapp.domain.repository

import com.example.mymoviedbapp.domain.model.Movie

internal interface MovieRepository {
    suspend fun getMovies(page: Int): List<Movie>
    suspend fun getMovie(movieId: Int): Movie
}

