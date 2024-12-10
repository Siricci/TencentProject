package com.example.tentcentproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tentcentproject.data.model.Picture

@Dao
interface PictureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(picture: Picture)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(picture: Picture)

    @Query("SELECT * FROM ${Picture.TABLE_NAME} WHERE id >= :lastItemId ORDER BY id ASC LIMIT :pageSize")
    suspend fun getItemsAfter(lastItemId: Int, pageSize: Int): List<Picture>
}

