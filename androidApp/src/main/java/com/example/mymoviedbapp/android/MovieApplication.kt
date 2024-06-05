package com.example.mymoviedbapp.android

import android.app.Application
import com.example.mymoviedbapp.android.di.appModule
import com.example.mymoviedbapp.di.getSharedModules
import org.koin.core.context.startKoin

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}