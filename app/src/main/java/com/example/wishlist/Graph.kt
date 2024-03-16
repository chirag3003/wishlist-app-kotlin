package com.example.wishlist

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(WishDao = database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java,"wishlist.db").build()
    }
}