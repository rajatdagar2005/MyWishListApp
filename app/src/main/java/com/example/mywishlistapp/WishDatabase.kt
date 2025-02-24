package com.example.mywishlistapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mywishlistapp.data.Wish

@Database(
    entities = [Wish::class],
    version = 1, //  because this is the first database we are creating
    exportSchema = false
)
abstract class WishDatabase :RoomDatabase(){
    abstract fun Wish_DAO():wish_DAO
}