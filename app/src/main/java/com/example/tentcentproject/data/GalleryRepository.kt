package com.example.tentcentproject.data

import com.example.tentcentproject.data.database.AppDatabase
import com.example.tentcentproject.data.model.Picture
import javax.inject.Inject

class GalleryRepository(appDatabase: AppDatabase) {
    private val pictureDao = appDatabase.PictureDao()

    suspend fun insert(picture: Picture) {
        pictureDao.insert(picture)
    }

    suspend fun update(picture: Picture) {
        pictureDao.update(picture)
    }

    suspend fun getAll(): List<Picture> {
        return pictureDao.getAll()
    }

    suspend fun setUri(id: Int, uri: String) {
        pictureDao.setUri(id, uri)
    } 
}