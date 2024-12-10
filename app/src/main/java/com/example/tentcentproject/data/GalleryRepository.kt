package com.example.tentcentproject.data

import android.net.Uri
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tentcentproject.data.dao.PictureDao
import com.example.tentcentproject.data.database.AppDatabase
import com.example.tentcentproject.data.fileutil.FileUtil
import com.example.tentcentproject.data.model.Picture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import timber.log.Timber
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    appDatabase: AppDatabase,
    private val fileUtil: FileUtil
) {
    private val  pictureDao = appDatabase.PictureDao()

    suspend fun insert(uri: Uri, filename: String) {
        fileUtil.storePictureFromUri(uri, filename)?.let{ it ->
            val picture = Picture(0, it, System.currentTimeMillis().toString())
            pictureDao.insert(picture)
            Timber.tag("Repository").d("insert picture: $picture")
        }
    }

    fun getItems(): Flow<PagingData<Picture>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { PicturePagingSource(pictureDao) }  // 使用 Hilt 注入的 itemDao
        ).flow
    }
}