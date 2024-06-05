package com.example.mymoviedbapp.android.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mymoviedbapp.android.util.launch
import com.example.mymoviedbapp.domain.model.Movie
import com.example.mymoviedbapp.domain.use_case.GetMovieUseCase


data class DetailScreenState(
    val loading: Boolean = false,
    val movie: Movie? = null,
    val errorMessage: String? = null
)

class DetailViewModel(
    val getMovieUseCase: GetMovieUseCase,
    movieId: Int
) : ViewModel() {
    var uiState by mutableStateOf(DetailScreenState(loading = true))
        private set

    init {
        loadMovie(movieId)
    }

    private fun loadMovie(movieId: Int) =
        launch {
            try {
                val movie = getMovieUseCase(movieId)
                uiState = uiState.copy(movie = movie,  loading = false)
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessage = e.localizedMessage, loading = false)
            }
        }
}