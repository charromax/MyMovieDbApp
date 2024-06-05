package com.example.mymoviedbapp.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoviedbapp.domain.model.Movie
import com.example.mymoviedbapp.domain.use_case.GetMoviesUseCase
import kotlinx.coroutines.launch

data class HomeScreenState(
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val movies: List<Movie> = listOf(),
    val errorMessage: String? = null,
    val loadFinished: Boolean = false,
)

class HomeViewModel(
    val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    var uiState by mutableStateOf(HomeScreenState())
    private var currentPage = 1

    init {
        loadMovies(forceReload = false)
    }

    fun loadMovies(forceReload: Boolean) {
        if (uiState.loading) return
        if (forceReload) currentPage = 1
        if (currentPage == 1) uiState = uiState.copy(refreshing = true)
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)
            try {
                val resultMovies = getMoviesUseCase(page = currentPage)
                val movies = if (currentPage == 1) resultMovies else uiState.movies + resultMovies
                currentPage++
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    movies = movies,
                    loadFinished = resultMovies.isEmpty()
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = true,
                    errorMessage = "unable to load movies: ${e.localizedMessage}"
                )
            }
        }
    }
}