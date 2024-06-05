package com.example.mymoviedbapp.android.common

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val title: String
    val route: String
    val routeWithArgs: String
}

// Home screen destination definition
object Home : Destination {
    override val title: String
        get() = "Movies"
    override val route: String
        get() = "home"
    override val routeWithArgs: String
        get() = route
}

// Detail screen destination definition
object Detail : Destination {
    override val title: String
        get() = "Movie Details"
    override val route: String
        get() = "detail"
    override val routeWithArgs: String
        get() = "$route/{movieId}"

    val arguments = listOf(
        navArgument("movieId") { type = NavType.IntType }
    )
}

val movieDestinations = listOf(Home, Detail)