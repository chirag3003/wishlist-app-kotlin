package com.example.wishlist

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WishlistItem::class], version = 1, exportSchema = false)
abstract class WishDatabase: RoomDatabase() {
    abstract fun wishDao(): WishDao
}