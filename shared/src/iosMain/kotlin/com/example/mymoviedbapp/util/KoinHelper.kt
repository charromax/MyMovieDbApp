package com.example.mymoviedbapp.util

import com.example.mymoviedbapp.di.getSharedModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin { modules(getSharedModules()) }
}