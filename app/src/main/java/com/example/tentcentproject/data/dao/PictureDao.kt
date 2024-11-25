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

    // TODO 尝试在这里加入分页加载功能
    @Query("SELECT * FROM gallery ORDER BY timestamp DESC")
    suspend fun getAll(): List<Picture>

    @Query("UPDATE gallery SET uri = :uri WHERE id = :id")
    suspend fun setUri(id: Int, uri: String)
}

