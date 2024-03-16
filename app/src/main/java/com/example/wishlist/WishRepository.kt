package com.example.wishlist

import kotlinx.coroutines.flow.Flow

class WishRepository(private val WishDao: WishDao) {

    lateinit var getAllWishes: Flow<List<WishlistItem>>
    private fun getWishes(): Flow<List<WishlistItem>> = WishDao.getWishes()

    init {
        getAllWishes = getWishes()
    }
    suspend fun addWish(wish: WishlistItem) {
        WishDao.addWish(wish)
    }
}