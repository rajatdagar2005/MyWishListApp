package com.example.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true) // autoGenerate = true means as new entry comes id increments itself
    val id:Long = 0L,
    @ColumnInfo(name = "wish_title") // giving column name in table
    val title:String = "",
    @ColumnInfo(name = "wish_desc")
    val discription:String = "")

object DummyData{
    val WishList = listOf(
        Wish(title = "google watch", discription = "android 15"),
        Wish(title = "apple watch", discription = "IOS "),
        Wish(title = "noise watch", discription = "android 11"),
        Wish(title = "boat watch", discription = "android 11"),
        Wish(title = "Mi watch", discription = "MIUI version 10"),
        Wish(title = "samsung watch", discription = "android 14"),
        Wish(title = "redmi watch", discription = "MIUI version 12"),
    )
}
