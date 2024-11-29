package com.example.tentcentproject.data

import android.net.Uri
import com.example.tentcentproject.data.database.AppDatabase
import com.example.tentcentproject.data.fileutil.FileUtil
import com.example.tentcentproject.data.model.Picture
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    appDatabase: AppDatabase,
    private val fileUtil: FileUtil
) {
    private val pictureDao = appDatabase.PictureDao()

    suspend fun insert(uri: Uri, filename: String) {
        val picture = Picture(0, filename, System.currentTimeMillis().toString())
        pictureDao.insert(picture)
        fileUtil.storePictureFromUri(uri, filename)
    }
}