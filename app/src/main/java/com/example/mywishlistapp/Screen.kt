package com.example.mywishlistapp

sealed class Screen (val route:String){ // no one can inherit from sealed class
    object HomeScreen: Screen("home_screen")
    object AddScreen : Screen("add_screen")
}