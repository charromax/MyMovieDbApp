package com.example.mymoviedbapp.domain.model

import com.example.mymoviedbapp.data.remote.MovieRemote

const val BASE_IMAGE_URL= "https://image.tmdb.org/t/p/w500"
data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val releaseDate: String,
)

internal fun MovieRemote.toMovie(): Movie = Movie(
    id = id,
    title = title,
    description = overview,
    imageUrl = getImageUrl(posterImage),
    releaseDate = releaseDate
)

private fun getImageUrl(posterImage: String) = "$BASE_IMAGE_URL$posterImage"