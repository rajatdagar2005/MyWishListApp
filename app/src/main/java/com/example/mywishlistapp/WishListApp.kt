package com.example.mywishlistapp

import android.app.Application

class WishListApp:Application() {
    override fun onCreate() {
        super.onCreate()
        graph.provide(this)  // this is used means this wishlist app is the whole database
    }
}