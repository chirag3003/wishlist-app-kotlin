package com.example.wishlist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addWish(wish: WishlistItem)

    @Query("SELECT * FROM `wishlist_table`")
    abstract fun getWishes(): Flow<List<WishlistItem>>
}