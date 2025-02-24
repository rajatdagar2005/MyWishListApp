package com.example.mywishlistapp.data

import com.example.mywishlistapp.wish_DAO
import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao:wish_DAO) {
    suspend fun AddAWish(wish: Wish){
        wishDao.addawish(wish)
    }
    fun getWishes(): Flow<List<Wish>> = wishDao.getallWishes()
    fun getawishbyid(id:Long):Flow<Wish> {
        return wishDao.getaWishbyId(id)
    }
    suspend fun updatewish(wish: Wish){
        wishDao.updateaWish(wish)
    }
    suspend fun deletewish(wish: Wish){
        wishDao.deleteaWish(wish)
    }
}