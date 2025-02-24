package com.example.mywishlistapp

import android.content.Context
import androidx.room.Room
import com.example.mywishlistapp.data.Wish
import com.example.mywishlistapp.data.WishRepository

// this is dependency setup in kotlin
object graph {
    lateinit var database: WishDatabase
    val wishRepository by lazy {  // by lazy is used when you want to initialise variable when needed
        WishRepository(wishDao = database.Wish_DAO())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context,WishDatabase::class.java,"wishlist.db").build()
    }
}