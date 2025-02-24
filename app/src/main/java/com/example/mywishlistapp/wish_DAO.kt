package com.example.mywishlistapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mywishlistapp.data.Wish
import kotlinx.coroutines.flow.Flow

@Dao  // data access object
abstract class wish_DAO {  // abstract class is a class that has method who don't need implementation

    @Insert(onConflict = OnConflictStrategy.IGNORE) // if you insert with same id it ignores it
    abstract fun addawish(wishEntity:Wish)

    @Query("Select * From `wish-table` ")   // ` is not a single inverted comma
    abstract  fun getallWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun updateaWish(wishEntity: Wish)

    @Delete
    abstract suspend fun deleteaWish(wishEntity: Wish)

    @Query("Select * From `wish-table` where id=:id ")
    abstract fun getaWishbyId(id:Long): Flow<Wish>
}