package com.example.tentcentproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tentcentproject.data.model.Picture
import com.example.tentcentproject.data.dao.PictureDao
import com.example.tentcentproject.data.model.Converters

@Database(entities = [Picture::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PictureDao(): PictureDao
}