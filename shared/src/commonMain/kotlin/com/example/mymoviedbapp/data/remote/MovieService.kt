package com.example.mymoviedbapp.data.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

const val POPULAR_MOVIES = "movie/popular"
const val MOVIE_BY_ID = "movie/"
const val PAGE = "page"

internal class MovieService : KtorApi() {
    suspend fun getMovies(page: Int = 1): MoviesResponse = httpClient.get {
        pathUrl(POPULAR_MOVIES)
        parameter(PAGE, page)
    }.body()

    suspend fun getMovie(movieId: Int): MovieRemote = httpClient.get {
        pathUrl("$MOVIE_BY_ID${movieId}")
    }.body()

}