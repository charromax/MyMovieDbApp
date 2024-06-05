package com.example.mymoviedbapp.android.util

import android.net.http.NetworkException
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.reflect.KMutableProperty0

@Immutable
data class UiState<T>(val loading: Boolean, val error: Error? = null, val data: T? = null, val mutex: Mutex = Mutex()) {

    open class Error(open val message: String? = null)
}

suspend inline fun <T> KMutableProperty0<UiState<T>>.transform(crossinline transformation: suspend (T) -> T) =
    get().mutex.withLock {
        get().data?.let { data -> set(get().copy(data = transformation(data))) }
    }

fun <T> KMutableProperty0<UiState<T>>.loading(loading: Boolean) {
    set(get().copy(loading = loading))
}

fun <T> KMutableProperty0<UiState<T>>.error(error: String?) {
    set(get().copy(error = UiState.Error(error)))
}

inline fun <T, E : UiState.Error> KMutableProperty0<UiState<T>>.error(crossinline getError: () -> E) {
    set(get().copy(error = getError()))
}

fun <T> KMutableProperty0<UiState<T>>.clearError() {
    set(get().copy(error = null))
}

internal fun ViewModel.launch(
    onLoading: (Boolean) -> Unit = {},
    onError: suspend (String?) -> Unit = {},
    onException: suspend (Exception) -> Unit = {},
    block: suspend () -> Unit
) = viewModelScope.launch {
    try {
        onLoading(true)
        block()
        onLoading(false)
    } catch (e: Exception) {
        onLoading(false)
        onError((e as? Exception)?.message?.takeIf { it.isNotBlank() })
        onException(e)
    }
}