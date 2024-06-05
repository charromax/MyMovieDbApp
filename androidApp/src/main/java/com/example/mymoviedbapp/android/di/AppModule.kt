package com.example.mymoviedbapp.android.di

import com.example.mymoviedbapp.android.detail.DetailViewModel
import com.example.mymoviedbapp.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { params ->
        DetailViewModel(get(), params.get())
    }
}