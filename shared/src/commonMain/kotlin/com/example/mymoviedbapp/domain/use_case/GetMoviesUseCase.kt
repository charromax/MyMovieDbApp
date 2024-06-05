package com.example.mymoviedbapp.domain.use_case

import com.example.mymoviedbapp.domain.model.Movie
import com.example.mymoviedbapp.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesUseCase : KoinComponent {
    private val repository: MovieRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(page: Int): List<Movie> = repository.getMovies(page)
}

class GetMovieUseCase : KoinComponent {
    private val repository: MovieRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(movieId: Int): Movie = repository.getMovie(movieId)
}