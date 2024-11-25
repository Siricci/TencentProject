package com.example.tentcentproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tentcentproject.data.model.Picture
import com.example.tentcentproject.data.dao.PictureDao

@Database(entities = [Picture::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PictureDao(): PictureDao
}