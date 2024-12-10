package com.example.tentcentproject.data

import android.content.Context
import androidx.core.net.toUri
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import javax.inject.Inject

@HiltWorker
class CopyWorker @Inject constructor(
    appContext: Context,
    workerParams: WorkerParameters,
    private val repository: GalleryRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        Timber.tag("CopyWorker").d("doWork")

        val sourcePath = inputData.getString("sourcePath")
        val filename = System.currentTimeMillis().toString()

        if (sourcePath != null) {
            val sourceUri = sourcePath.toUri()

            return withContext(Dispatchers.IO) { // 在 I/O 线程池中执行复制操作
                try {
                    repository.insert(sourceUri, filename)
                    Result.success()
                } catch (e: Exception) {
                    Result.failure()
                }
            }
        } else {
            return Result.failure()
        }
    }
}